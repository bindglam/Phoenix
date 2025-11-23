package com.bindglam.phoenix.api.item;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public interface ItemReference {
    @NotNull ItemStack itemStack();

    static @NotNull ItemReference wrap(ItemStack itemStack) {
        return () -> itemStack;
    }
}
