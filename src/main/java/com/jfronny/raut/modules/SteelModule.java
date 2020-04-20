package com.jfronny.raut.modules;

import com.jfronny.raut.api.AttributeArmorMat;
import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.api.DepRegistry;
import com.jfronny.raut.api.MiningLevel;
import com.jfronny.raut.armor.SteelArmorMat;
import com.jfronny.raut.tools.SteelToolMat;
import io.github.cottonmc.cotton.datapack.recipe.RecipeUtil;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

import static com.jfronny.raut.RaUt.config;
import static com.jfronny.raut.RaUt.logger;

public class SteelModule extends BaseModule {
    public static final Item RAW_STEEL = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item STEEL_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item STEEL_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Block STEEL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).breakByHand(false).breakByTool(FabricToolTags.PICKAXES, MiningLevel.IRON).strength(5, 6).build());
    public static final AttributeArmorMat STEEL_ARMOR = new SteelArmorMat();

    @Override
    public void Init() {
        DepRegistry.registerTools("steel", config.steel, new SteelToolMat());
        DepRegistry.registerItem("raw_steel", config.steel || config.aquilorite, RAW_STEEL);
        DepRegistry.registerItem("steel_ingot", config.steel || config.aquilorite, STEEL_INGOT);
        DepRegistry.registerItem("steel_nugget", config.steel, STEEL_NUGGET);
        DepRegistry.registerBlock("steel_block", config.steel, STEEL_BLOCK);
        DepRegistry.registerArmor("steel", config.steel, STEEL_ARMOR);
        if (!config.steel) {
            logger.devInfo("unreg steel tools");
            RecipeUtil.removeRecipe("raut:steel_axe1");
            RecipeUtil.removeRecipe("raut:steel_axe2");
            RecipeUtil.removeRecipe("raut:steel_hoe1");
            RecipeUtil.removeRecipe("raut:steel_hoe2");
            RecipeUtil.removeRecipe("raut:steel_pickaxe");
            RecipeUtil.removeRecipe("raut:steel_shovel");
            RecipeUtil.removeRecipe("raut:steel_sword");
            RecipeUtil.removeRecipe("raut:steel_paxel");
            logger.devInfo("unreg steel compactions");
            RecipeUtil.removeRecipe("raut:steel_nugget_1");
            RecipeUtil.removeRecipe("raut:steel_nugget_2");
            RecipeUtil.removeRecipe("raut:steel_block_1");
            RecipeUtil.removeRecipe("raut:steel_block_2");
            logger.devInfo("unreg steel armor");
            RecipeUtil.removeRecipe("raut:steel_helmet");
            RecipeUtil.removeRecipe("raut:steel_chestplate");
            RecipeUtil.removeRecipe("raut:steel_leggings");
            RecipeUtil.removeRecipe("raut:steel_boots");
            if (!config.aquilorite) {
                logger.devInfo("unreg steel");
                RecipeUtil.removeRecipe("raw_steel");
                RecipeUtil.removeRecipe("steel_ingot");
            }
        }
    }
}
