package com.jfronny.raut.mixin.interfacing;

import net.minecraft.item.Item;

import java.util.HashMap;

public class RecipeManagerExtension {
    public static HashMap<Item, Item> replacements = new HashMap<>();

    public static void RegisterReplacement(Item original, Item replacement) {
        replacements.put(original, replacement);
    }
}
