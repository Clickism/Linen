pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.kikugie.dev/snapshots")
        maven("https://maven.fabricmc.net/")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.8.2"
}

rootProject.name = "Linen"

include(
    "core:api",
    "core:paper",
    "core:fabric",
    "test-mod",
    "test-plugin"
)

stonecutter {
    create(project(":core:fabric")) {
        versions("1.21.11", "1.21.10", "1.21.8", "1.21.5", "1.21.4", "1.21.1")
        vcsVersion = "1.21.11"
    }
}