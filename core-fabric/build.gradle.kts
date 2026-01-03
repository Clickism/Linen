/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

plugins {
    id("java")
    id("java-library")
    id("net.fabricmc.fabric-loom-remap") version "1.14-SNAPSHOT"
}

group = "de.clickism"
version = property("library_version").toString()

repositories {
    mavenCentral()
}

val minecraftVersion = stonecutter.current.version

dependencies {
    // Core
    implementation(project(":core"))
    // Adventure API (Not bundled with Fabric)
    implementation("net.kyori:adventure-api:4.25.0")
    implementation("net.kyori:adventure-text-minimessage:4.25.0")
    implementation("net.kyori:adventure-text-serializer-legacy:4.25.0")
    // Fabric
    modImplementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")
    minecraft("com.mojang:minecraft:${minecraftVersion}")
    mappings("net.fabricmc:yarn:${property("deps.yarn_mappings")}:v2")
    // Testing
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
    withJavadocJar()
}

tasks.test {
    useJUnitPlatform()
}

tasks.processResources {
    val properties = mapOf(
        "version" to version,
        "targetVersion" to minecraftVersion,
        "minecraftVersion" to minecraftVersion,
        "fabricVersion" to project.property("deps.fabric_loader"),
    )

    filesMatching("fabric.mod.json") {
        expand(properties)
    }
    inputs.properties(properties)
}