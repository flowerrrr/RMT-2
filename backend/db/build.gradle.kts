plugins {
    id("org.springframework.boot") version "3.1.3"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    runtimeOnly("org.jetbrains.kotlin:kotlin-reflect")

    testRuntimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation(platform("org.junit:junit-bom:5.9.1"))
//    testImplementation("org.junit.jupiter:junit-jupiter")
//    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

