package com.bindglam.phoenix.util

import com.bindglam.phoenix.api.Phoenix
import com.bindglam.phoenix.api.PhoenixPlugin
import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Logger

fun PhoenixPlugin.plugin() = this as JavaPlugin

val logger: Logger
    get() = Phoenix.instance().plugin().logger