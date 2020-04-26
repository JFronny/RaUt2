package com.jfronny.raut;

import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.api.DepRegistry;
import com.jfronny.raut.modules.*;
import io.github.cottonmc.cotton.datapack.recipe.RecipeUtil;
import io.github.cottonmc.cotton.logging.ModLogger;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import me.sargunvohra.mcmods.autoconfig1u.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;

public class RaUt implements ModInitializer {

    public static final String MOD_ID = "raut";
    public static final String MOD_NAME = "RaUt2";
    public static final ModLogger logger = new ModLogger(MOD_ID, MOD_NAME);
    public static final ArrayList<BaseModule> modules = new ArrayList<>();
    public static Cfg cfg;

    @Override
    public void onInitialize() {
        logger.info("Initializing");
        //cfg = ConfigManager.loadConfig(Cfg.class);
        AutoConfig.register(Cfg.class, JanksonConfigSerializer::new);
        cfg = AutoConfig.getConfigHolder(Cfg.class).getConfig();
        modules.add(new AquiloriteModule());
        modules.add(new CottonModule());
        modules.add(new CrystalPlantModule());
        modules.add(new DataFixerModule());
        modules.add(new DebugModule());
        modules.add(new MiscModule());
        modules.add(new SteelModule());
        modules.add(new TrinketsModule());
        modules.add(new VanillaPaxelsModule());

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
        for (Item item : DepRegistry.disabledItems.values()) {
            RecipeUtil.removeRecipeFor(new ItemStack(item));
        }
    }
}