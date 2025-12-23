package com.bindglam.phoenix.compatibility.papi

import com.bindglam.phoenix.compatibility.PlaceholderCompatibility
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.OfflinePlayer

object PlaceholderAPICompatibility : PlaceholderCompatibility {
    override val requiredPlugin = "PlaceholderAPI"

    override fun start() {
    }

    override fun end() {
    }

    override fun parse(player: OfflinePlayer?, string: String): String = PlaceholderAPI.setPlaceholders(player, string)
}