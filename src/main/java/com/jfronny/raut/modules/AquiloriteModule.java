package com.jfronny.raut.modules;

import com.jfronny.raut.api.*;
import com.jfronny.raut.armor.AquiloriteArmorMat;
import com.jfronny.raut.tools.AquiloriteToolMat;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import static com.jfronny.raut.RaUt.cfg;

public class AquiloriteModule extends BaseModule {
    public static final Block AQUILORITE_ORE = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES, MiningLevel.DIAMOND).strength(2, 2).build());
    public static final Block AQUILORITE_BLOCK = new Block(FabricBlockSettings.of(Material.GLASS).breakByHand(false).breakByTool(FabricToolTags.PICKAXES, MiningLevel.IRON).noCollision().strength(2, 2).build());
    public static final Block AQUILORITE_BLOCK_2 = new Block(FabricBlockSettings.of(Material.GLASS).breakByHand(false).breakByTool(FabricToolTags.PICKAXES, MiningLevel.IRON).slipperiness(1).strength(3, 3).build());
    public static final Item AQUILORITE_GEM = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item AQUILORITE_MULTITOOL = new PaxelItem(new AquiloriteToolMat(), new Item.Settings().maxDamage(12000).group(ItemGroup.TOOLS));
    public static final AttributeArmorMat AQUILORITE_ARMOR = new AquiloriteArmorMat();

    @Override
    public void Init() {
        DepRegistry.registerArmor("aquilorite", cfg.aquilorite.enabled && cfg.aquilorite.aquiloriteArmor, AQUILORITE_ARMOR);
        DepRegistry.registerBlock("aquilorite_ore", cfg.aquilorite.enabled, AQUILORITE_ORE);
        DepRegistry.registerBlock("aquilorite_block", cfg.aquilorite.enabled && cfg.aquilorite.aquiloriteBlock, AQUILORITE_BLOCK);
        DepRegistry.registerBlock("aquilorite_block_hardened", cfg.aquilorite.enabled && cfg.aquilorite.aquiloriteBlockHardened, AQUILORITE_BLOCK_2);
        DepRegistry.registerItem("aquilorite_gem", cfg.aquilorite.enabled, AQUILORITE_GEM);
        DepRegistry.registerItem("aquilorite_paxel", cfg.aquilorite.enabled && cfg.aquilorite.aquiloritePaxel, AQUILORITE_MULTITOOL);
        RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> handleBiome(biome));
    }

    @Override
    public void handleBiome(Biome biome) {
        if (biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) {
            if (cfg.aquilorite.enabled) {
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
