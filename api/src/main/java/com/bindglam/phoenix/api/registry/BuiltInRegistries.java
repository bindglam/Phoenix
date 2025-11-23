package com.bindglam.phoenix.api.registry;

import com.bindglam.phoenix.api.exceptions.CannotBeInstantiateException;
import com.bindglam.phoenix.api.item.PhoenixItem;
import com.bindglam.phoenix.api.item.attribute.Attribute;

public final class BuiltInRegistries {
    public static final Registry<PhoenixItem> ITEMS = of();
    public static final Registry<Attribute<?, ?>> ATTRIBUTES = of();

    private BuiltInRegistries() {
        throw new CannotBeInstantiateException();
    }

    private static <T> Registry<T> of() {
        return new ScalableRegistry<>();
    }
}
