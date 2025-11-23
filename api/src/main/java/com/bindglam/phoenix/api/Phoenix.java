package com.bindglam.phoenix.api;

import com.bindglam.phoenix.api.exceptions.CannotBeInstantiateException;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public final class Phoenix {
    private static @Nullable PhoenixPlugin plugin = null;

    private Phoenix() {
        throw new CannotBeInstantiateException();
    }

    public static @NotNull PhoenixPlugin instance() {
        return Objects.requireNonNull(plugin, "Failed to get PhoenixPlugin instance");
    }

    @ApiStatus.Internal
    public static void register(@NotNull PhoenixPlugin plugin) {
        if(Phoenix.plugin != null)
            throw new IllegalStateException("Already registered");
        Phoenix.plugin = plugin;
    }
}
