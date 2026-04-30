pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "IOSLauncher"
include(":app")
include(":core-ui")
include(":feature-home")
include(":feature-widget")
include(":feature-controlcenter")
include(":feature-player")
include(":feature-applibrary")
