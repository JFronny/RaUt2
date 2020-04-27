package com.jfronny.raut.modules;

import com.jfronny.raut.RaUt;
import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.api.DepRegistry;
import com.jfronny.raut.crops.CrystalPlant;
import com.jfronny.raut.items.Crystal;
import io.github.cottonmc.cotton.datapack.recipe.RecipeUtil;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import static com.jfronny.raut.RaUt.cfg;

public class CrystalPlantModule extends BaseModule {
    public static final Block CRYSTAL_PLANT = new CrystalPlant();
    public static final BlockItem CRYSTAL_PLANT_SEED = new AliasedBlockItem(CRYSTAL_PLANT, new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item CRYSTAL = new Crystal();

    @Override
    public void Init() {
        DepRegistry.registerBlock("crystal_plant", cfg.crystalPlant, CRYSTAL_PLANT, CRYSTAL_PLANT_SEED);
        DepRegistry.registerItem("crystal", cfg.crystalPlant, CRYSTAL);
        if (!cfg.crystalPlant){
            RecipeUtil.removeRecipe("raut:crystal_apple");
            RecipeUtil.removeRecipe("raut:crystal_enchanted_apple");
        }
    }

    @Override
    public void onLootTableLoading(ResourceManager resourceManager, LootManager lootManager, Identifier id, FabricLootSupplierBuilder supplier, LootTableLoadingCallback.LootTableSetter setter) {
        if ((id.equals(new Identifier("chests/abandoned_mineshaft")) || id.equals(new Identifier("chests/desert_pyramid")) || id.equals(new Identifier("chests/jungle_temple")))) {
            if (cfg.crystalPlant) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder
                        .builder()
                        .withRolls(ConstantLootTableRange.create(1))
                        .withCondition(RandomChanceLootCondition.builder(0.1f))
                        .withEntry(ItemEntry.builder(CRYSTAL))
                        .withEntry(ItemEntry.builder(CRYSTAL_PLANT_SEED));
                supplier.withPool(poolBuilder);
            }
        }
    }

    @Override
    public void InitClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(CRYSTAL_PLANT, RenderLayer.getCutout());
    }
}
