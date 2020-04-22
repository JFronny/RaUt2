package com.jfronny.raut.modules;

import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.api.DepRegistry;
import com.jfronny.raut.api.MiningLevel;
import com.jfronny.raut.api.ItemGroupExtension;
import com.jfronny.raut.tools.AngelBlock;
import com.jfronny.raut.tools.AngelBlockItem;
import io.github.cottonmc.cotton.datapack.recipe.RecipeUtil;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.*;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static com.jfronny.raut.RaUt.config;
import static com.jfronny.raut.RaUt.logger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;

public class MiscModule extends BaseModule {
    public static final Block BOOST = new Block(FabricBlockSettings.of(Material.GLASS).breakByHand(false).breakByTool(FabricToolTags.PICKAXES, MiningLevel.IRON).slipperiness(10).strength(3, 3).build());
    public static final Item CHAIN_PLATE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Block ANGEL_BLOCK = new AngelBlock();

    @Override
    public void Init() {
        DepRegistry.registerBlock("boost", config.misc, BOOST);
        DepRegistry.registerItem("chain_plate", config.misc || config.aquilorite, CHAIN_PLATE);
        DepRegistry.registerBlock("angelblock", config.misc, ANGEL_BLOCK, new AngelBlockItem());
        if (config.misc) {
            if (config.replaceVanilla) {
                logger.devInfo("unreg vanilla armor");
                RecipeUtil.removeRecipe("minecraft:leather_horse_armor");
                RecipeUtil.removeRecipe("minecraft:diamond_boots");
                RecipeUtil.removeRecipe("minecraft:diamond_chestplate");
                RecipeUtil.removeRecipe("minecraft:diamond_helmet");
                RecipeUtil.removeRecipe("minecraft:diamond_leggings");
            } else {
                logger.devInfo("unreg diamond armor replacements");
                RecipeUtil.removeRecipe("raut:diamond_boots");
                RecipeUtil.removeRecipe("raut:diamond_chestplate");
                RecipeUtil.removeRecipe("raut:diamond_helmet");
                RecipeUtil.removeRecipe("raut:diamond_leggings");
            }
        } else {
            logger.devInfo("unreg vanilla armor replacements");
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
            logger.devInfo("unreg boost");
            RecipeUtil.removeRecipe("raut:boost");
            logger.devInfo("unreg angelblock");
            RecipeUtil.removeRecipe("raut:angelblock");
            if (!config.aquilorite) {
                logger.devInfo("unreg chainplate");
                RecipeUtil.removeRecipe("raut:chain_plate");
            }
        }
    }

    @Override
    public void onLootTableLoading(ResourceManager resourceManager, LootManager lootManager, Identifier id, FabricLootSupplierBuilder supplier, LootTableLoadingCallback.LootTableSetter setter) {
        if ((id.equals(new Identifier("blocks/grass")) || id.equals(new Identifier("blocks/fern")) || id.equals(new Identifier("blocks/tall_grass")))) {
            if (config.misc) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder
                        .builder()
                        .withRolls(ConstantLootTableRange.create(1))
                        .withCondition(RandomChanceLootCondition.builder(0.125f))
                        .withEntry(ItemEntry.builder(Items.BEETROOT_SEEDS))
                        .withEntry(ItemEntry.builder(Items.MELON_SEEDS))
                        .withEntry(ItemEntry.builder(Items.PUMPKIN_SEEDS));
                supplier.withPool(poolBuilder);
            }
        }
    }

    @Override
    public void InitClient() {
        if (config.replaceVanilla){
            logger.devInfo("add extra items to creative menus");

            ItemGroupExtension REDSTONE = (ItemGroupExtension)ItemGroup.REDSTONE;
            REDSTONE.addStack(new ItemStack(Items.COMMAND_BLOCK));
            REDSTONE.addStack(new ItemStack(Items.CHAIN_COMMAND_BLOCK));
            REDSTONE.addStack(new ItemStack(Items.REPEATING_COMMAND_BLOCK));
            REDSTONE.addStack(new ItemStack(Items.STRUCTURE_BLOCK));
            REDSTONE.addStack(new ItemStack(Items.JIGSAW));

            ItemGroupExtension TRANSPORT = (ItemGroupExtension)ItemGroup.TRANSPORTATION;
            TRANSPORT.addStack(new ItemStack(Items.COMMAND_BLOCK_MINECART));

            ItemGroupExtension DECORATIONS = (ItemGroupExtension)ItemGroup.DECORATIONS;
            DECORATIONS.addStack(new ItemStack(Items.DRAGON_EGG));
            DECORATIONS.addStack(new ItemStack(Items.BARRIER));
            DECORATIONS.addStack(new ItemStack(Items.SPAWNER));
            DECORATIONS.addStack(new ItemStack(Items.STRUCTURE_VOID));

            ItemGroupExtension MISC = (ItemGroupExtension)ItemGroup.MISC;
            MISC.addStack(new ItemStack(Items.DEBUG_STICK));
            MISC.addStack(new ItemStack(Items.FILLED_MAP));
            MISC.addStack(new ItemStack(Items.WRITTEN_BOOK));
            MISC.addStack(new ItemStack(Items.KNOWLEDGE_BOOK));
        }
    }
}
