plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.eclipse.jdt:org.eclipse.jdt.core:3.39.0")
    implementation("commons-cli:commons-cli:1.9.0")
    compileOnly("jakarta.annotation:jakarta.annotation-api:3.0.0")

    testImplementation(libs.junit.jupiter)
    testImplementation("net.lingala.zip4j:zip4j:2.11.5")
    testImplementation("org.assertj:assertj-core:3.26.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass = "tahia.TahiaCli"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
