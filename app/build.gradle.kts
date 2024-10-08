plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain {
    languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "tahia.TahiaApp"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
