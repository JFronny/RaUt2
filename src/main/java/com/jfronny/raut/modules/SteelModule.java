package com.jfronny.raut.modules;

import com.jfronny.raut.api.AttributeArmorMat;
import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.api.DepRegistry;
import com.jfronny.raut.api.MiningLevel;
import com.jfronny.raut.armor.SteelArmorMat;
import com.jfronny.raut.tools.SteelToolMat;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import static com.jfronny.raut.RaUt.cfg;

public class SteelModule extends BaseModule {
    public static final Item RAW_STEEL = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item STEEL_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item STEEL_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Block STEEL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).breakByHand(false).breakByTool(FabricToolTags.PICKAXES, MiningLevel.IRON).strength(5, 6).build());
    public static final AttributeArmorMat STEEL_ARMOR = new SteelArmorMat();

    @Override
    public void Init() {
        DepRegistry.registerTools("steel", cfg.steel.tools, new SteelToolMat());
        boolean cottonRes = FabricLoader.getInstance().isModLoaded("cotton-resources");
        DepRegistry.registerItem("raw_steel", cfg.steel.enabled || ((cfg.aquilorite.enabled && cfg.aquilorite.aquiloriteBlockHardened) && !cottonRes), RAW_STEEL);
        DepRegistry.registerItem("steel_ingot", cfg.steel.enabled || ((cfg.aquilorite.enabled && cfg.aquilorite.aquiloriteBlockHardened) && !cottonRes), STEEL_INGOT);
        DepRegistry.registerItem("steel_nugget", cfg.steel.nugget, STEEL_NUGGET);
        DepRegistry.registerBlock("steel_block", cfg.steel.block, STEEL_BLOCK);
        DepRegistry.registerArmor("steel", cfg.steel.armor, STEEL_ARMOR);
    }
}
