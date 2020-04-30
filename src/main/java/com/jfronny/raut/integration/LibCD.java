package com.jfronny.raut.integration;

import com.jfronny.raut.RaUt;
import io.github.cottonmc.libcd.api.CDSyntaxError;
import io.github.cottonmc.libcd.api.LibCDInitializer;
import io.github.cottonmc.libcd.api.condition.ConditionManager;
import io.github.cottonmc.libcd.api.tweaker.TweakerManager;
import net.minecraft.util.Identifier;

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
                    return RaUt.cfg.misc.enabled;
                case "misc.chainPlate":
                    return RaUt.cfg.misc.enabled && RaUt.cfg.misc.chainPlate;
                case "misc.horseArmorRecipe":
                    return RaUt.cfg.misc.enabled && RaUt.cfg.misc.horseArmorRecipe;
                case "misc.chainmailRecipe":
                    return RaUt.cfg.misc.enabled && RaUt.cfg.misc.chainmailRecipe;
                case "misc.betterDiamondRecipe":
                    return RaUt.cfg.misc.enabled && RaUt.cfg.misc.betterDiamondRecipe;
                case "misc.extraCreativeItems":
                    return RaUt.cfg.misc.enabled && RaUt.cfg.misc.extraCreativeItems;
                case "misc.moreSeeds":
                    return RaUt.cfg.misc.enabled && RaUt.cfg.misc.moreSeeds;
                case "misc.angelBlock":
                    return RaUt.cfg.misc.enabled && RaUt.cfg.misc.angelBlock;
                case "misc.boostBlock":
                    return RaUt.cfg.misc.enabled && RaUt.cfg.misc.boostBlock;
                case "vanillaPaxels":
                case "vanillaPaxels.enabled":
                    return RaUt.cfg.vanillaPaxels.enabled;
                case "vanillaPaxels.wood":
                    return RaUt.cfg.vanillaPaxels.wood;
                case "vanillaPaxels.stone":
                    return RaUt.cfg.vanillaPaxels.stone;
                case "vanillaPaxels.iron":
                    return RaUt.cfg.vanillaPaxels.iron;
                case "vanillaPaxels.gold":
                    return RaUt.cfg.vanillaPaxels.gold;
                case "vanillaPaxels.diamond":
                    return RaUt.cfg.vanillaPaxels.diamond;
                case "steel":
                case "steel.enabled;":
                    return RaUt.cfg.steel.enabled;
                case "steel.nugget":
                    return RaUt.cfg.steel.nugget;
                case "steel.block":
                    return RaUt.cfg.steel.block;
                case "steel.tools":
                    return RaUt.cfg.steel.tools;
                case "steel.armor":
                    return RaUt.cfg.steel.armor;
                case "aquilorite":
                case "aquilorite.enabled":
                    return RaUt.cfg.aquilorite.enabled;
                case "aquilorite.aquiloriteBlock":
                    return RaUt.cfg.aquilorite.aquiloriteBlock;
                case "aquilorite.aquiloriteBlockHardened":
                    return RaUt.cfg.aquilorite.aquiloriteBlockHardened;
                case "aquilorite.aquiloriteArmor":
                    return RaUt.cfg.aquilorite.aquiloriteArmor;
                case "aquilorite.aquiloritePaxel":
                    return RaUt.cfg.aquilorite.aquiloritePaxel;
                case "cotton":
                case "cotton.enabled":
                    return RaUt.cfg.cotton.enabled;
                case "crystalPlant":
                case "crystalPlant.enabled":
                    return RaUt.cfg.crystalPlant.enabled;
                case "crystalPlant.craftGApples":
                    return RaUt.cfg.crystalPlant.craftGApples;
                case "crystalPlant.craftGApples2":
                    return RaUt.cfg.crystalPlant.craftGApples2;
                case "trinkets":
                case "trinkets.enabled":
                    return RaUt.cfg.trinkets.enabled;
                case "trinkets.traveller_ring":
                    return RaUt.cfg.trinkets.traveller_ring;
                case "trinkets.angel_ring":
                    return RaUt.cfg.trinkets.angel_ring;
                case "trinkets.builders_ring":
                    return RaUt.cfg.trinkets.builders_ring;
                case "trinkets.backpack":
                    return RaUt.cfg.trinkets.backpack;
                case "debug":
                case "debug.enabled":
                    return RaUt.cfg.debug.enabled;
                case "debug.debugClear":
                    return RaUt.cfg.debug.debugClear;
                case "reiCompat":
                case "reiCompat.enabled":
                    return RaUt.cfg.reiCompat.enabled;
                default:
                    throw new CDSyntaxError("Expected config entry");
            }
        });
    }
}
