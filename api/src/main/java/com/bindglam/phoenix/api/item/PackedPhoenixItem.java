package com.bindglam.phoenix.api.item;

import com.bindglam.phoenix.api.item.attribute.Attribute;
import org.jetbrains.annotations.NotNull;

public interface PackedPhoenixItem extends PhoenixItem {
    @Override
    default <C, P> void putAttribute(Attribute<C, P> attribute, @NotNull C value) {
        throw new IllegalStateException();
    }

    @Override
    default void removeAttribute(Attribute<?, ?> attribute) {
        throw new IllegalStateException();
    }
}
