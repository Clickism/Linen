/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

plugins {
    id("java")
    id("java-library")
    id("net.fabricmc.fabric-loom-remap") version "1.14-SNAPSHOT"
    id("maven-publish")
    id("signing")
}

val libraryVersion = property("library_version").toString()
val minecraftVersion = stonecutter.current.version

group = "de.clickism"
version = "${libraryVersion}+${minecraftVersion}"

repositories {
    mavenCentral()
}

base {
    archivesName.set("linen-core-fabric")
}

dependencies {
    // Core
    implementation(project(":core:api"))
    // Fabric
    modImplementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")
    minecraft("com.mojang:minecraft:${minecraftVersion}")
    mappings(loom.officialMojangMappings())
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
    withJavadocJar()
}

tasks.processResources {
    val properties = mapOf(
        // Mod version
        "version" to version,
        // Supported Minecraft versions, e.g. ">=1.20.1"
        "supportedVersions" to project.property("mod.supported_versions").toString(),
        // Target Minecraft version
        "minecraftVersion" to minecraftVersion,
        // Fabric Loader version
        "fabricLoaderVersion" to project.property("deps.fabric_loader"),
    )

    filesMatching("fabric.mod.json") {
        expand(properties)
    }
    inputs.properties(properties)
}

stonecutter {
    replacements {
        string(current.parsed < "1.21.11") {
            replace("Identifier", "ResourceLocation")
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = group.toString()
            artifactId = "linen-core-fabric"
            version = version.toString()
            pom {
                name.set("Linen")
                description.set("Fabric implementation of the server side development framework Linen.")
                url.set("https://github.com/Clickism/Linen")
                licenses {
                    license {
                        name.set("GNU General Public License v3.0")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.html")
                    }
                }
                developers {
                    developer {
                        id.set("Clickism")
                        name.set("Clickism")
                        email.set("dev@clickism.de")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/Clickism/Configured.git")
                    developerConnection.set("scm:git:ssh://github.com/Clickism/Configured.git")
                    url.set("https://github.com/Clickism/Configured")
                }
            }
        }
    }
    signing {
        sign(publishing.publications["mavenJava"])
    }
}