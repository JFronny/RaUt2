package com.jfronny.raut.api;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class BaseAxe extends AxeItem {
    public BaseAxe(FactorToolMat material) {
        super(material, material.getAxeDamage() - 1, -3f, new Settings().group(ItemGroup.TOOLS));
    }
}
