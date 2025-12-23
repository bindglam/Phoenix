package com.bindglam.phoenix.api.manager;

public interface ManagerBase {
    default void preload() {
    }

    void start();

    void end();
}
