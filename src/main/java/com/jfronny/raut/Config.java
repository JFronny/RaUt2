package com.jfronny.raut;

import blue.endless.jankson.Comment;
import io.github.cottonmc.cotton.config.annotations.ConfigFile;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

@ConfigFile(name = "RaUt2")
public class Config {
    @Comment(value = "Miscellaneous content. Includes a recipe for chainmail/horse armor using chain plates, grass dropping melon/pumpkin/beetroot seeds and a block that shoots you across your world")
    public Boolean misc = true;

    @Comment(value = "Adds steel as a resource in between iron and diamonds. Contains processing, ingots, nuggets, blocks, armor and tools")
    public Boolean steel = true;

    @Comment(value = "A new, powerful ore. Includes the ore, the aquilorite gem, two full blocks and armor")
    public Boolean aquilorite = true;

    @Comment(value = "Adds a new plant that can be used to farm string")
    public Boolean cotton = true;

    @Comment(value = "Adds overpowered creative-only items. Only useful for debugging")
    public Boolean debug = false;

    @Comment(value = "Enables replacing vanilla content for a more homogenous experience. Might interfere with other mods")
    public Boolean replaceVanilla = true;

    @Comment(value = "Enables REI Compat. Only disable if it causes problems")
    public Boolean reiCompat = true;
}
