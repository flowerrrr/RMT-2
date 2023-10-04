import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
//	id("org.springframework.boot") version "3.1.3"
//	id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.8.22"
//	kotlin("plugin.spring") version "1.8.22"
//	kotlin("plugin.jpa") version "1.8.22"
}

group = "de.flower"
version = "0.0.1-SNAPSHOT"

//configurations {
//	compileOnly {
//		extendsFrom(configurations.annotationProcessor.get())
//	}
//}

//java {
//	sourceCompatibility = JavaVersion.VERSION_17
//}

allprojects {

    repositories {
        mavenCentral()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}

subprojects {

    apply(plugin = "java")

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}




