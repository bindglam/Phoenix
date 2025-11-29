package com.bindglam.phoenix.item.builder

import com.bindglam.phoenix.api.item.PhoenixItem
import com.bindglam.phoenix.api.registry.BuiltInRegistries
import com.bindglam.phoenix.item.attribute.AttributeApplier
import com.bindglam.phoenix.manager.ItemManagerImpl
import com.bindglam.phoenix.util.phoenix
import de.tr7zw.changeme.nbtapi.NBT
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack

object ItemBuilder {
    fun toItemStack(item: PhoenixItem): ItemStack {
        val attributes = item.attributes()

        val itemStack = item.properties().base().itemStack().clone().apply {
            itemMeta = itemMeta.apply meta@ {
                itemName(item.properties().itemName())

                val placeholders = AttributeApplier.applyAttributes(this@meta, item.attributes())
                lore(ItemManagerImpl.loreFormat!!.process placeholder@ { placeholder ->
                    val attribute = BuiltInRegistries.ATTRIBUTES.getOrThrow(placeholder.phoenix())

                    if(!attributes.containsKey(attribute)) return@placeholder null

                    return@placeholder placeholders[attribute]
                }.map { MiniMessage.miniMessage().deserialize(it).decoration(TextDecoration.ITALIC, false) }) // TODO : support PAPI

                setRarity(item.properties().rarity())

                if(item.properties().hideAttributes())
                    addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
        }

        NBT.modify(itemStack) { nbt ->
            AttributeApplier.saveInNBT(nbt, item.attributes())
        }

        return itemStack
    }
}