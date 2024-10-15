plugins {
    application
    id("com.gradleup.shadow") version "8.3.3"
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
