plugins {
    id("java")
    id("com.gradleup.shadow") version("8.3.3")
    kotlin("jvm")
}

group = "de.johannes"
version = "1.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation("net.sf.jopt-simple:jopt-simple:4.7")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "de.johannes.Main"
    }
}
kotlin {
    jvmToolchain(22)
}