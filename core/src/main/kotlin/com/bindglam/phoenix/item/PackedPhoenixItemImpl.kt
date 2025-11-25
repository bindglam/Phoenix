package com.bindglam.phoenix.item

import com.bindglam.phoenix.api.item.PackedPhoenixItem
import com.bindglam.phoenix.api.item.PhoenixItem
import com.bindglam.phoenix.api.item.attribute.Attribute
import com.bindglam.phoenix.api.item.properties.PhoenixItemProperties
import net.kyori.adventure.key.Key
import org.bukkit.inventory.ItemStack
import org.jetbrains.annotations.Unmodifiable

class PackedPhoenixItemImpl(private val item: PhoenixItem) : PackedPhoenixItem {
    override fun key(): Key = item.key()

    override fun properties(): PhoenixItemProperties = item.properties()

    override fun attributes(): @Unmodifiable Map<Attribute<*, *>, Any> = item.attributes()

    override fun itemStack(): ItemStack = item.itemStack()
}