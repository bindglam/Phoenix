package com.bindglam.phoenix.manager

import com.bindglam.phoenix.api.Phoenix
import com.bindglam.phoenix.api.manager.ManagerBase
import com.bindglam.phoenix.api.registry.BuiltInRegistries
import com.bindglam.phoenix.util.plugin
import dev.jorel.commandapi.CommandAPI
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandAPIPaperConfig
import dev.jorel.commandapi.CommandPermission
import dev.jorel.commandapi.arguments.EntitySelectorArgument
import dev.jorel.commandapi.arguments.IntegerArgument
import dev.jorel.commandapi.arguments.NamespacedKeyArgument
import dev.jorel.commandapi.executors.CommandExecutor
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player

object CommandManager : ManagerBase {
    override fun preload() {
        CommandAPI.onLoad(CommandAPIPaperConfig(Phoenix.instance().plugin()).silentLogs(true))

        CommandAPICommand("phoenix")
            .withPermission(CommandPermission.OP)
            .withSubcommands(
                CommandAPICommand("give")
                    .withArguments(EntitySelectorArgument.OnePlayer("player"), NamespacedKeyArgument("key"), IntegerArgument("amount", 0).setOptional(true))
                    .executes(CommandExecutor { sender, args ->
                        val player = args["player"] as Player
                        val key = args["key"] as NamespacedKey
                        val amount = (args["amount"] as Int?) ?: 1

                        BuiltInRegistries.ITEMS.get(key).ifPresentOrElse({ item ->
                            player.inventory.addItem(item.unpack().itemStack().apply { this.amount = amount })

                            sender.sendMessage(Component.text("Successfully gave an item").color(NamedTextColor.GREEN))
                        }, {
                            sender.sendMessage(Component.text("Unknown item").color(NamedTextColor.RED))
                        })
                    }),

                CommandAPICommand("reload")
                    .executes(CommandExecutor { sender, _ ->
                        sender.sendMessage(Component.text("Reloading").color(NamedTextColor.YELLOW))

                        Phoenix.instance().reload()

                        sender.sendMessage(Component.text("Successfully reloaded!").color(NamedTextColor.GREEN))
                    })
            )
            .register()

        CommandAPI.onEnable()
    }

    override fun start() {
    }

    override fun end() {
        CommandAPI.onDisable()
    }
}