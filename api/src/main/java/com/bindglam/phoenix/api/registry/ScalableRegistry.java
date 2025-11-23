package com.bindglam.phoenix.api.registry;

import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class ScalableRegistry<T> implements WritableRegistry<T> {
    private final Map<Key, T> map;

    private boolean isLocked = false;

    ScalableRegistry() {
        this.map = new HashMap<>();
    }

    ScalableRegistry(Map<Key, T> map) {
        this.map = new HashMap<>(map);
    }

    @Override
    public @NotNull Optional<T> get(@NotNull Key key) {
        return Optional.ofNullable(map.get(key));
    }

    @Override
    public @NotNull @Unmodifiable Set<Map.Entry<Key, T>> entries() {
        return map.entrySet();
    }

    @Override
    public void register(@NotNull Key key, @NotNull T value) {
        if(isLocked)
            throw new IllegalStateException("Locked!");
        if(map.containsKey(key))
            throw new IllegalStateException(key.asString() + "is already registered");
        map.put(key, value);
    }

    @Override
    public void clear() {
        if(isLocked)
            throw new IllegalStateException("Locked!");
        map.clear();
    }

    @Override
    public void lock() {
        isLocked = true;
    }
}
