package com.jfronny.raut.api;

import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemGroup;

public class BaseHoe extends HoeItem {
    public BaseHoe(FactorToolMat material) {
        super(material, material.getHoeSpeed() - 4, new Settings().group(ItemGroup.TOOLS));
    }
}
