package com.jfronny.raut.modules;

import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.api.DepRegistry;
import com.jfronny.raut.mixin.interfacing.ItemExtension;
import io.github.cottonmc.cotton.datapack.recipe.RecipeUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;

import static com.jfronny.raut.RaUt.cfg;

public class OreProcessingModule extends BaseModule {
    public static final Item IRON_SHARD = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item IRON_DUST = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item GOLD_SHARD = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item GOLD_DUST = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

    @Override
    public void Init() {
        DepRegistry.registerItem("iron_shard", cfg.oreProcessing.enabled && cfg.oreProcessing.iron, IRON_SHARD);
        DepRegistry.registerItem("iron_dust", cfg.oreProcessing.enabled && cfg.oreProcessing.iron, IRON_DUST);
        DepRegistry.registerItem("gold_shard", cfg.oreProcessing.enabled && cfg.oreProcessing.gold, GOLD_SHARD);
        DepRegistry.registerItem("gold_dust", cfg.oreProcessing.enabled && cfg.oreProcessing.gold, GOLD_DUST);
        if (cfg.oreProcessing.enabled) {
            ((ItemExtension) Items.ANVIL).SetCount(1);
            if (!cfg.oreProcessing.iron) {
                RecipeUtil.removeRecipe("raut:iron_dust");
                RecipeUtil.removeRecipe("raut:iron_shard");
                RecipeUtil.removeRecipe("raut:iron_ingot");
                RecipeUtil.removeRecipe("raut:iron_ingot2");
            }
            if (!cfg.oreProcessing.gold) {
                RecipeUtil.removeRecipe("raut:gold_dust");
                RecipeUtil.removeRecipe("raut:gold_shard");
                RecipeUtil.removeRecipe("raut:gold_ingot");
                RecipeUtil.removeRecipe("raut:gold_ingot2");
            }
            if (!cfg.oreProcessing.gems) {
                RecipeUtil.removeRecipe("raut:aquilorite_proc");
                RecipeUtil.removeRecipe("raut:diamond_proc");
                RecipeUtil.removeRecipe("raut:emerald_proc");
            }
        } else {
            RecipeUtil.removeRecipe("raut:iron_dust");
            RecipeUtil.removeRecipe("raut:iron_shard");
            RecipeUtil.removeRecipe("raut:iron_ingot");
            RecipeUtil.removeRecipe("raut:iron_ingot2");
            RecipeUtil.removeRecipe("raut:gold_dust");
            RecipeUtil.removeRecipe("raut:gold_shard");
            RecipeUtil.removeRecipe("raut:gold_ingot");
            RecipeUtil.removeRecipe("raut:gold_ingot2");
            RecipeUtil.removeRecipe("raut:aquilorite_proc");
            RecipeUtil.removeRecipe("raut:coal_proc");
            RecipeUtil.removeRecipe("raut:diamond_proc");
            RecipeUtil.removeRecipe("raut:emerald_proc");
            RecipeUtil.removeRecipe("raut:lapis_proc");
            RecipeUtil.removeRecipe("raut:nether_quartz_proc");
            //NBT-Crafting doesn't seem to like my DepRegistry recipe removal
        }
    }
}
