plugins {
    id("standard-convention")
    alias(libs.plugins.runTask.paper)
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(project(":core"))
}

val groupString = group.toString()
val versionString = version.toString()
val mcVersionString = property("minecraft_version").toString()

tasks {
    runServer {
        version(mcVersionString)

        downloadPlugins {
            modrinth("placeholderapi", "2.11.7")
        }
    }

    jar {
        finalizedBy(shadowJar)
    }

    shadowJar {
        archiveClassifier = ""

        dependencies {
            exclude(dependency("org.jetbrains:annotations:13.0")); exclude(dependency("org.jetbrains:annotations:23.0.0")); exclude(dependency("org.jetbrains:annotations:26.0.2"))
        }

        fun prefix(pattern: String) {
            relocate(pattern, "$groupString.shaded.$pattern")
        }
        prefix("kotlin")
        prefix("de.tr7zw.changeme.nbtapi")
        prefix("dev.jorel.commandapi")
    }
}