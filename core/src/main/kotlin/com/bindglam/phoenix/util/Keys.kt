package com.bindglam.phoenix.util

import org.bukkit.NamespacedKey
import kotlin.text.count

fun String.minecraft(): NamespacedKey = NamespacedKey.fromString(this)!!
fun String.phoenix(): NamespacedKey = if(count { it == ':' } == 0) key("phoenix", this) else NamespacedKey.fromString(this)!!

fun key(namespace: String, value: String): NamespacedKey = NamespacedKey(namespace, value)