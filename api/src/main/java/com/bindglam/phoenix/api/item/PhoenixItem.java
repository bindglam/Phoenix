package com.bindglam.phoenix.api.item;

import com.bindglam.phoenix.api.item.attribute.Attribute;
import com.bindglam.phoenix.api.item.properties.PhoenixItemProperties;
import net.kyori.adventure.key.Keyed;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Map;

public interface PhoenixItem extends Keyed, ItemReference {
    PhoenixItemProperties properties();

    @Unmodifiable Map<Attribute<?, ?>, Object> attributes();

    <C, P> void putAttribute(Attribute<C, P> attribute, @NotNull C value);

    void removeAttribute(Attribute<?, ?> attribute);
}
