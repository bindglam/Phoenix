package com.bindglam.phoenix.util

import io.papermc.paper.registry.RegistryAccess
import io.papermc.paper.registry.RegistryKey
import org.bukkit.Registry
import org.bukkit.inventory.ItemType

fun RegistryAccess.item(): Registry<ItemType> = RegistryAccess.registryAccess().getRegistry(RegistryKey.ITEM)