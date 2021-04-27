import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.30"
    application
}

group = "me.sasha"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    //maven { url = uri("https://dl.bintray.com/kotlin/kotlinx") }
    //maven { url = uri("https://dl.bintray.com/kotlin/ktor") }
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    testImplementation(kotlin("test-junit"))
    //implementation("io.ktor:ktor-server-core:1.4.0")
    //implementation("io.ktor:ktor-server-netty:1.4.0")
    //implementation("io.ktor:ktor-html-builder:1.4.0")
    //implementation("org.kodein.di:kodein-di-framework-ktor-server-jvm:7.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
    //implementation("io.ktor:ktor-serialization:1.4.0")
    //added
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.xerial:sqlite-jdbc:3.34.0")
    implementation("io.github.microutils:kotlin-logging:2.0.6")
    implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("com.google.firebase:firebase-admin:7.1.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("com.j256.ormlite:ormlite-core:5.3")
    implementation("com.j256.ormlite:ormlite-jdbc:5.3")
}
tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName<Jar>("bootJar") {
    archiveFileName.set("onlinestore-backend.jar")
}

application {
    mainClassName = "ServerKt"
}