package com.bindglam.phoenix.item.properties

import com.bindglam.phoenix.api.item.properties.PhoenixItemProperties
import com.bindglam.phoenix.compatibility.ItemCompatibility
import com.bindglam.phoenix.compatibility.PlaceholderCompatibility
import com.bindglam.phoenix.config.ConfigLoader
import com.bindglam.phoenix.manager.CompatibilityManager
import com.bindglam.phoenix.util.item
import com.bindglam.phoenix.util.minecraft
import io.papermc.paper.registry.RegistryAccess
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.inventory.ItemRarity

object PropertiesLoader : ConfigLoader<PhoenixItemProperties> {
    override fun load(config: ConfigurationSection): PhoenixItemProperties = PhoenixItemProperties.builder()
        .base(config.getString("base")!!.let { base ->
            {
                RegistryAccess.registryAccess().item().get(base.minecraft())?.createItemStack()
                    ?: CompatibilityManager.filterEnabled(ItemCompatibility::class.java)?.item(base)
                    ?: error("Unable load to base")
            }
        })
        .itemName(config.getString("item-name")?.let { itemName ->
            {
                CompatibilityManager.filterEnabled(PlaceholderCompatibility::class.java)?.parse(null, itemName)
                    ?.let { input -> MiniMessage.miniMessage().deserialize(input) }
            }
        })
        .rarity(config.getString("rarity")?.let { ItemRarity.valueOf(it) })
        .hideAttributes(config.getBoolean("hide-attributes", true))
        .build()
}