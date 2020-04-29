package com.jfronny.raut.api;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;

public class BasePaxel extends PaxelItem {
    public BasePaxel(ToolMaterial material) {
        super(new ToolMatBuilder(material).setAttackDamage(material.getAttackDamage()), new Settings().group(ItemGroup.TOOLS).maxDamage(material.getDurability() * 3));
    }
}
