package com.bindglam.phoenix.api.item.builder;

import org.bukkit.inventory.ItemType;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.function.Consumer;

public interface ItemBuilderContext {
    ItemType type();

    void itemMeta(Consumer<ItemMeta> consumer);

    void lore(List<String> lore);
}
