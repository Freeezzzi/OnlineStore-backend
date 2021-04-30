import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.3"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.32"
    kotlin("plugin.spring") version "1.4.32"
}


group = "me.sasha"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    testImplementation(kotlin("test-junit"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.google.firebase:firebase-admin:7.1.0")
    implementation("org.xerial:sqlite-jdbc:3.34.0")
    implementation("io.github.microutils:kotlin-logging:2.0.6")
    implementation("org.springframework.boot:spring-boot-starter-logging")
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
    archiveFileName.set("backend.jar")
}

/*tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "com.onlinestore.ServerKt"
    }
}*/

/*
val fatJar = task("fatJar", type = Jar::class) {
    //duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    baseName = "${project.name}-fat"
    manifest {
        attributes["Implementation-Version"] = version
        attributes["Main-Class"] = "onlinestore.ServerKt"
        configurations["runtimeClasspath"].forEach { file: File ->
            from(zipTree(file.canonicalPath))
        }
    }
    /*from(configurations.runtimeClasspath.get().map({ if (it.isDirectory) it else zipTree(it.absolutePath) }))
    {
        exclude("META-INF/*.RSA", "META-INF/*.SF","META-INF/*.DSA")
    }*/*/*/*/
    with(tasks.jar.get() as CopySpec)
}*/

/*tasks{
    "build"{
        dependsOn(fatJar)
    }
}*/



