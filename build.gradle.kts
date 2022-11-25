import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
    kotlin("plugin.serialization") version "1.7.10"
    id("io.qameta.allure") version "2.11.2"

}

group = "org.example"
version = "1.0-SNAPSHOT"

val restAssuredVersion = "5.2.0"
val allureVersion = "2.20.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-test")

    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation ("com.google.code.gson:gson:2.10")
    testImplementation ("io.rest-assured:rest-assured:5.3.0")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation ("org.junit.jupiter:junit-jupiter")

    implementation ("com.codeborne:selenide:6.9.0")

    // Allure
    testImplementation ("org.aspectj:aspectjweaver:1.9.9.1")
    implementation ("io.qameta.allure:allure-junit5:${allureVersion}")
    implementation ("io.qameta.allure:allure-assertj:${allureVersion}")
    implementation ("io.qameta.allure:allure-rest-assured:${allureVersion}")
    implementation ("io.qameta.allure:allure-java-commons:${allureVersion}")
    implementation ("io.qameta.allure:allure-selenide:${allureVersion}")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}