/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

plugins {
    id("java")
    id("maven-publish")
    id("signing")
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
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    // Testing
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = group.toString()
            artifactId = "linen-core-paper"
            version = "${version}"
            pom {
                name.set("Linen")
                description.set("Paper implementation of the server side development framework Linen.")
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