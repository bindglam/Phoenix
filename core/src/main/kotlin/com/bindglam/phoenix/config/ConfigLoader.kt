package com.bindglam.phoenix.config

import org.bukkit.configuration.ConfigurationSection

interface ConfigLoader<T> {
    fun load(config: ConfigurationSection): T
}