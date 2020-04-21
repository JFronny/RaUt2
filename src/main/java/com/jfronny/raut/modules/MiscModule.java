package com.jfronny.raut.modules;

import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.api.DefList;
import com.jfronny.raut.api.DepRegistry;
import com.jfronny.raut.api.MiningLevel;
import com.jfronny.raut.tools.AngelBlock;
import com.jfronny.raut.tools.AngelBlockItem;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import io.github.cottonmc.cotton.datapack.recipe.RecipeUtil;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.*;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.resource.ResourceManager;
import net.minecraft.server.command.CommandManager;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

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
        CommandRegistry.INSTANCE.register(true, dispatcher -> { // Or directly registering the command to the dispatcher.
            dispatcher.register(CommandManager.literal("gm").then(CommandManager.argument("mode", integer())
            .executes(ctx -> {

                return 1;
            })));
        });
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
        logger.devInfo("attempt: add extra items");
        DefList<ItemStack> redstoneExtra = new DefList<>();
        redstoneExtra.add(new ItemStack(Items.COMMAND_BLOCK));
        redstoneExtra.add(new ItemStack(Items.COMMAND_BLOCK_MINECART));
        redstoneExtra.add(new ItemStack(Items.CHAIN_COMMAND_BLOCK));
        redstoneExtra.add(new ItemStack(Items.REPEATING_COMMAND_BLOCK));
        redstoneExtra.add(new ItemStack(Items.AIR));
        redstoneExtra.add(new ItemStack(Items.BARRIER));
        redstoneExtra.add(new ItemStack(Items.DEBUG_STICK));
        redstoneExtra.add(new ItemStack(Items.DRAGON_EGG));
        redstoneExtra.add(new ItemStack(Items.SPAWNER));
        redstoneExtra.add(new ItemStack(Items.STRUCTURE_VOID));
        redstoneExtra.add(new ItemStack(Items.STRUCTURE_BLOCK));
        redstoneExtra.add(new ItemStack(Items.JIGSAW));
        redstoneExtra.add(new ItemStack(Items.FILLED_MAP));
        redstoneExtra.add(new ItemStack(Items.WRITTEN_BOOK));
        redstoneExtra.add(new ItemStack(Items.KNOWLEDGE_BOOK));
        ItemGroup.REDSTONE.appendStacks(redstoneExtra);
    }
}
