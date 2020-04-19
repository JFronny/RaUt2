package com.jfronny.raut;

import com.jfronny.raut.armor.AquiloriteArmorMat;
import com.jfronny.raut.api.AttributeArmorMat;
import com.jfronny.raut.api.BaseArmor;
import com.jfronny.raut.crops.CottonCrop;
import com.jfronny.raut.items.DebugClear;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RaUt implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();
    public static final Item CHAIN_PLATE = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Block AQUILORITE_ORE = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES, 3).strength(2, 2).build());
    public static final Block AQUILORITE_BLOCK = new Block(FabricBlockSettings.of(Material.GLASS).breakByHand(false).breakByTool(FabricToolTags.PICKAXES, 2).noCollision().strength(2, 2).build());
    public static final Block AQUILORITE_BLOCK_2 = new Block(FabricBlockSettings.of(Material.GLASS).breakByHand(false).breakByTool(FabricToolTags.PICKAXES, 2).slipperiness(1).strength(3, 3).build());
    public static final Item AQUILORITE_GEM = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Block BOOST = new Block(FabricBlockSettings.of(Material.GLASS).breakByHand(false).breakByTool(FabricToolTags.PICKAXES, 2).slipperiness(10).strength(3, 3).build());
    public static final AttributeArmorMat AQUILORITE_ARMOR = new AquiloriteArmorMat();
    public static final Item DEBUG_CLEAR = new DebugClear();
    public static final Block COTTON_CROP = new CottonCrop();
    public static final Item COTTON_SEED = new AliasedBlockItem(COTTON_CROP, new Item.Settings().group(ItemGroup.MATERIALS));
    public static final String MOD_ID = "raut";
    public static final String MOD_NAME = "RaUt2";

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing config");
        Config.initialize();
        if (Config.miscModule.value){
            log(Level.INFO, "Registering misc module");
            Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "boost"), BOOST);
            Registry.register(Registry.ITEM, new Identifier(MOD_ID, "boost"), new BlockItem(BOOST, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
            Registry.register(Registry.ITEM, new Identifier(MOD_ID, "chain_plate"), CHAIN_PLATE);
        }
        else if (Config.aquiloriteModule.value){
            log(Level.INFO, "Registering misc module stub");
            Registry.register(Registry.ITEM, new Identifier(MOD_ID, "chain_plate"), CHAIN_PLATE);
        }
        if (Config.aquiloriteModule.value) {
            log(Level.INFO, "Registering aquilorite module");
            Registry.register(Registry.ITEM, new Identifier(MOD_ID, "aquilorite_gem"), AQUILORITE_GEM);
            Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "aquilorite_ore"), AQUILORITE_ORE);
            Registry.register(Registry.ITEM, new Identifier(MOD_ID, "aquilorite_ore"), new BlockItem(AQUILORITE_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
            Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "aquilorite_block"), AQUILORITE_BLOCK);
            Registry.register(Registry.ITEM, new Identifier(MOD_ID, "aquilorite_block"), new BlockItem(AQUILORITE_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
            Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "aquilorite_block_hardened"), AQUILORITE_BLOCK_2);
            Registry.register(Registry.ITEM, new Identifier(MOD_ID, "aquilorite_block_hardened"), new BlockItem(AQUILORITE_BLOCK_2, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
            Registry.BIOME.forEach(this::handleBiome);
            RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> handleBiome(biome));
            Registry.register(Registry.ITEM, new Identifier(MOD_ID, "aquilorite_helmet"), new BaseArmor(AQUILORITE_ARMOR, EquipmentSlot.HEAD));
            Registry.register(Registry.ITEM, new Identifier(MOD_ID, "aquilorite_chestplate"), new BaseArmor(AQUILORITE_ARMOR, EquipmentSlot.CHEST));
            Registry.register(Registry.ITEM, new Identifier(MOD_ID, "aquilorite_leggings"), new BaseArmor(AQUILORITE_ARMOR, EquipmentSlot.LEGS));
            Registry.register(Registry.ITEM, new Identifier(MOD_ID, "aquilorite_boots"), new BaseArmor(AQUILORITE_ARMOR, EquipmentSlot.FEET));
        }
        if (Config.cottonModule.value){
            log(Level.INFO, "Registering cotton module");
            Registry.register(Registry.ITEM, new Identifier(MOD_ID, "cotton_seeds"), COTTON_SEED);
            Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "cotton"), COTTON_CROP);
            LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
                if(id.equals(new Identifier("blocks/grass")) || id.equals(new Identifier("blocks/fern")) || id.equals(new Identifier("blocks/tall_grass"))){
                    FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder
                            .builder()
                            .withRolls(ConstantLootTableRange.create(1))
                            .withCondition(RandomChanceLootCondition.builder(0.125f))
                            .withEntry(ItemEntry.builder(COTTON_SEED));

                    supplier.withPool(poolBuilder);
                }
            });
        }
        if (Config.debugModule.value){
            log(Level.INFO, "Registering debug module");
            Registry.register(Registry.ITEM, new Identifier(MOD_ID, "debug_clear"), DEBUG_CLEAR);
        }

    }

    private void handleBiome(Biome biome) {
        if(biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
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

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}