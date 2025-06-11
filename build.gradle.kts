plugins {
    id("java")
    id("io.github.goooler.shadow") version "8.1.8"
}

group = "rip.diamond"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/") //Velocity
}

dependencies {
    compileOnly("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.4.0-SNAPSHOT")

    implementation("io.github.revxrsal:lamp.common:4.0.0-rc.12")
    implementation("io.github.revxrsal:lamp.velocity:4.0.0-rc.12")
    implementation("io.github.revxrsal:lamp.brigadier:4.0.0-rc.12")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
}

tasks.withType<JavaCompile> {
    // Preserve parameter names in the bytecode, needed in Lamp
    options.compilerArgs.add("-parameters")
}
