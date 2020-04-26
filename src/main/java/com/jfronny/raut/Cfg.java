package com.jfronny.raut;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.shadowed.blue.endless.jankson.Comment;
import net.fabricmc.loader.api.FabricLoader;

@Config(name = "RaUt2")
public class Cfg implements ConfigData {
    @Comment(value = "Miscellaneous content. Includes a recipe for chainmail/horse armor using chain plates, grass dropping melon/pumpkin/beetroot seeds, an angel block and a block that shoots you across your world")
    public Boolean misc = true;

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

    @Comment(value = "Enables replacing and modifying vanilla content for a more homogenous experience. Might interfere with other mods")
    public Boolean replaceVanilla = true;

    @Comment(value = "Enables REI Compat. Only disable if it causes problems")
    public Boolean reiCompat = true;
}
