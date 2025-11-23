package com.bindglam.phoenix

import com.bindglam.phoenix.api.Phoenix
import com.bindglam.phoenix.api.PhoenixPlugin
import com.bindglam.phoenix.manager.CommandManager
import com.bindglam.phoenix.manager.ItemManager
import de.tr7zw.changeme.nbtapi.NBT
import org.bukkit.plugin.java.JavaPlugin

class PhoenixPlugin : JavaPlugin(), PhoenixPlugin {
    private val managers = listOf(
        ItemManager,
        CommandManager
    )

    override fun onEnable() {
        Phoenix.register(this)

        if(!NBT.preloadApi()) {
            logger.severe("NBT-API wasn't initialized properly, disabling the plugin")
            server.pluginManager.disablePlugin(this)
            return
        }

        managers.forEach { it.start() }
    }

    override fun onDisable() {
        managers.forEach { it.end() }
    }

    override fun itemManager() = ItemManager
}