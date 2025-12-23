package com.bindglam.phoenix.manager

import com.bindglam.phoenix.api.manager.ManagerBase
import com.bindglam.phoenix.compatibility.Compatibility
import com.bindglam.phoenix.compatibility.nexo.NexoCompatibility
import com.bindglam.phoenix.compatibility.papi.PlaceholderAPICompatibility
import org.bukkit.Bukkit

object CompatibilityManager : ManagerBase {
    private val compatibilities = listOf(NexoCompatibility, PlaceholderAPICompatibility)

    private val enabledCompatibilities = arrayListOf<Compatibility>()

    override fun start() {
        compatibilities.forEach { compat ->
            if(Bukkit.getPluginManager().isPluginEnabled(compat.requiredPlugin)) {
                compat.start()

                enabledCompatibilities.add(compat)
            }
        }
    }

    override fun end() {
        enabledCompatibilities.forEach { it.end() }
    }

    fun isEnabled(compat: Compatibility) = enabledCompatibilities.contains(compat)

    fun <T : Compatibility> filterEnabled(clazz: Class<T>): T? = enabledCompatibilities.filterIsInstance(clazz).firstOrNull()
}