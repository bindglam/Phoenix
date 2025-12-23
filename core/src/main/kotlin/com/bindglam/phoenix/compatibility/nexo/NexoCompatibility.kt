package com.bindglam.phoenix.compatibility.nexo

import com.bindglam.phoenix.compatibility.ItemCompatibility
import com.bindglam.phoenix.util.logger
import com.nexomc.nexo.api.NexoItems
import org.bukkit.inventory.ItemStack

object NexoCompatibility : ItemCompatibility {
    override val requiredPlugin = "Nexo"

    override fun start() {
        logger.info("Nexo hook enabled!")
    }

    override fun end() {
    }

    override fun item(id: String): ItemStack? = NexoItems.itemFromId(id)?.build()
}