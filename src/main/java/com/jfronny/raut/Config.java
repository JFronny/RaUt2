package com.jfronny.raut;

import de.siphalor.tweed.client.TweedClothBridge;
import de.siphalor.tweed.config.*;
import de.siphalor.tweed.config.entry.BooleanEntry;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class Config {

    public static TweedClothBridge tweedClothBridge;

    private static ConfigFile file = TweedRegistry.registerConfigFile(RaUt.MOD_ID).setEnvironment(ConfigEnvironment.SERVER);

    public static ConfigCategory modulesCategory = file.register("modules", new ConfigCategory())
            .setEnvironment(ConfigEnvironment.SYNCED)
            .setComment("Enable or disable modules. Note that if some components of a module are required for another to work they will not be disabled.");

    public static BooleanEntry miscModule = modulesCategory.register("misc", new BooleanEntry(true))
            .setComment("Miscellaneous content. Includes a recipe for chainmail armor using chain plates and a block that shoots you across your world");

    public static BooleanEntry aquiloriteModule = modulesCategory.register("aquilorite", new BooleanEntry(true))
            .setComment("A new, powerful ore. Includes the ore, the aquilorite gem, two full blocks and armor");

    public static BooleanEntry cottonModule = modulesCategory.register("cotton", new BooleanEntry(true))
            .setComment("Adds a new plant that can be used to farm strings and its' seeds");

    public static BooleanEntry debugModule = modulesCategory.register("debug", new BooleanEntry(false))
            .setComment("Adds overpowered creative-only items. Only useful for debugging");

    public static void initialize() {
        if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT)
            tweedClothBridge = new TweedClothBridge(file);
    }
}
