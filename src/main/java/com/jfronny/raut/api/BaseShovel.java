package com.jfronny.raut.api;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class BaseShovel extends ShovelItem {
    public BaseShovel(FactorToolMat material) {
        super(material, material.getShovelDamage() - 1, -3f, new Settings().group(ItemGroup.TOOLS));
    }
}
