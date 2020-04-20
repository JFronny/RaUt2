package com.jfronny.raut.api;

import net.minecraft.item.ItemGroup;

public class BasePaxel extends PaxelItem {
    public BasePaxel(FactorToolMat material) {
        super(material, new Settings().group(ItemGroup.TOOLS).maxDamage(material.getDurability() * 3));
    }
}
