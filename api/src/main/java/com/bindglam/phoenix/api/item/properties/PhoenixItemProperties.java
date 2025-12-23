package com.bindglam.phoenix.api.item.properties;

import com.bindglam.phoenix.api.item.ItemReference;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemRarity;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Supplier;

public record PhoenixItemProperties(@NotNull ItemReference base, Supplier<Component> itemName, ItemRarity rarity, boolean hideAttributes) {
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ItemReference base;

        private Supplier<Component> itemName;
        private ItemRarity rarity;
        private boolean hideAttributes;

        private Builder() {
        }

        public Builder base(ItemReference base) {
            this.base = base;
            return this;
        }

        public Builder itemName(Supplier<Component> itemName) {
            this.itemName = itemName;
            return this;
        }

        public Builder rarity(ItemRarity rarity) {
            this.rarity = rarity;
            return this;
        }

        public Builder hideAttributes(boolean hideAttributes) {
            this.hideAttributes = hideAttributes;
            return this;
        }

        public @NotNull PhoenixItemProperties build() {
            return new PhoenixItemProperties(Objects.requireNonNull(base, "base cannot be null"), itemName, rarity, hideAttributes);
        }
    }
}
