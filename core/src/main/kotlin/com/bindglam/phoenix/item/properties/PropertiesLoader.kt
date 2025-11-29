package com.bindglam.phoenix.item.properties

import com.bindglam.phoenix.api.item.ItemReference
import com.bindglam.phoenix.api.item.properties.PhoenixItemProperties
import com.bindglam.phoenix.config.ConfigLoader
import com.bindglam.phoenix.util.item
import com.bindglam.phoenix.util.minecraft
import io.papermc.paper.registry.RegistryAccess
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.inventory.ItemRarity

object PropertiesLoader : ConfigLoader<PhoenixItemProperties> {
    override fun load(config: ConfigurationSection): PhoenixItemProperties = PhoenixItemProperties.builder()
        .base(ItemReference.wrap(RegistryAccess.registryAccess().item().getOrThrow(config.getString("base")!!.minecraft()).createItemStack()))
        .itemName(config.getString("item-name")?.let { MiniMessage.miniMessage().deserialize(it) })
        .rarity(config.getString("rarity")?.let { ItemRarity.valueOf(it) })
        .hideAttributes(config.getBoolean("hide-attributes", true))
        .build()
}