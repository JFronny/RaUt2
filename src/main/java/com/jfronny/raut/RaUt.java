package com.jfronny.raut;

import com.jfronny.raut.api.AttributeArmorMat;
import com.jfronny.raut.api.DepRegistry;
import com.jfronny.raut.armor.AquiloriteArmorMat;
import com.jfronny.raut.armor.SteelArmorMat;
import com.jfronny.raut.crops.CottonCrop;
import com.jfronny.raut.items.DebugClear;
import com.jfronny.raut.tools.SteelToolMat;
import io.github.cottonmc.cotton.config.ConfigManager;
import io.github.cottonmc.cotton.datapack.recipe.RecipeUtil;
import io.github.cottonmc.cotton.logging.ModLogger;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.*;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class RaUt implements ModInitializer {
    public static final Item CHAIN_PLATE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Block AQUILORITE_ORE = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES, 3).strength(2, 2).build());
    public static final Block AQUILORITE_BLOCK = new Block(FabricBlockSettings.of(Material.GLASS).breakByHand(false).breakByTool(FabricToolTags.PICKAXES, 2).noCollision().strength(2, 2).build());
    public static final Block AQUILORITE_BLOCK_2 = new Block(FabricBlockSettings.of(Material.GLASS).breakByHand(false).breakByTool(FabricToolTags.PICKAXES, 2).slipperiness(1).strength(3, 3).build());
    public static final Item AQUILORITE_GEM = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Block BOOST = new Block(FabricBlockSettings.of(Material.GLASS).breakByHand(false).breakByTool(FabricToolTags.PICKAXES, 2).slipperiness(10).strength(3, 3).build());
    public static final AttributeArmorMat AQUILORITE_ARMOR = new AquiloriteArmorMat();
    public static final Item DEBUG_CLEAR = new DebugClear();
    public static final Block COTTON_CROP = new CottonCrop();
    public static final BlockItem COTTON_SEED = new AliasedBlockItem(COTTON_CROP, new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item RAW_STEEL = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item STEEL_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item STEEL_NUGGET = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Block STEEL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).breakByHand(false).breakByTool(FabricToolTags.PICKAXES, 2).strength(5, 6).build());
    public static final AttributeArmorMat STEEL_ARMOR = new SteelArmorMat();
    public static final String MOD_ID = "raut";
    public static final String MOD_NAME = "RaUt2";
    public static Config config;
    public static final ModLogger logger = new ModLogger(MOD_ID, MOD_NAME);

    @Override
    public void onInitialize() {
        logger.info("Initializing");
        logger.devInfo("Initializing config");
        config = ConfigManager.loadConfig(Config.class);
        DepRegistry.registerBlock("boost", config.misc, BOOST);
        DepRegistry.registerItem("chain_plate", config.misc || config.aquilorite, CHAIN_PLATE);
        if (config.misc) {
            if (config.replaceVanilla){
                logger.devInfo("unreg vanilla armor");
                RecipeUtil.removeRecipe("minecraft:leather_horse_armor");
                RecipeUtil.removeRecipe("minecraft:diamond_boots");
                RecipeUtil.removeRecipe("minecraft:diamond_chestplate");
                RecipeUtil.removeRecipe("minecraft:diamond_helmet");
                RecipeUtil.removeRecipe("minecraft:diamond_leggings");
            }
            else {
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
            if (!config.aquilorite) {
                logger.devInfo("unreg chainplate");
                RecipeUtil.removeRecipe("raut:chain_plate");
            }
        }
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
        DepRegistry.registerArmor("aquilorite", config.aquilorite, AQUILORITE_ARMOR);
        DepRegistry.registerBlock("aquilorite_ore", config.aquilorite, AQUILORITE_ORE);
        DepRegistry.registerBlock("aquilorite_block", config.aquilorite, AQUILORITE_BLOCK);
        DepRegistry.registerBlock("aquilorite_block_hardened", config.aquilorite, AQUILORITE_BLOCK_2);
        DepRegistry.registerItem("aquilorite_gem", config.aquilorite, AQUILORITE_GEM);
        Registry.BIOME.forEach(this::handleBiome);
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> handleBiome(biome));
        if (!config.aquilorite) {
            logger.devInfo("unreg aquilorite block");
            RecipeUtil.removeRecipe("raut:aquilorite_block_1");
            RecipeUtil.removeRecipe("raut:aquilorite_block_2");
            RecipeUtil.removeRecipe("raut:aquilorite_block_hardened_1");
            RecipeUtil.removeRecipe("raut:aquilorite_block_hardened_2");
            logger.devInfo("unreg aquilorite armor");
            RecipeUtil.removeRecipe("raut:aquilorite_boots");
            RecipeUtil.removeRecipe("raut:aquilorite_chestplate");
            RecipeUtil.removeRecipe("raut:aquilorite_helmet");
            RecipeUtil.removeRecipe("raut:aquilorite_leggings");
        }
        DepRegistry.registerBlock("cotton", config.cotton, COTTON_CROP, COTTON_SEED);
        if (!config.cotton) {
            logger.devInfo("unreg cotton->string");
            RecipeUtil.removeRecipe("raut:cotton_string");
        }
        DepRegistry.registerItem("debug_clear", config.debug, DEBUG_CLEAR);
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            if ((id.equals(new Identifier("blocks/grass")) || id.equals(new Identifier("blocks/fern")) || id.equals(new Identifier("blocks/tall_grass")))){
                if (config.cotton){
                    FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder
                            .builder()
                            .withRolls(ConstantLootTableRange.create(1))
                            .withCondition(RandomChanceLootCondition.builder(0.042f))
                            .withEntry(ItemEntry.builder(COTTON_SEED));
                    supplier.withPool(poolBuilder);
                }
                if (config.misc){
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
        });
    }

    private void handleBiome(Biome biome) {
        if(biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
            if (config.aquilorite) {
                biome.addFeature(
                        GenerationStep.Feature.UNDERGROUND_ORES,
                        Feature.ORE.configure(
                                new OreFeatureConfig(
                                        OreFeatureConfig.Target.NATURAL_STONE,
                                        AQUILORITE_ORE.getDefaultState(),
                                        4 //Ore vein size
                                )).createDecoratedFeature(
                                Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(
                                        4, //Number of veins per chunk
                                        0, //Bottom Offset
                                        0, //Min y level
                                        40 //Max y level
                                ))));
            }
        }
    }

}