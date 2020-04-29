package com.jfronny.raut.api;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;

public class BaseSword extends SwordItem {
    public BaseSword(FactorToolMat material) {
        super(material, (int) (material.getSwordDamage() - 1 - material.getAttackDamage()), -2.4f, new Settings().group(ItemGroup.COMBAT));
    }
}
