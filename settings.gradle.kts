pluginManagement {
    val redirectModule = mapOf(
        "dagger.hilt.android.plugin" to "com.google.dagger:hilt-android-gradle-plugin",
        "com.google.firebase.crashlytics" to "com.google.firebase:firebase-crashlytics-gradle"
    )

    resolutionStrategy {
        eachPlugin {
            if (requested.id.id in redirectModule) {
                useModule("${redirectModule[requested.id.id]}:${requested.version}")
            }
        }
    }

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://www.jitpack.io")
        maven(url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap"))
//        maven(url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev"))
    }
}
rootProject.name = "ComposeNavigationWithMultiModule"
include(":app")
include(":framework")
include(":core")
include(":core:domain")
include(":core:network")
include(":feature")
include(":feature:sign")
include(":feature:home")
