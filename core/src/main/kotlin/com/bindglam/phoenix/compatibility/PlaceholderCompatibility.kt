package com.bindglam.phoenix.compatibility

import org.bukkit.OfflinePlayer

interface PlaceholderCompatibility : Compatibility {
    fun parse(player: OfflinePlayer?, string: String): String
}