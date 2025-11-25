package com.bindglam.phoenix.api.item.builder;

import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.function.Consumer;

public interface ItemBuilderConsumer {
    void itemMeta(Consumer<ItemMeta> consumer);

    void lore(List<String> lore);
}
