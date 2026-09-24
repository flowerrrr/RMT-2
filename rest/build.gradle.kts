import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}


dependencies {
    implementation(project(":core"))
    implementation(project(":db"))
    implementation("org.apache.commons:commons-lang3")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("io.jsonwebtoken:jjwt-api:0.13.0")

    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.13.0")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.13.0")
    runtimeOnly("com.mysql:mysql-connector-j")

    testImplementation(testFixtures(project(":db")))

    testImplementation("tools.jackson.module:jackson-module-kotlin")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-test-autoconfigure")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation("org.springframework.boot:spring-boot-starter-security-test")

    testRuntimeOnly("com.h2database:h2")
}

tasks.getByName<Jar>("jar") {
    val baseName = "das-tool-rest"
    archiveBaseName.set(baseName)
}

tasks.getByName<BootJar>("bootJar") {
    val baseName = "das-tool-rest"
    archiveBaseName.set(baseName)
}

