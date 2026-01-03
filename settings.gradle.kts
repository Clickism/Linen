pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.kikugie.dev/snapshots")
        maven("https://maven.fabricmc.net/")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.7.11"
}

rootProject.name = "Linen"

include("core", "core-paper", "core-fabric", "test-mod", "test-plugin")

stonecutter {
    create(project(":core-fabric")) {
        versions("1.21.11")
    }
}