package com.jfronny.raut.api;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;

public class BasePickaxe extends PickaxeItem {
    public BasePickaxe(FactorToolMat material) {
        super(material, (int) (material.getPickaxeDamage() - 1 - material.getAttackDamage()), -2.8f, new Item.Settings().group(ItemGroup.TOOLS));
    }
}
