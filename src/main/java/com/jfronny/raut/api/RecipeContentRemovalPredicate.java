package com.jfronny.raut.api;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;

import java.util.function.Predicate;

public class RecipeContentRemovalPredicate implements Predicate<Recipe<?>> {
    private final ItemStack content;

    public RecipeContentRemovalPredicate(ItemStack stack) {
        this.content = stack;
    }

    @Override
    public boolean test(Recipe<?> recipe) {
        return recipe.getPreviewInputs().contains(Ingredient.ofStacks(content));
    }
}
