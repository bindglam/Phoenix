import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml
import xyz.jpenilla.resourcefactory.paper.PaperPluginYaml

plugins {
    id("paper-convention")
    alias(libs.plugins.resourceFactory.paper)
}

repositories {
    maven("https://repo.nexomc.com/releases")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    implementation("de.tr7zw:item-nbt-api:2.15.5")
    implementation("dev.jorel:commandapi-paper-shade:11.1.0")

    implementation(project(":api"))
    /*rootProject.project("nms").subprojects.forEach {
        implementation(project(":nms:${it.name}"))
    }*/

    compileOnly("com.nexomc:nexo:1.16.1")
    compileOnly("me.clip:placeholderapi:2.11.7")
}

paperPluginYaml {
    name = rootProject.name
    version = rootProject.version.toString()
    main = "$group.PhoenixPluginImpl"
    apiVersion = "1.21"
    author = "Bindglam"
    load = BukkitPluginYaml.PluginLoadOrder.POSTWORLD
    dependencies {
        server(name = "Nexo", load = PaperPluginYaml.Load.BEFORE, required = false, joinClasspath = true)
        server(name = "PlaceholderAPI", load = PaperPluginYaml.Load.BEFORE, required = false, joinClasspath = true)
    }
}