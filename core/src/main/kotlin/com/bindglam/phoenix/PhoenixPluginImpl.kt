package com.bindglam.phoenix

import com.bindglam.phoenix.api.Phoenix
import com.bindglam.phoenix.api.PhoenixPlugin
import com.bindglam.phoenix.api.manager.Reloadable
import com.bindglam.phoenix.manager.CommandManager
import com.bindglam.phoenix.manager.ItemManagerImpl
import de.tr7zw.changeme.nbtapi.NBT
import org.bukkit.plugin.java.JavaPlugin

class PhoenixPluginImpl : JavaPlugin(), PhoenixPlugin {
    private val managers = listOf(
        ItemManagerImpl,
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

    override fun reload() {
        managers.filterIsInstance<Reloadable>().forEach { it.reload() }
    }

    override fun itemManager() = ItemManagerImpl
}