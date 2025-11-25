package com.bindglam.phoenix.item.builder

import com.bindglam.phoenix.config.ConfigLoader
import org.bukkit.configuration.ConfigurationSection
import java.util.LinkedList

object LoreFormatLoader : ConfigLoader<LoreFormat> {
    override fun load(config: ConfigurationSection): LoreFormat = LoreFormat(LinkedList(config.getStringList("lore")))
}