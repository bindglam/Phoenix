package com.bindglam.phoenix.util

import net.kyori.adventure.key.Key
import kotlin.text.count

fun String.minecraft(): Key = Key.key(this)
fun String.phoenix(): Key = if(count { it == ':' } == 0) key("phoenix", this) else Key.key(this)

fun key(namespace: String, value: String): Key = Key.key(namespace, value)