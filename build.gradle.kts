import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "4.1.1" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
    kotlin("jvm") version "2.4.20"
    kotlin("plugin.spring") version "2.4.20" apply false
    kotlin("plugin.jpa") version "2.4.20" apply false
    kotlin("plugin.allopen") version "2.4.20" apply false
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
        compilerOptions {
            freeCompilerArgs.add("-Xjsr305=strict")
            jvmTarget.set(JvmTarget.JVM_17)
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




