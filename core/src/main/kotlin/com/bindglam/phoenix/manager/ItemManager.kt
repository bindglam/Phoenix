package com.bindglam.phoenix.manager

import com.bindglam.phoenix.api.item.PhoenixItem
import com.bindglam.phoenix.api.manager.ItemManager
import com.bindglam.phoenix.api.manager.ManagerBase
import com.bindglam.phoenix.api.registry.BuiltInRegistries
import com.bindglam.phoenix.api.registry.WritableRegistry
import com.bindglam.phoenix.item.PackedPhoenixItem
import com.bindglam.phoenix.item.PhoenixItemImpl
import com.bindglam.phoenix.item.properties.PropertiesLoader
import com.bindglam.phoenix.util.logger
import de.tr7zw.changeme.nbtapi.NBT
import de.tr7zw.changeme.nbtapi.NBTType
import net.kyori.adventure.key.Key
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.inventory.ItemStack
import java.io.File
import java.util.Optional

object ItemManager : ItemManager, ManagerBase {
    private val itemsFolder = File("plugins/Phoenix/items")

    override fun start() {
        val itemsRegistry = BuiltInRegistries.ITEMS as WritableRegistry<PhoenixItem>

        if(!itemsFolder.exists())
            itemsFolder.mkdirs()

        itemsFolder.listFiles().forEach { file ->
            val config = YamlConfiguration.loadConfiguration(file)

            config.getKeys(false).forEach { keyStr ->
                val key = Key.key(keyStr)

                val properties = PropertiesLoader.load(config.getConfigurationSection(keyStr)!!)

                itemsRegistry.register(key, PackedPhoenixItem(PhoenixItemImpl(key, properties)))
            }
        }

        itemsRegistry.lock()

        logger.info("${itemsRegistry.entries().size} items loaded!")
    }

    override fun end() {
    }

    override fun isPhoenixItem(itemStack: ItemStack): Boolean {
        return NBT.readNbt(itemStack).hasTag("phoenix_key", NBTType.NBTTagString)
    }

    override fun asPhoenixItem(itemStack: ItemStack): Optional<PhoenixItem> {
        return if(!isPhoenixItem(itemStack)) Optional.empty() else Optional.of(PhoenixItemImpl(itemStack))
    }
}