package com.bindglam.phoenix.api.item.attribute;

import com.bindglam.phoenix.api.util.Codec;
import net.kyori.adventure.key.Key;

public interface Attribute<C, P> {
    Key key();

    Codec<C, P> codec();
}
