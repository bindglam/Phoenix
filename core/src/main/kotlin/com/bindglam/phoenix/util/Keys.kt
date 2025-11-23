package com.bindglam.phoenix.util

import net.kyori.adventure.key.Key

fun String.toKey(): Key = Key.key(this)