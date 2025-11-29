package com.bindglam.phoenix.item.builder

import com.bindglam.phoenix.api.item.PhoenixItem
import com.bindglam.phoenix.api.item.builder.ItemBuilderConsumer
import com.bindglam.phoenix.api.registry.BuiltInRegistries
import com.bindglam.phoenix.item.attribute.AttributeApplier
import com.bindglam.phoenix.manager.ItemManagerImpl
import com.bindglam.phoenix.util.phoenix
import de.tr7zw.changeme.nbtapi.NBT
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.function.Consumer

object ItemBuilder {
    fun toItemStack(item: PhoenixItem): ItemStack {
        val itemStack = item.properties().base().itemStack().clone().apply {
            itemMeta = itemMeta.apply meta@ {
                itemName(item.properties().itemName())

                lore(ItemManagerImpl.loreFormat!!.process placeholder@ { placeholder ->
                    val attributes = item.attributes()
                    val attribute = BuiltInRegistries.ATTRIBUTES.getOrThrow(placeholder.phoenix())

                    if(!attributes.contains(attribute)) return@placeholder null

                    val result = StringBuilder()

                    attribute.applyObj(object : ItemBuilderConsumer {
                        override fun itemMeta(consumer: Consumer<ItemMeta>) {
                            consumer.accept(this@meta)
                        }

                        override fun lore(l: List<String>) {
                            l.forEach { result.append(it) }
                        }
                    }, attributes[attribute])

                    return@placeholder result.toString()
                }.map { MiniMessage.miniMessage().deserialize(it).decoration(TextDecoration.ITALIC, false) }) // TODO : support PAPI

                setRarity(item.properties().rarity())

                addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
            }
        }

        NBT.modify(itemStack) { nbt ->
            AttributeApplier.saveInNBT(nbt, item.attributes())
        }

        return itemStack
    }
}