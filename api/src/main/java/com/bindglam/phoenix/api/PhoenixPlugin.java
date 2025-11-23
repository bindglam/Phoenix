package com.bindglam.phoenix.api;

import com.bindglam.phoenix.api.manager.ItemManager;
import org.jetbrains.annotations.NotNull;

public interface PhoenixPlugin {
    @NotNull ItemManager itemManager();
}
