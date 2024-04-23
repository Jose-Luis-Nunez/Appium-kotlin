plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.appium:java-client:9.1.0")

    testImplementation("org.jetbrains.kotlin:kotlin-test:1.9.22")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.22")

    implementation("org.testng:testng:7.9.0")
    implementation("io.strikt:strikt-core:0.34.1")
}

tasks.test {
    useTestNG()
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(16))
    }
}
