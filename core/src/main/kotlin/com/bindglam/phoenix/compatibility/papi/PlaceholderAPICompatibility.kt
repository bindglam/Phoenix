package com.bindglam.phoenix.compatibility.papi

import com.bindglam.phoenix.compatibility.PlaceholderCompatibility
import com.bindglam.phoenix.util.logger
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.OfflinePlayer

object PlaceholderAPICompatibility : PlaceholderCompatibility {
    override val requiredPlugin = "PlaceholderAPI"

    override fun start() {
        logger.info("PlaceholderAPI hook enabled!")
    }

    override fun end() {
    }

    override fun parse(player: OfflinePlayer?, string: String): String = PlaceholderAPI.setPlaceholders(player, string)
}