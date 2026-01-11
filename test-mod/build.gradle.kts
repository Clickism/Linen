/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

plugins {
    id("java")
    id("net.fabricmc.fabric-loom-remap") version "1.14-SNAPSHOT"
}

group = "de.clickism"
version = property("library_version").toString()

repositories {
    mavenCentral()
}

val minecraftVersion = "1.21.11"

dependencies {
    // Core
    implementation(include(project(":core:api"))!!)
    implementation(include(project(path = ":core:fabric:${minecraftVersion}", configuration = "namedElements"))!!)
    implementation(include("net.kyori:adventure-api:4.25.0")!!)
    implementation(include("net.kyori:adventure-text-minimessage:4.25.0")!!)
    implementation(include("net.kyori:adventure-text-serializer-legacy:4.25.0")!!)
    // Fabric
    modImplementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")
    minecraft("com.mojang:minecraft:${minecraftVersion}")
    mappings(loom.officialMojangMappings())
    // Testing
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.compileJava {
    dependsOn(":core-fabric:${minecraftVersion}:build")
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

configurations.all {
    resolutionStrategy {
        preferProjectModules()
        cacheChangingModulesFor(0, "seconds")
    }
}