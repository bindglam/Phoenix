package com.bindglam.phoenix.api.util;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public interface Codec<C, P> {
    Codec<Integer, Integer> INTEGER = new Codec<>() {
        @Override
        public @NotNull Integer fromPrimitive(@NotNull Integer data) {
            return data;
        }

        @Override
        public @NotNull Integer toPrimitive(@NotNull Integer data) {
            return data;
        }

        @Override
        public Class<Integer> complexType() {
            return Integer.class;
        }

        @Override
        public Class<Integer> primitiveType() {
            return Integer.class;
        }
    };


    @NotNull C fromPrimitive(@NotNull P data);

    @ApiStatus.Internal
    default @NotNull C fromPrimitiveObj(@NotNull Object data) {
        return fromPrimitive(primitiveType().cast(data));
    }

    @NotNull P toPrimitive(@NotNull C data);

    Class<C> complexType();

    Class<P> primitiveType();
}
