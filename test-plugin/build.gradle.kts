plugins {
    id("java")
    id("com.gradleup.shadow") version "9.3.0"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "de.clickism"
version = property("library_version").toString()

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    // Core
    implementation(project(":core"))
    implementation(project(":core-paper"))
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    // Testing
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.runServer {
    dependsOn(tasks.build)
    minecraftVersion("1.21.11")
}

tasks.shadowJar {
    archiveClassifier.set("")
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    mergeServiceFiles()
    enableAutoRelocation = true
    relocationPrefix = "shadow.de.clickism.linentestplugin"
}