package com.bindglam.phoenix.item

import com.bindglam.phoenix.api.item.PhoenixItem
import com.bindglam.phoenix.api.item.attribute.Attribute
import com.bindglam.phoenix.api.item.properties.PhoenixItemProperties
import com.bindglam.phoenix.api.registry.BuiltInRegistries
import com.bindglam.phoenix.item.attribute.AttributeApplier
import com.bindglam.phoenix.item.builder.ItemBuilder
import de.tr7zw.changeme.nbtapi.NBT
import net.kyori.adventure.key.Key
import org.bukkit.inventory.ItemStack
import org.jetbrains.annotations.Unmodifiable
import java.util.*

class PhoenixItemImpl : PhoenixItem {
    private val key: Key
    private val properties: PhoenixItemProperties
    private val attributes: HashMap<Attribute<*, *>, Any>

    constructor(key: Key, properties: PhoenixItemProperties, attributes: HashMap<Attribute<*, *>, Any>) {
        this.key = key
        this.properties = properties
        this.attributes = attributes
    }

    constructor(itemStack: ItemStack) {
        val nbt = NBT.readNbt(itemStack)

        this.key = Key.key(nbt.getString("phoenix_key") ?: throw IllegalStateException("Not PhoenixItem"))
        this.properties = BuiltInRegistries.ITEMS.getOrThrow(key).properties()
        this.attributes = HashMap(AttributeApplier.loadFromNBT(nbt))
    }

    override fun key(): Key = key

    override fun properties(): PhoenixItemProperties = properties

    override fun attributes(): @Unmodifiable Map<Attribute<*, *>, Any> = Collections.unmodifiableMap(attributes)

    override fun <C : Any, P : Any> putAttribute(attribute: Attribute<C, P>, value: C) {
        attributes[attribute] = value
    }

    override fun removeAttribute(attribute: Attribute<*, *>) {
        attributes.remove(attribute)
    }

    override fun itemStack(): ItemStack = ItemBuilder.toItemStack(this)
}