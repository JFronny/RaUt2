package com.jfronny.raut.modules;

import com.jfronny.raut.RaUt;
import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.api.DepRegistry;
import com.jfronny.raut.api.GenericPlant;
import com.jfronny.raut.api.MiningLevel;
import com.jfronny.raut.blocks.BlockBreaker;
import com.jfronny.raut.blocks.CobbleGeneratorBlock;
import com.jfronny.raut.blocks.CobbleGeneratorEntity;
import com.jfronny.raut.mixin.interfacing.ItemGroupExtension;
import com.jfronny.raut.tools.AngelBlock;
import com.jfronny.raut.tools.AngelBlockItem;
import io.github.cottonmc.cotton.datapack.recipe.RecipeUtil;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.*;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.resource.ResourceManager;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.jfronny.raut.RaUt.cfg;

public class MiscModule extends BaseModule {
    public static final Block BOOST = new Block(FabricBlockSettings.of(Material.GLASS).breakByHand(false).breakByTool(FabricToolTags.PICKAXES, MiningLevel.IRON).slipperiness(10).strength(3, 3).build());
    public static final Item CHAIN_PLATE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Block ANGEL_BLOCK = new AngelBlock();
    public static final Item GLASS_SHARD = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final GenericPlant END_PLANT = new GenericPlant();
    public static final BlockItem END_PLANT_SEED = END_PLANT.seed;
    public static final Block COBBLE_GENERATOR = new CobbleGeneratorBlock();
    public static final BlockItem COBBLE_GENERATOR_ITEM = new BlockItem(COBBLE_GENERATOR, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final BlockEntityType COBBLE_GENERATOR_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(RaUt.MOD_ID, "cobble_generator"), BlockEntityType.Builder.create(CobbleGeneratorEntity::new, COBBLE_GENERATOR).build(null));
    public static final Block BLOCK_BREAKER = new BlockBreaker();
    public static final Tag<Block> UNBREAKABLE = TagRegistry.block(new Identifier(RaUt.MOD_ID, "unbreakable"));

    @Override
    public void Init() {
        DepRegistry.registerBlock("boost", cfg.misc.enabled && cfg.misc.boostBlock, BOOST);
        DepRegistry.registerItem("chain_plate", cfg.misc.enabled && cfg.misc.chainPlate, CHAIN_PLATE);
        DepRegistry.registerBlock("angelblock", cfg.misc.enabled && cfg.misc.angelBlock, ANGEL_BLOCK, new AngelBlockItem());
        DepRegistry.registerItem("glass_shard", cfg.misc.enabled && cfg.misc.glassShards, GLASS_SHARD);
        DepRegistry.registerBlock("end_plant", cfg.misc.enabled && cfg.misc.endPlant, END_PLANT, END_PLANT_SEED);
        DepRegistry.registerBlock("cobble_generator", cfg.misc.enabled && cfg.misc.cobbleGen, COBBLE_GENERATOR, COBBLE_GENERATOR_ITEM);
        DepRegistry.registerBlock("block_breaker", cfg.misc.enabled, BLOCK_BREAKER, ItemGroup.REDSTONE);
        if (cfg.misc.enabled) {
            if (cfg.misc.betterDiamondRecipe) {
                RecipeUtil.removeRecipe("minecraft:leather_horse_armor");
                RecipeUtil.removeRecipe("minecraft:diamond_boots");
                RecipeUtil.removeRecipe("minecraft:diamond_chestplate");
                RecipeUtil.removeRecipe("minecraft:diamond_helmet");
                RecipeUtil.removeRecipe("minecraft:diamond_leggings");
            } else {
                RecipeUtil.removeRecipe("raut:diamond_boots");
                RecipeUtil.removeRecipe("raut:diamond_chestplate");
                RecipeUtil.removeRecipe("raut:diamond_helmet");
                RecipeUtil.removeRecipe("raut:diamond_leggings");
            }
            if (!cfg.misc.chainmailRecipe) {
                RecipeUtil.removeRecipe("raut:chainmail_boots");
                RecipeUtil.removeRecipe("raut:chainmail_chestplate");
                RecipeUtil.removeRecipe("raut:chainmail_helmet");
                RecipeUtil.removeRecipe("raut:chainmail_leggings");
            }
            if (!cfg.misc.horseArmorRecipe) {
                RecipeUtil.removeRecipe("raut:leather_horse_armor");
                RecipeUtil.removeRecipe("raut:diamond_horse_armor");
                RecipeUtil.removeRecipe("raut:golden_horse_armor");
                RecipeUtil.removeRecipe("raut:iron_horse_armor");
            }
            if (!cfg.misc.glassShards) {
                RecipeUtil.removeRecipe("raut:glass_shard");
            }
        } else {
            RecipeUtil.removeRecipe("raut:leather_horse_armor");
            RecipeUtil.removeRecipe("raut:diamond_horse_armor");
            RecipeUtil.removeRecipe("raut:golden_horse_armor");
            RecipeUtil.removeRecipe("raut:iron_horse_armor");
            RecipeUtil.removeRecipe("raut:chainmail_boots");
            RecipeUtil.removeRecipe("raut:chainmail_chestplate");
            RecipeUtil.removeRecipe("raut:chainmail_helmet");
            RecipeUtil.removeRecipe("raut:chainmail_leggings");
            RecipeUtil.removeRecipe("raut:diamond_boots");
            RecipeUtil.removeRecipe("raut:diamond_chestplate");
            RecipeUtil.removeRecipe("raut:diamond_helmet");
            RecipeUtil.removeRecipe("raut:diamond_leggings");
            RecipeUtil.removeRecipe("raut:glass_shard");
        }
    }

