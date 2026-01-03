/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

plugins {
    id("java")
    id("java-library")
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