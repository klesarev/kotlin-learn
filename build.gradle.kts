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
val koin_version= "3.3.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlin:kotlin-test")

    implementation("io.qameta.allure:allure-junit5:2.21.0")


    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")
    implementation ("com.google.code.gson:gson:2.10")
    implementation ("io.rest-assured:rest-assured:5.3.0")

    implementation(platform("org.junit:junit-bom:5.9.1"))
    implementation ("org.junit.jupiter:junit-jupiter")

    implementation ("com.codeborne:selenide:6.9.0")

    // Koin Core features
    implementation ("io.insert-koin:koin-core:$koin_version")
    // Koin Test features
    testImplementation ("io.insert-koin:koin-test-junit5:$koin_version")

    runtimeOnly("org.slf4j:slf4j-simple:2.0.5")
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