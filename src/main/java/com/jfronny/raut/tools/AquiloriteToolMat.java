package com.jfronny.raut.tools;

import com.jfronny.raut.modules.AquiloriteModule;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class AquiloriteToolMat implements ToolMaterial {
    @Override
    public int getDurability() {
        return 4000;
    }

    @Override
    public float getMiningSpeed() {
        return 10;
    }

    @Override
    public float getAttackDamage() {
        return 15;
    }

    @Override
    public int getMiningLevel() {
        return 4;
    }

    @Override
    public int getEnchantability() {
        return 30;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(AquiloriteModule.AQUILORITE_GEM);
    }
}
