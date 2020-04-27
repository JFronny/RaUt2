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
                    return RaUt.cfg.misc.chainPlate;
                case "misc.horseArmorRecipe":
                    return RaUt.cfg.misc.horseArmorRecipe;
                case "misc.chainmailRecipe":
                    return RaUt.cfg.misc.chainmailRecipe;
                case "misc.betterDiamondRecipe":
                    return RaUt.cfg.misc.betterDiamondRecipe;
                case "misc.extraCreativeItems":
                    return RaUt.cfg.misc.extraCreativeItems;
                case "misc.moreSeeds":
                    return RaUt.cfg.misc.moreSeeds;
                case "misc.angelBlock":
                    return RaUt.cfg.misc.angelBlock;
                case "misc.boostBlock":
                    return RaUt.cfg.misc.boostBlock;
                case "vanillaPaxels":
                    return RaUt.cfg.vanillaPaxels;
                case "steel":
                    return RaUt.cfg.steel;
                case "aquilorite":
                    return RaUt.cfg.aquilorite;
                case "cotton":
                    return RaUt.cfg.cotton;
                case "crystalPlant":
                    return RaUt.cfg.crystalPlant;
                case "trinkets":
                    return RaUt.cfg.trinkets;
                case "debug":
                    return RaUt.cfg.debug;
                case "reiCompat":
                    return RaUt.cfg.reiCompat;
                default:
                    throw new CDSyntaxError("Expected config entry");
            }
        });
    }
}
