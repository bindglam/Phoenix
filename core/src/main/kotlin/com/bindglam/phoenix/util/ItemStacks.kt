package com.bindglam.phoenix.util

import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.inventory.EquipmentSlotGroup

fun createBaseAttributeModifier(attribute: Attribute, amount: Double, operation: AttributeModifier.Operation, slot: EquipmentSlotGroup = EquipmentSlotGroup.ANY) =
    AttributeModifier("base_${attribute.key().value()}".minecraft(), amount, operation, slot)