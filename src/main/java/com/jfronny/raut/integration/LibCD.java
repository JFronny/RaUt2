package com.jfronny.raut.integration;

import com.jfronny.raut.RaUt;
import io.github.cottonmc.libcd.api.CDSyntaxError;
import io.github.cottonmc.libcd.api.LibCDInitializer;
import io.github.cottonmc.libcd.api.condition.ConditionManager;
import io.github.cottonmc.libcd.api.tweaker.TweakerManager;
import net.minecraft.util.Identifier;

import static com.jfronny.raut.RaUt.cfg;

public class LibCD implements LibCDInitializer {
    @Override
    public void initTweakers(TweakerManager tweakerManager) {

    }

    @Override
    public void initConditions(ConditionManager conditionManager) {
        conditionManager.registerCondition(new Identifier(RaUt.MOD_ID, "config"), (s) -> {
            if (!(s instanceof String)) {
                throw new CDSyntaxError("Expected string");
            }
            switch ((String) s) {
                case "misc":
                case "misc.enabled":
                    return cfg.misc.enabled;
                case "misc.chainPlate":
                    return cfg.misc.enabled && cfg.misc.chainPlate;
                case "misc.horseArmorRecipe":
                    return cfg.misc.enabled && cfg.misc.horseArmorRecipe;
                case "misc.chainmailRecipe":
                    return cfg.misc.enabled && cfg.misc.chainmailRecipe;
                case "misc.betterDiamondRecipe":
                    return cfg.misc.enabled && cfg.misc.betterDiamondRecipe;
                case "misc.extraCreativeItems":
                    return cfg.misc.enabled && cfg.misc.extraCreativeItems;
                case "misc.moreSeeds":
                    return cfg.misc.enabled && cfg.misc.moreSeeds;
                case "misc.angelBlock":
                    return cfg.misc.enabled && cfg.misc.angelBlock;
                case "misc.boostBlock":
                    return cfg.misc.enabled && cfg.misc.boostBlock;
                case "misc.glassShards":
                    return cfg.misc.enabled && cfg.misc.glassShards;
                case "vanillaPaxels":
                case "vanillaPaxels.enabled":
                    return cfg.vanillaPaxels.enabled;
                case "vanillaPaxels.wood":
                    return cfg.vanillaPaxels.enabled && cfg.vanillaPaxels.wood;
                case "vanillaPaxels.stone":
                    return cfg.vanillaPaxels.enabled && cfg.vanillaPaxels.stone;
                case "vanillaPaxels.iron":
                    return cfg.vanillaPaxels.enabled && cfg.vanillaPaxels.iron;
                case "vanillaPaxels.gold":
                    return cfg.vanillaPaxels.enabled && cfg.vanillaPaxels.gold;
                case "vanillaPaxels.diamond":
                    return cfg.vanillaPaxels.enabled && cfg.vanillaPaxels.diamond;
                case "steel":
                case "steel.enabled;":
                    return cfg.steel.enabled;
                case "steel.nugget":
                    return cfg.steel.enabled && cfg.steel.nugget;
                case "steel.block":
                    return cfg.steel.enabled && cfg.steel.block;
                case "steel.tools":
                    return cfg.steel.enabled && cfg.steel.tools;
                case "steel.armor":
                    return cfg.steel.enabled && cfg.steel.armor;
                case "aquilorite":
                case "aquilorite.enabled":
                    return cfg.aquilorite.enabled;
                case "aquilorite.aquiloriteBlock":
                    return cfg.aquilorite.enabled && cfg.aquilorite.aquiloriteBlock;
                case "aquilorite.aquiloriteBlockHardened":
                    return cfg.aquilorite.enabled && cfg.aquilorite.aquiloriteBlockHardened;
                case "aquilorite.aquiloriteArmor":
                    return cfg.aquilorite.enabled && cfg.aquilorite.aquiloriteArmor;
                case "aquilorite.aquiloritePaxel":
                    return cfg.aquilorite.enabled && cfg.aquilorite.aquiloritePaxel;
                case "cotton":
                case "cotton.enabled":
                    return cfg.cotton.enabled;
                case "crystalPlant":
                case "crystalPlant.enabled":
                    return cfg.crystalPlant.enabled;
                case "crystalPlant.craftGApples":
                    return cfg.crystalPlant.enabled && cfg.crystalPlant.craftGApples;
                case "crystalPlant.craftGApples2":
                    return cfg.crystalPlant.enabled && cfg.crystalPlant.craftGApples2;
                case "trinkets":
                case "trinkets.enabled":
                    return cfg.trinkets.enabled;
                case "trinkets.traveller_ring":
                    return cfg.trinkets.enabled && cfg.trinkets.traveller_ring;
                case "trinkets.angel_ring":
                    return cfg.trinkets.enabled && cfg.trinkets.angel_ring;
                case "trinkets.builders_ring":
                    return cfg.trinkets.enabled && cfg.trinkets.builders_ring;
                case "trinkets.backpack":
                    return cfg.trinkets.enabled && cfg.trinkets.backpack;
                case "debug":
                case "debug.enabled":
                    return cfg.debug.enabled;
                case "debug.debugClear":
                    return cfg.debug.enabled && cfg.debug.debugClear;
                case "reiCompat":
                case "reiCompat.enabled":
                    return cfg.reiCompat.enabled;
                default:
                    throw new CDSyntaxError("Expected config entry");
            }
        });
    }
}
