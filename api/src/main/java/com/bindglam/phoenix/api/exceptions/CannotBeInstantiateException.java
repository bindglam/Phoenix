package com.bindglam.phoenix.api.exceptions;

public class CannotBeInstantiateException extends RuntimeException {
    public CannotBeInstantiateException() {
    }

    public CannotBeInstantiateException(String message) {
        super(message);
    }
}
