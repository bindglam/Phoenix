package com.bindglam.phoenix.util

import com.bindglam.phoenix.api.registry.ScalableRegistry
import io.papermc.paper.registry.RegistryAccess
import io.papermc.paper.registry.RegistryKey
import org.bukkit.Registry
import org.bukkit.inventory.ItemType

private val `field$ScalableRegistry$isLocked` = ScalableRegistry::class.java.getDeclaredField("isLocked").apply { isAccessible = true }

fun RegistryAccess.item(): Registry<ItemType> = getRegistry(RegistryKey.ITEM)

fun com.bindglam.phoenix.api.registry.Registry<*>.unlock() {
    `field$ScalableRegistry$isLocked`.set(this, false)
}