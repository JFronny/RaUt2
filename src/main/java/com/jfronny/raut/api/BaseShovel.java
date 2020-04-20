package com.jfronny.raut.api;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;

public class BaseShovel extends ShovelItem {
    public BaseShovel(FactorToolMat material) {
        super(material, material.getShovelDamage() - 1 - material.getAttackDamage(), -3f, new Settings().group(ItemGroup.TOOLS));
    }
}
