package com.bindglam.phoenix.item

import com.bindglam.phoenix.api.item.PackedPhoenixItem
import com.bindglam.phoenix.api.item.attribute.Attribute
import com.bindglam.phoenix.api.registry.BuiltInRegistries
import com.bindglam.phoenix.config.ConfigLoader
import com.bindglam.phoenix.item.properties.PropertiesLoader
import com.bindglam.phoenix.util.phoenix
import net.kyori.adventure.key.Key
import org.bukkit.configuration.ConfigurationSection

object PhoenixItemLoader : ConfigLoader<PackedPhoenixItem> {
    override fun load(config: ConfigurationSection): PackedPhoenixItem {
        val key = Key.key(config.name)
        val properties = PropertiesLoader.load(config)

        val attributes = hashMapOf<Attribute<*, *>, () -> Any>()
        config.getConfigurationSection("attributes")!!.also { attributesConfig ->
            attributesConfig.getKeys(false).forEach { key ->
                attributes[BuiltInRegistries.ATTRIBUTES.getOrThrow(key.phoenix())] = { attributesConfig.get(key)!! }
            }
        }

        return PackedPhoenixItemImpl(key, properties) { PhoenixItemImpl(key, properties, attributes.mapValues { (_, supplier) -> supplier() }) }
    }
}