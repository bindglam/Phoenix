package com.bindglam.phoenix.item.attribute

import com.bindglam.phoenix.api.item.attribute.Attribute
import com.bindglam.phoenix.api.item.builder.ItemBuilderContext
import com.bindglam.phoenix.api.registry.BuiltInRegistries
import de.tr7zw.changeme.nbtapi.NBTType
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT
import de.tr7zw.changeme.nbtapi.iface.ReadableNBT
import net.kyori.adventure.key.Key
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ItemType
import org.bukkit.inventory.meta.ItemMeta
import java.util.function.Consumer

object AttributeApplier {
    fun loadFromNBT(nbt: ReadableNBT): Map<Attribute<*, *>, Any> {
        val attributes = hashMapOf<Attribute<*, *>, Any>()

        val attributeContainer = nbt.getCompound("phoenix_attributes")!!

        attributeContainer.keys.map { BuiltInRegistries.ATTRIBUTES.getOrThrow(Key.key(it)) }.forEach { attribute ->
            val key = attribute.key().asString()

            val value = when(attributeContainer.getType(key)) {
                NBTType.NBTTagInt -> nbt.getInteger(key)
                NBTType.NBTTagLong -> nbt.getLong(key)
                NBTType.NBTTagFloat -> nbt.getFloat(key)
                NBTType.NBTTagDouble -> nbt.getDouble(key)
                NBTType.NBTTagString -> nbt.getString(key)
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

            when(val rawValue = attribute.codec().toPrimitiveObj(value)) {
                is Int -> attributeContainer.setInteger(key, rawValue)
                is Long -> attributeContainer.setLong(key, rawValue)
                is Float -> attributeContainer.setFloat(key, rawValue)
                is Double -> attributeContainer.setDouble(key, rawValue)
                is String -> attributeContainer.setString(key, rawValue)
                else -> error("Unknown type")
            }
        }
    }

    fun applyAttributes(stack: ItemStack, itemMeta: ItemMeta, attributes: Map<Attribute<*, *>, Any>): Map<Attribute<*, *>, String> {
        val lore = hashMapOf<Attribute<*, *>, String>()

        attributes.forEach { (attribute, value) ->
            attribute.applyObj(object : ItemBuilderContext {
                override fun type(): ItemType = stack.type.asItemType()!!

                override fun itemMeta(consumer: Consumer<ItemMeta>) {
                    consumer.accept(itemMeta)
                }

                override fun lore(l: List<String>) {
                    lore[attribute] = l.joinToString(" ")
                }
            }, value)
        }

        return lore
    }
}