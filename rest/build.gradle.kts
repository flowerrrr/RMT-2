import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.1.3"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}


dependencies {
    implementation(project(":core"))
    implementation(project(":db"))
    implementation("org.apache.commons:commons-lang3")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.jsonwebtoken:jjwt-api:0.12.1")

    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.1")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.1")
    runtimeOnly("com.mysql:mysql-connector-j:8.1.0")

    testImplementation(testFixtures(project(":db")))

    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-test-autoconfigure")
    testImplementation("org.springframework.security:spring-security-test")

    testRuntimeOnly("com.h2database:h2")
}

tasks.getByName<Jar>("jar") {
    val baseName = "das-tool-rest"
    archiveBaseName.set(baseName)
}

tasks.getByName<BootJar>("bootJar") {
    val baseName = "das-tool-rest"
    archiveBaseName.set(baseName)
    launchScript()
}

