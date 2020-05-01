package com.jfronny.raut.modules;

import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.api.DepRegistry;
import com.jfronny.raut.api.GenericPlant;
import io.github.cottonmc.cotton.datapack.recipe.RecipeUtil;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import static com.jfronny.raut.RaUt.cfg;

public class CottonModule extends BaseModule {
    public static final GenericPlant COTTON_CROP = new GenericPlant();
    public static final BlockItem COTTON_SEED = COTTON_CROP.seed;

    @Override
    public void Init() {
        DepRegistry.registerBlock("cotton", cfg.cotton.enabled, COTTON_CROP, COTTON_SEED);
        if (!cfg.cotton.enabled) {
            RecipeUtil.removeRecipe("raut:cotton_string");
        }
    }

    @Override
    public void onLootTableLoading(ResourceManager resourceManager, LootManager lootManager, Identifier id, FabricLootSupplierBuilder supplier, LootTableLoadingCallback.LootTableSetter setter) {
        if ((id.equals(new Identifier("blocks/grass")) || id.equals(new Identifier("blocks/fern")) || id.equals(new Identifier("blocks/tall_grass")))) {
            if (cfg.cotton.enabled) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder
                        .builder()
                        .withRolls(ConstantLootTableRange.create(1))
                        .withCondition(RandomChanceLootCondition.builder(0.042f))
                        .withEntry(ItemEntry.builder(COTTON_SEED));
                supplier.withPool(poolBuilder);
            }
        }
    }

    @Override
    public void InitClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(COTTON_CROP, RenderLayer.getCutout());
    }
}
