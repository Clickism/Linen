/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

plugins {
    id("java")
    id("java-library")
    id("maven-publish")
    id("signing")
}

group = "de.clickism"
version = property("library_version").toString()

repositories {
    mavenCentral()
}

dependencies {
    compileOnlyApi("net.kyori:adventure-api:4.25.0")
    compileOnlyApi("net.kyori:adventure-text-minimessage:4.25.0")
    compileOnlyApi("net.kyori:adventure-text-serializer-legacy:4.25.0")
    // Annotations
    compileOnlyApi("org.jetbrains:annotations:24.0.0")
    // Testing
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = group.toString()
            artifactId = "linen-core"
            version = version.toString()
            pom {
                name.set("Linen")
                description.set("Server side development framework uniting Paper and Fabric.")
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