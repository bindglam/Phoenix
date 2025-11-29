package com.bindglam.phoenix.item.attribute

import com.bindglam.phoenix.api.item.attribute.Attribute
import com.bindglam.phoenix.api.item.attribute.Codec
import com.bindglam.phoenix.api.item.builder.ItemBuilderContext
import com.bindglam.phoenix.util.createBaseAttributeModifier
import com.bindglam.phoenix.util.phoenix
import net.kyori.adventure.key.Key
import org.bukkit.attribute.AttributeModifier

class AttackSpeedAttribute : Attribute<Double, Double> {
    companion object {
        val KEY = "attack_speed".phoenix()
    }

    override fun key(): Key = KEY

    override fun codec(): Codec<Double, Double> = Codec.DOUBLE

    override fun apply(builder: ItemBuilderContext, data: Double) {
        builder.lore(listOf(String.format("%.1f", data)))

        builder.itemMeta { meta ->
            meta.addAttributeModifier(org.bukkit.attribute.Attribute.ATTACK_SPEED,
                createBaseAttributeModifier(org.bukkit.attribute.Attribute.ATTACK_SPEED, data-4.0, AttributeModifier.Operation.ADD_NUMBER))
        }
    }
}