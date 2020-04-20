package com.jfronny.raut.api;

import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemGroup;

public class BaseAxe extends AxeItem {
    public BaseAxe(FactorToolMat material) {
        super(material, material.getAxeDamage() - 1 - material.getAttackDamage(), -3f, new Settings().group(ItemGroup.TOOLS));
    }
}
