package com.jfronny.raut.modules;

import com.jfronny.raut.api.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterials;

import static com.jfronny.raut.RaUt.cfg;

public class VanillaPaxelsModule extends BaseModule {
    public static final PaxelItem WOODEN_PAXEL = new BasePaxel(ToolMaterials.WOOD);
    public static final PaxelItem STONE_PAXEL = new BasePaxel(ToolMaterials.STONE);
    public static final PaxelItem IRON_PAXEL = new BasePaxel(ToolMaterials.IRON);
    public static final PaxelItem GOLDEN_PAXEL = new BasePaxel(new ToolMatBuilder(ToolMaterials.GOLD).setAttackDamage(6));
    public static final PaxelItem DIAMOND_PAXEL = new BasePaxel(ToolMaterials.DIAMOND);

    @Override
    public void Init() {
        DepRegistry.registerItem("wooden_paxel", cfg.vanillaPaxels, WOODEN_PAXEL);
        DepRegistry.registerItem("stone_paxel", cfg.vanillaPaxels, STONE_PAXEL);
        DepRegistry.registerItem("iron_paxel", cfg.vanillaPaxels, IRON_PAXEL);
        DepRegistry.registerItem("golden_paxel", cfg.vanillaPaxels, GOLDEN_PAXEL);
        DepRegistry.registerItem("diamond_paxel", cfg.vanillaPaxels, DIAMOND_PAXEL);
    }
}
