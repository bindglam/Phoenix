package com.bindglam.phoenix.item

import com.bindglam.phoenix.api.item.PackedPhoenixItem
import com.bindglam.phoenix.api.item.PhoenixItem
import com.bindglam.phoenix.api.item.properties.PhoenixItemProperties
import net.kyori.adventure.key.Key

class PackedPhoenixItemImpl(private val key: Key, private val properties: PhoenixItemProperties, private val supplier: () -> PhoenixItem) : PackedPhoenixItem {
    override fun unpack(): PhoenixItem = supplier()

    override fun key(): Key = key

    override fun properties(): PhoenixItemProperties = properties
}