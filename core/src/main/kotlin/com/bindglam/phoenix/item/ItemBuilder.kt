package com.bindglam.phoenix.item

import com.bindglam.phoenix.api.item.PhoenixItem
import org.bukkit.inventory.ItemStack

object ItemBuilder {
    fun toItemStack(item: PhoenixItem): ItemStack {
        return item.properties().base().itemStack().clone().apply {
            itemMeta = itemMeta.apply {
                itemName(item.properties().itemName())
                lore(item.properties().lore())
                setRarity(item.properties().rarity())
            }
        }
    }
}