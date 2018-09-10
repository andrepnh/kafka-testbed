import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    java
    application
    id("com.github.johnrengelman.shadow") version("2.0.4")
}

group = "com.github.andrepnh"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

project.tasks.withType(Test::class.java) {
    maxParallelForks = (Runtime.getRuntime().availableProcessors() / 2).takeIf { it > 0 } ?: 1
    testLogging {
        events(TestLogEvent.STARTED ,
                TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.STANDARD_OUT, TestLogEvent.STANDARD_ERROR)
    }
}

dependencies {
    compile("org.apache.kafka:kafka-clients:2.0.0")
    compile("com.google.guava:guava:25.1-jre")
    compile("org.eclipse.collections:eclipse-collections-api:9.2.0")
    compile("org.eclipse.collections:eclipse-collections:9.2.0")
    compile("org.postgresql:postgresql:42.2.4")
    compile("org.apache.kafka:kafka-streams:2.0.0")
    compile("com.fasterxml.jackson.core:jackson-core:2.9.6")

    testCompile("junit", "junit", "4.12")
    testCompile("org.reflections", "reflections", "0.9.11")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_10
}

configure<ApplicationPluginConvention> {
    mainClassName = "com.github.andrepnh.kafka.playground.Main"
}