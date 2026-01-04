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
    mappings(loom.officialMojangMappings())
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

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = group.toString()
            artifactId = "linen-core-fabric"
            version = "${version}+${minecraftVersion}"
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