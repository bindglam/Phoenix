package com.bindglam.phoenix.compatibility

import org.bukkit.inventory.ItemStack

interface ItemCompatibility : Compatibility {
    fun item(id: String): ItemStack?
}