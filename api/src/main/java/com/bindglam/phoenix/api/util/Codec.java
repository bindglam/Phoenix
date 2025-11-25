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

    Codec<Long, Long> LONG = new Codec<>() {
        @Override
        public @NotNull Long fromPrimitive(@NotNull Long data) {
            return data;
        }

        @Override
        public @NotNull Long toPrimitive(@NotNull Long data) {
            return data;
        }

        @Override
        public Class<Long> complexType() {
            return Long.class;
        }

        @Override
        public Class<Long> primitiveType() {
            return Long.class;
        }
    };

    Codec<Float, Float> FLOAT = new Codec<>() {
        @Override
        public @NotNull Float fromPrimitive(@NotNull Float data) {
            return data;
        }

        @Override
        public @NotNull Float toPrimitive(@NotNull Float data) {
            return data;
        }

        @Override
        public Class<Float> complexType() {
            return Float.class;
        }

        @Override
        public Class<Float> primitiveType() {
            return Float.class;
        }
    };

    Codec<Double, Double> DOUBLE = new Codec<>() {
        @Override
        public @NotNull Double fromPrimitive(@NotNull Double data) {
            return data;
        }

        @Override
        public @NotNull Double toPrimitive(@NotNull Double data) {
            return data;
        }

        @Override
        public Class<Double> complexType() {
            return Double.class;
        }

        @Override
        public Class<Double> primitiveType() {
            return Double.class;
        }
    };

    Codec<String, String> STRING = new Codec<>() {
        @Override
        public @NotNull String fromPrimitive(@NotNull String data) {
            return data;
        }

        @Override
        public @NotNull String toPrimitive(@NotNull String data) {
            return data;
        }

        @Override
        public Class<String> complexType() {
            return String.class;
        }

        @Override
        public Class<String> primitiveType() {
            return String.class;
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
