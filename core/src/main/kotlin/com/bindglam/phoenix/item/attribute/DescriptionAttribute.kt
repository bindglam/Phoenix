package com.bindglam.phoenix.item.attribute

import com.bindglam.phoenix.api.item.builder.ItemBuilderConsumer
import com.bindglam.phoenix.api.item.attribute.Attribute
import com.bindglam.phoenix.api.item.attribute.Codec
import com.bindglam.phoenix.util.phoenix
import net.kyori.adventure.key.Key

class DescriptionAttribute : Attribute<String, String> {
    companion object {
        val KEY = "description".phoenix()
    }

    override fun key(): Key = KEY

    override fun codec(): Codec<String, String> = Codec.STRING

    override fun apply(builder: ItemBuilderConsumer, data: String) {
        builder.lore(listOf(data))
    }
}