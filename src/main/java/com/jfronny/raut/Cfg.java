package com.jfronny.raut;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;
import net.fabricmc.loader.api.FabricLoader;

@Config(name = "RaUt2")
public class Cfg implements ConfigData {
    @Comment(value = "Miscellaneous content")
    @ConfigEntry.Category("Misc")
    @ConfigEntry.Gui.TransitiveObject
    public MiscModule misc = new MiscModule();

    @Comment(value = "Adds paxels for vanilla resources. Disable if another mod does that already.")
    public Boolean vanillaPaxels = !FabricLoader.getInstance().isModLoaded("variablepaxels");

    @Comment(value = "Adds steel as a resource in between iron and diamonds. Contains processing, ingots, nuggets, blocks, armor and tools")
    public Boolean steel = true;

    @Comment(value = "A new, powerful ore. Includes the ore, the aquilorite gem, two full blocks and armor")
    public Boolean aquilorite = true;

    @Comment(value = "Adds a new plant that can be used to farm string")
    public Boolean cotton = !FabricLoader.getInstance().isModLoaded("flax");

    @Comment(value = "Adds a new plant that can be used to get random potion effects")
    public Boolean crystalPlant = true;

    @Comment(value = "Adds some trinkets")
    public Boolean trinkets = FabricLoader.getInstance().isModLoaded("trinkets");

    @Comment(value = "Adds overpowered creative-only items. Only useful for debugging")
    public Boolean debug = false;

    @Comment(value = "Enables REI Compat. Only disable if it causes problems")
    public Boolean reiCompat = true;

    public static class MiscModule {
        public Boolean enabled = true;
        @Comment("A recipe for chainmail armor")
        public Boolean chainmailRecipe = enabled;
        @Comment("A recipe for horse armor")
        public Boolean horseArmorRecipe = enabled;
        @Comment("Grass drops melon/pumpkin/beetroot seeds")
        public Boolean moreSeeds = enabled;
        @Comment("An angel block (a block you can place in mid-air)")
        public Boolean angelBlock = enabled;
        @Comment("A block that speeds you up drastically")
        public Boolean boostBlock = enabled;
        @Comment("A crafting-item, used by other modules if available")
        public Boolean chainPlate = enabled;
        @Comment("A recipe for diamond armor that uses chainmail as a base (like aquilorite)")
        public Boolean betterDiamondRecipe = enabled;
        @Comment("Adds some items to the creative menus that are hidden by default (eg command blocks)")
        public Boolean extraCreativeItems = enabled;
    }
}
