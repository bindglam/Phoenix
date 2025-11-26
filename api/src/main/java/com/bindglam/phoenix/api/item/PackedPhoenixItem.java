package com.bindglam.phoenix.api.item;

import com.bindglam.phoenix.api.item.properties.PhoenixItemProperties;
import net.kyori.adventure.key.Keyed;
import org.jetbrains.annotations.NotNull;

public interface PackedPhoenixItem extends Keyed {
    @NotNull PhoenixItem unpack();

    @NotNull PhoenixItemProperties properties();
}
