package com.bindglam.phoenix.item.attribute

import com.bindglam.phoenix.api.item.attribute.Attribute
import com.bindglam.phoenix.api.registry.BuiltInRegistries
import de.tr7zw.changeme.nbtapi.NBTType
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT
import de.tr7zw.changeme.nbtapi.iface.ReadableNBT
import net.kyori.adventure.key.Key
import kotlin.collections.set

object AttributeApplier {
    fun loadFromNBT(nbt: ReadableNBT): Map<Attribute<*, *>, Any> {
        val attributes = hashMapOf<Attribute<*, *>, Any>()

        val attributeContainer = nbt.getCompound("phoenix_attributes")!!

        attributeContainer.keys.map { BuiltInRegistries.ATTRIBUTES.getOrThrow(Key.key(it)) }.forEach { attribute ->
            val key = attribute.key().asString()

            val value = when(attributeContainer.getType(key)) {
                NBTType.NBTTagInt -> nbt.getInteger(key)
                else -> error("Unknown type")
            }

            attributes[attribute] = attribute.codec().fromPrimitiveObj(value)
        }

        return attributes
    }

    fun saveInNBT(nbt: ReadWriteNBT, attributes: Map<Attribute<*, *>, Any>) {
        val attributeContainer = nbt.getOrCreateCompound("phoenix_attributes").apply { clearNBT() }

        attributes.forEach { (attribute, value) ->
            val key = attribute.key().asString()

            when(value) {
                is Int -> attributeContainer.setInteger(key, value)
                else -> error("Unknown type")
            }
        }
    }
}