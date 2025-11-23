import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml

plugins {
    id("paper-convention")
    alias(libs.plugins.resourceFactory.paper)
}

repositories {
}

dependencies {
    implementation("de.tr7zw:item-nbt-api:2.15.3")
    implementation("dev.jorel:commandapi-paper-shade:11.0.0")

    implementation(project(":api"))
    /*rootProject.project("nms").subprojects.forEach {
        implementation(project(":nms:${it.name}"))
    }*/
}

paperPluginYaml {
    name = rootProject.name
    version = rootProject.version.toString()
    main = "$group.PhoenixPlugin"
    apiVersion = "1.21"
    author = "Bindglam"
    load = BukkitPluginYaml.PluginLoadOrder.STARTUP
}