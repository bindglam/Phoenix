package com.bindglam.phoenix.api.item.attribute;

import com.bindglam.phoenix.api.item.builder.ItemBuilderConsumer;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.ApiStatus;

public interface Attribute<C, P> {
    Key key();

    Codec<C, P> codec();

    void apply(ItemBuilderConsumer builder, C data);

    @ApiStatus.Internal
    default void applyObj(ItemBuilderConsumer builder, Object data) {
        apply(builder, codec().complexType().cast(data));
    }
}
