package com.jfronny.raut;

import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.modules.*;
import io.github.cottonmc.cotton.config.ConfigManager;
import io.github.cottonmc.cotton.logging.ModLogger;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

public class RaUt implements ModInitializer {

    public static final String MOD_ID = "raut";
    public static final String MOD_NAME = "RaUt2";
    public static final ModLogger logger = new ModLogger(MOD_ID, MOD_NAME);
    public static Config config;
    public static final ArrayList<BaseModule> modules = new ArrayList<>();

    @Override
    public void onInitialize() {
        logger.info("Initializing");
        logger.devInfo("Initializing config");
        config = ConfigManager.loadConfig(Config.class);
        modules.add(new AquiloriteModule());
        modules.add(new CottonModule());
        modules.add(new DebugModule());
        modules.add(new MiscModule());
        modules.add(new SteelModule());
        modules.add(new VanillaPaxelsModule());
        modules.add(new TrinketsModule());

        for (BaseModule module : modules) {
            module.Init();
        }

        Registry.BIOME.forEach((biome -> {
            for (BaseModule module : modules) {
                module.handleBiome(biome);
            }
        }));
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, supplier, setter) -> {
            for (BaseModule module : modules) {
                module.onLootTableLoading(resourceManager, lootManager, id, supplier, setter);
            }
        });
    }
}