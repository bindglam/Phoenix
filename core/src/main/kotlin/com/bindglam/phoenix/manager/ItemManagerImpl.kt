package com.bindglam.phoenix.manager

import com.bindglam.phoenix.api.event.RegistryInitializeEvent
import com.bindglam.phoenix.api.item.PackedPhoenixItem
import com.bindglam.phoenix.api.item.PhoenixItem
import com.bindglam.phoenix.api.manager.ItemManager
import com.bindglam.phoenix.api.manager.Reloadable
import com.bindglam.phoenix.api.registry.BuiltInRegistries
import com.bindglam.phoenix.api.registry.WritableRegistry
import com.bindglam.phoenix.item.PhoenixItemImpl
import com.bindglam.phoenix.item.PhoenixItemLoader
import com.bindglam.phoenix.item.attribute.AttackDamageAttribute
import com.bindglam.phoenix.item.attribute.AttackSpeedAttribute
import com.bindglam.phoenix.item.attribute.DescriptionAttribute
import com.bindglam.phoenix.item.builder.LoreFormat
import com.bindglam.phoenix.item.builder.LoreFormatLoader
import com.bindglam.phoenix.util.copyToFile
import com.bindglam.phoenix.util.logger
import com.bindglam.phoenix.util.resource
import com.bindglam.phoenix.util.unlock
import de.tr7zw.changeme.nbtapi.NBT
import de.tr7zw.changeme.nbtapi.NBTType
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.inventory.ItemStack
import java.io.File
import java.util.*

object ItemManagerImpl : ItemManager, Reloadable {
    private val itemsFolder = File("plugins/Phoenix/items")
    private val loreFormatFile = File("plugins/Phoenix/lore-format.yml")

    var loreFormat: LoreFormat? = null

    override fun start() {
        val itemsRegistry = BuiltInRegistries.ITEMS as WritableRegistry<PackedPhoenixItem>

        registerDefaultAttributes()

        if(!itemsFolder.exists())
            itemsFolder.mkdirs()
        if(!loreFormatFile.exists()) {
            loreFormatFile.createNewFile()

            resource("lore-format.yml")!!.copyToFile(loreFormatFile)
        }

        loreFormat = LoreFormatLoader.load(YamlConfiguration.loadConfiguration(loreFormatFile))

        itemsFolder.listFiles().forEach { file ->
            val config = YamlConfiguration.loadConfiguration(file)

            config.getKeys(false).forEach { keyStr ->
                val item = PhoenixItemLoader.load(config.getConfigurationSection(keyStr)!!)

                itemsRegistry.register(item.key(), item)
            }
        }

        itemsRegistry.lock()

        logger.info("${itemsRegistry.entries().size} items loaded!")
    }

    override fun end() {
        val itemsRegistry = BuiltInRegistries.ITEMS as WritableRegistry<PackedPhoenixItem>

        BuiltInRegistries.ATTRIBUTES.unlock()
        BuiltInRegistries.ATTRIBUTES.clear()

        itemsRegistry.unlock()
        itemsRegistry.clear()
    }

    private fun registerDefaultAttributes() {
        BuiltInRegistries.ATTRIBUTES.register(DescriptionAttribute.KEY, DescriptionAttribute())
        BuiltInRegistries.ATTRIBUTES.register(AttackDamageAttribute.KEY, AttackDamageAttribute())
        BuiltInRegistries.ATTRIBUTES.register(AttackSpeedAttribute.KEY, AttackSpeedAttribute())

        RegistryInitializeEvent().callEvent()
    }

    override fun isPhoenixItem(itemStack: ItemStack): Boolean {
        return NBT.readNbt(itemStack).hasTag("phoenix_key", NBTType.NBTTagString)
    }

    override fun asPhoenixItem(itemStack: ItemStack): Optional<PhoenixItem> {
        return if(!isPhoenixItem(itemStack)) Optional.empty() else Optional.of(PhoenixItemImpl(itemStack))
    }
}