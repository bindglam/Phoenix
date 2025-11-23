package com.bindglam.phoenix.api.manager;

import com.bindglam.phoenix.api.item.PhoenixItem;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface ItemManager {
    boolean isPhoenixItem(@NotNull ItemStack itemStack);

    Optional<PhoenixItem> asPhoenixItem(@NotNull ItemStack itemStack);
}
