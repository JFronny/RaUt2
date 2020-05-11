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
    public VanillaPaxelsModule vanillaPaxels = new VanillaPaxelsModule();

    @Comment(value = "Adds steel as a resource in between iron and diamonds. Contains processing, ingots, nuggets, blocks, armor and tools")
    @ConfigEntry.Category("Steel")
    @ConfigEntry.Gui.TransitiveObject
    public SteelModule steel = new SteelModule();

    @Comment(value = "A new, powerful ore. Includes the ore, the aquilorite gem, two full blocks and armor")
    @ConfigEntry.Category("Aquilorite")
    @ConfigEntry.Gui.TransitiveObject
    public AquiloriteModule aquilorite = new AquiloriteModule();

    @Comment(value = "Adds a new plant that can be used to farm string")
    @ConfigEntry.Category("Cotton")
    @ConfigEntry.Gui.TransitiveObject
    public CottonModule cotton = new CottonModule();

    @Comment(value = "Adds a new plant that can be used to get random potion effects")
    @ConfigEntry.Category("CrystalPlant")
    @ConfigEntry.Gui.TransitiveObject
    public CrystalPlantModule crystalPlant = new CrystalPlantModule();

    @Comment(value = "Adds some trinkets")
    @ConfigEntry.Category("Trinkets")
    @ConfigEntry.Gui.TransitiveObject
    public TrinketsModule trinkets = new TrinketsModule();

    @Comment(value = "Adds overpowered creative-only items. Only useful for debugging")
    @ConfigEntry.Category("Debug")
    @ConfigEntry.Gui.TransitiveObject
    public DebugModule debug = new DebugModule();

    @Comment(value = "Enables REI Compat. Only disable if it causes problems")
    @ConfigEntry.Category("REICompat")
    @ConfigEntry.Gui.TransitiveObject
    public ReiCompatModule reiCompat = new ReiCompatModule();

    @Comment(value = "Ore processing")
    @ConfigEntry.Category("OreProcessing")
    @ConfigEntry.Gui.TransitiveObject
    public OreProcessingModule oreProcessing = new OreProcessingModule();

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
        @Comment("Glass drops some shards so you don't loose all of it")
        public Boolean glassShards = enabled;
        @Comment("A plant to farm ender pearls")
        public Boolean endPlant = enabled;
    }

    public static class VanillaPaxelsModule {
        public Boolean enabled = !FabricLoader.getInstance().isModLoaded("variablepaxels");
        @Comment("Wooden paxel")
        public Boolean wood = enabled;
        @Comment("Stone paxel")
        public Boolean stone = enabled;
        @Comment("Iron paxel")
        public Boolean iron = enabled;
        @Comment("Golden paxel")
        public Boolean gold = enabled;
        @Comment("Diamond paxel")
        public Boolean diamond = enabled;
    }

    public static class SteelModule {
        public Boolean enabled = true;
        @Comment("Steel nuggets")
        public Boolean nugget = enabled;
        @Comment("Steel blocks")
        public Boolean block = enabled;
        @Comment("Steel Tools. Between iron and diamond")
        public Boolean tools = enabled;
        @Comment("Steel Armor. Between iron and diamond")
        public Boolean armor = enabled;
    }

    public static class AquiloriteModule {
        public Boolean enabled = true;
        @Comment("A walk-through block")
        public Boolean aquiloriteBlock = enabled;
        @Comment("Just another ore block")
        public Boolean aquiloriteBlockHardened = enabled;
        @Comment("Armor similarly powered to netherite")
        public Boolean aquiloriteArmor = enabled;
        @Comment("A powerful multitool")
        public Boolean aquiloritePaxel = enabled;
        @Comment("Aquilorite gun")
        public Boolean gun = enabled;
    }

    public static class CottonModule {
        public Boolean enabled = !FabricLoader.getInstance().isModLoaded("flax");
    }

    public static class CrystalPlantModule {
        public Boolean enabled = true;
        @Comment("Allows to craft Gold Apples using Crystal Plants")
        public Boolean craftGApples = enabled;
        @Comment("Allows to craft Notch Apples from Gold Apples using Crystal Plants")
        public Boolean craftGApples2 = enabled;
    }

    public static class TrinketsModule {
        public Boolean enabled = FabricLoader.getInstance().isModLoaded("trinkets");
        @Comment("Ring that allows you to run faster/step higher")
        public Boolean traveller_ring = enabled;
        @Comment("Ring that allows you to reach further")
        public Boolean angel_ring = enabled;
        @Comment("Ring that allows you to reach further")
        public Boolean builders_ring = enabled;
        @Comment("Backpack to store items. Comes in three tiers")
        public Boolean backpack = enabled;
    }

    public static class DebugModule {
        public Boolean enabled = false;
        @Comment("Item to easily clear large areas")
        public Boolean debugClear = enabled;
    }

    public static class ReiCompatModule {
        public Boolean enabled = true;
    }

    public static class OreProcessingModule {
        public Boolean enabled = true;
        @Comment("Enables processing for gold")
        public Boolean gold = enabled;
        @Comment("Enables processing for iron")
        public Boolean iron = enabled;
        @Comment("Enables processing for diamonds, emeralds and aquilorite (if enabled)")
        public Boolean gems = enabled;
    }
}
