package com.jfronny.raut.api;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class BaseSword extends SwordItem {
    public BaseSword(FactorToolMat material) {
        super(material, (int)(material.getSwordDamage() - 1), -2.4f, new Settings().group(ItemGroup.TOOLS));
    }
}
