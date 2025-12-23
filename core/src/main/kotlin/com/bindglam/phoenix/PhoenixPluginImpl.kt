package com.bindglam.phoenix

import com.bindglam.phoenix.api.Phoenix
import com.bindglam.phoenix.api.PhoenixPlugin
import com.bindglam.phoenix.api.event.RegistryInitializeEvent
import com.bindglam.phoenix.api.manager.Reloadable
import com.bindglam.phoenix.manager.CommandManager
import com.bindglam.phoenix.manager.CompatibilityManager
import com.bindglam.phoenix.manager.ItemManagerImpl
import org.bukkit.plugin.java.JavaPlugin
import de.tr7zw.changeme.nbtapi.NBT
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.server.ServerLoadEvent

class PhoenixPluginImpl : JavaPlugin(), PhoenixPlugin {
    private val managers = listOf(
        CompatibilityManager,
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

        managers.forEach { it.preload() }

        server.pluginManager.registerEvents(object : Listener {
            @EventHandler
            fun ServerLoadEvent.startServices() {
                RegistryInitializeEvent().callEvent()

                managers.forEach { it.start() }
            }
        }, this)
    }

    override fun onDisable() {
        managers.forEach { it.end() }
    }

    override fun reload() {
        managers.filterIsInstance<Reloadable>().forEach { it.reload() }
    }

    override fun itemManager() = ItemManagerImpl
}