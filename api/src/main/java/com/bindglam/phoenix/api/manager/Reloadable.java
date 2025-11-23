package com.bindglam.phoenix.api.manager;

public interface Reloadable extends ManagerBase {
    default void reload() {
        end();

        start();
    }
}