    @Override
    public void onLootTableLoading(ResourceManager resourceManager, LootManager lootManager, Identifier id, FabricLootSupplierBuilder supplier, LootTableLoadingCallback.LootTableSetter setter) {
        if ((id.equals(new Identifier("blocks/grass")) || id.equals(new Identifier("blocks/fern")) || id.equals(new Identifier("blocks/tall_grass")))) {
            if (cfg.misc.enabled && cfg.misc.moreSeeds) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder
                        .builder()
                        .withRolls(ConstantLootTableRange.create(1))
                        .withCondition(RandomChanceLootCondition.builder(0.125f))
                        .withEntry(ItemEntry.builder(Items.BEETROOT_SEEDS))
                        .withEntry(ItemEntry.builder(Items.MELON_SEEDS))
                        .withEntry(ItemEntry.builder(Items.PUMPKIN_SEEDS));
                supplier.withPool(poolBuilder.build());
            }
        }
        if ((id.equals(new Identifier("chests/abandoned_mineshaft")) || id.equals(new Identifier("chests/desert_pyramid")) || id.equals(new Identifier("chests/jungle_temple")))) {
            if (cfg.misc.enabled && cfg.misc.endPlant) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder
                        .builder()
                        .withRolls(ConstantLootTableRange.create(1))
                        .withCondition(RandomChanceLootCondition.builder(0.1f))
                        .withEntry(ItemEntry.builder(END_PLANT_SEED));
                supplier.withPool(poolBuilder);
            }
        }
    }

    @Override
    public void InitClient() {
        if (cfg.misc.enabled && cfg.misc.extraCreativeItems) {
            ItemGroupExtension SEARCH = (ItemGroupExtension) ItemGroup.SEARCH;

            ItemGroupExtension REDSTONE = (ItemGroupExtension) ItemGroup.REDSTONE;
            REDSTONE.addStack(new ItemStack(Items.COMMAND_BLOCK));
            SEARCH.addStack(new ItemStack(Items.COMMAND_BLOCK));
            REDSTONE.addStack(new ItemStack(Items.CHAIN_COMMAND_BLOCK));
            SEARCH.addStack(new ItemStack(Items.CHAIN_COMMAND_BLOCK));
            REDSTONE.addStack(new ItemStack(Items.REPEATING_COMMAND_BLOCK));
            SEARCH.addStack(new ItemStack(Items.REPEATING_COMMAND_BLOCK));
            REDSTONE.addStack(new ItemStack(Items.STRUCTURE_BLOCK));
            SEARCH.addStack(new ItemStack(Items.STRUCTURE_BLOCK));
            REDSTONE.addStack(new ItemStack(Items.JIGSAW));
            SEARCH.addStack(new ItemStack(Items.JIGSAW));

            ItemGroupExtension TRANSPORT = (ItemGroupExtension) ItemGroup.TRANSPORTATION;
            TRANSPORT.addStack(new ItemStack(Items.COMMAND_BLOCK_MINECART));
            SEARCH.addStack(new ItemStack(Items.COMMAND_BLOCK_MINECART));

            ItemGroupExtension DECORATIONS = (ItemGroupExtension) ItemGroup.DECORATIONS;
            DECORATIONS.addStack(new ItemStack(Items.DRAGON_EGG));
            SEARCH.addStack(new ItemStack(Items.DRAGON_EGG));
            DECORATIONS.addStack(new ItemStack(Items.BARRIER));
            SEARCH.addStack(new ItemStack(Items.BARRIER));
            DECORATIONS.addStack(new ItemStack(Items.SPAWNER));
            SEARCH.addStack(new ItemStack(Items.SPAWNER));
            DECORATIONS.addStack(new ItemStack(Items.STRUCTURE_VOID));
            SEARCH.addStack(new ItemStack(Items.STRUCTURE_VOID));

            ItemGroupExtension MISC = (ItemGroupExtension) ItemGroup.MISC;
            MISC.addStack(new ItemStack(Items.DEBUG_STICK));
            SEARCH.addStack(new ItemStack(Items.DEBUG_STICK));
            MISC.addStack(new ItemStack(Items.FILLED_MAP));
            SEARCH.addStack(new ItemStack(Items.FILLED_MAP));
            MISC.addStack(new ItemStack(Items.WRITTEN_BOOK));
            SEARCH.addStack(new ItemStack(Items.WRITTEN_BOOK));
            MISC.addStack(new ItemStack(Items.KNOWLEDGE_BOOK));
            SEARCH.addStack(new ItemStack(Items.KNOWLEDGE_BOOK));
        }
        if (cfg.misc.enabled && cfg.misc.endPlant) {
            BlockRenderLayerMap.INSTANCE.putBlock(END_PLANT, RenderLayer.getCutout());
        }
    }
}
