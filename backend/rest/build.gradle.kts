plugins {
    war
    id("org.springframework.boot") version "3.1.3"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
}


dependencies {
    implementation(project(":core"))
//    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("joda-time:joda-time:2.12.5")
    implementation("joda-time:joda-time:2.12.5")
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//
//
//    developmentOnly("org.springframework.boot:spring-boot-devtools")
//    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
//
//    runtimeOnly("com.mysql:mysql-connector-j")
//
//    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
//
//    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
//
//    testImplementation(platform("org.junit:junit-bom:5.9.1"))
//    testImplementation("org.junit.jupiter:junit-jupiter")
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("org.springframework.boot:spring-boot-testcontainers")
//    testImplementation("org.springframework.security:spring-security-test")
//    testImplementation("org.testcontainers:junit-jupiter")
//    testImplementation("org.testcontainers:mysql")
}


