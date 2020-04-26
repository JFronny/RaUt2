package com.jfronny.raut.modules;

import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.api.DepRegistry;
import com.jfronny.raut.api.PaxelItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterials;

import static com.jfronny.raut.RaUt.cfg;

public class VanillaPaxelsModule extends BaseModule {
    public static final PaxelItem WOODEN_PAXEL = new PaxelItem(ToolMaterials.WOOD, new Item.Settings().maxDamage(ToolMaterials.WOOD.getDurability() * 3).group(ItemGroup.TOOLS));
    public static final PaxelItem STONE_PAXEL = new PaxelItem(ToolMaterials.STONE, new Item.Settings().maxDamage(ToolMaterials.STONE.getDurability() * 3).group(ItemGroup.TOOLS));
    public static final PaxelItem IRON_PAXEL = new PaxelItem(ToolMaterials.IRON, new Item.Settings().maxDamage(ToolMaterials.IRON.getDurability() * 3).group(ItemGroup.TOOLS));
    public static final PaxelItem GOLDEN_PAXEL = new PaxelItem(ToolMaterials.GOLD, new Item.Settings().maxDamage(ToolMaterials.GOLD.getDurability() * 3).group(ItemGroup.TOOLS));
    public static final PaxelItem DIAMOND_PAXEL = new PaxelItem(ToolMaterials.DIAMOND, new Item.Settings().maxDamage(ToolMaterials.DIAMOND.getDurability() * 3).group(ItemGroup.TOOLS));

    @Override
    public void Init() {
        DepRegistry.registerItem("wooden_paxel", cfg.vanillaPaxels, WOODEN_PAXEL);
        DepRegistry.registerItem("stone_paxel", cfg.vanillaPaxels, STONE_PAXEL);
        DepRegistry.registerItem("iron_paxel", cfg.vanillaPaxels, IRON_PAXEL);
        DepRegistry.registerItem("golden_paxel", cfg.vanillaPaxels, GOLDEN_PAXEL);
        DepRegistry.registerItem("diamond_paxel", cfg.vanillaPaxels, DIAMOND_PAXEL);
    }
}
