import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.graalvm.buildtools.gradle.tasks.BuildNativeImageTask
import java.net.URI
import java.nio.file.Files
import java.nio.file.StandardCopyOption

plugins {
    application
    // https://github.com/GradleUp/shadow/releases
    id("com.gradleup.shadow") version "8.3.3"
    // https://graalvm.github.io/native-build-tools/latest/index.html#changelog
    id("org.graalvm.buildtools.native") version "0.10.3"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.eclipse.jdt:org.eclipse.jdt.core:3.39.0")
    implementation("commons-cli:commons-cli:1.9.0")
    compileOnly("jakarta.annotation:jakarta.annotation-api:3.0.0")

    testImplementation("net.lingala.zip4j:zip4j:2.11.5")
    testImplementation("org.assertj:assertj-core:3.26.3")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass = "tahia.TahiaCli"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

val version = "0.0.3"

// https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html
graalvmNative.binaries.all {
    imageName.set("tahia-v$version")
    useFatJar.set(true)
    debug.set(false)
    verbose.set(false)

    val iprofFile = file("./graal/default.iprof")
    val logFile = file("./build/native/nativeCompile/build.log")
    buildArgs.addAll(
        // https://www.graalvm.org/latest/reference-manual/native-image/optimizations-and-performance/#optimization-levels
        "-O2",
        "--gc=G1",
        // "--pgo-instrument",
        "--pgo=$iprofFile",
        "--exact-reachability-metadata",
        "--emit build-report",
        "-Dgraal.LogFile=$logFile",
    )
}

// Make GraalVM use shadowJar as input
tasks.named<BuildNativeImageTask>("nativeCompile") {
    classpathJar.set(tasks.named<ShadowJar>("shadowJar").flatMap { it.archiveFile })
}

// bench stuff

// TODO: better to use task outputs than shared variables
val benchmarkDir = layout.buildDirectory.dir("benchmark")
val testDataDir = layout.buildDirectory.dir("benchmark/testdata")

val downloadTestDataTask = tasks.register("benchmarkDownloadTestData") {
    // Using Apache Commons Lang source code as a formatter test
    val testDataFileName = "commons-lang-3.17.0.zip";
    val testDataFile = benchmarkDir.get().file("downloads/$testDataFileName")
    outputs.file(testDataFile)

    doLast {
        val outputFile = testDataFile.asFile
        if (!outputFile.exists()) {
            val url = "https://github.com/apache/commons-lang/archive/refs/tags/rel/$testDataFileName"
            println("Downloading $url")
            Files.copy(
                URI.create(url).toURL().openStream(),
                outputFile.toPath(),
                StandardCopyOption.REPLACE_EXISTING
            )
        }
    }
}

val unzipTestDataTask = tasks.register<Copy>("benchmarkUnzipTestData") {
    dependsOn(downloadTestDataTask)
    from(zipTree(downloadTestDataTask.get().outputs.files.singleFile))
    into(testDataDir)
}

task<Exec>("benchmarkFormatCodebase") {
    dependsOn(unzipTestDataTask)
    val tahia = "${layout.buildDirectory.get()}/native/nativeCompile/tahia"
    // https://github.com/sharkdp/hyperfine
    commandLine(
        "hyperfine",
        "--warmup=1",
        "--runs=5",
        "--show-output",
        "--export-markdown=${benchmarkDir.get()}/format-codebase-report.md",
        "--prepare", "$rootDir/gradlew benchmarkUnzipTestData",
        "-n", "v0.0.1", "$tahia ${testDataDir.get()}",
        "-n", "v0.0.3", "$tahia-v0.0.3 ${testDataDir.get()}",
    )
}

val copySampleFileTask = tasks.register<Copy>("benchmarkCopySampleFile") {
    from("./src/test/resources/tahia/formatter/SampleCode.txt")
    into(testDataDir.get())
}

task<Exec>("benchmarkFormatFile") {
    dependsOn(copySampleFileTask)
    // https://github.com/sharkdp/hyperfine
    commandLine(
        "hyperfine",
        "--show-output",
        "--export-markdown=${benchmarkDir.get()}/format-code-report.md",
        "--warmup", "1",
        "--prepare", "$rootDir/gradlew benchmarkCopySampleFile",
        "${layout.buildDirectory.get()}/native/nativeCompile/tahia ${testDataDir.get().file("SampleCode.txt")}",
    )
}

