package com.bindglam.phoenix.api.item.properties;

import com.bindglam.phoenix.api.item.ItemReference;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record PhoenixItemProperties(@NotNull ItemReference base, Component itemName) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ItemReference base;

        private Component itemName;

        private Builder() {
        }

        public Builder base(ItemReference base) {
            this.base = base;
            return this;
        }

        public Builder itemName(Component itemName) {
            this.itemName = itemName;
            return this;
        }

        public @NotNull PhoenixItemProperties build() {
            return new PhoenixItemProperties(Objects.requireNonNull(base, "base cannot be null"), itemName);
        }
    }
}
