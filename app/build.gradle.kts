plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.eclipse.jdt:org.eclipse.jdt.core:3.39.0")

    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass = "tahia.TahiaApp"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
