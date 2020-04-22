package com.jfronny.raut.tools;

import com.jfronny.raut.api.FactorToolMat;
import com.jfronny.raut.modules.SteelModule;
import net.minecraft.recipe.Ingredient;

public class SteelToolMat implements FactorToolMat {
    @Override
    public int getDurability() {
        return 2000;
    }

    @Override
    public float getMiningSpeed() {
        return 7;
    }

    @Override
    public float getAttackDamage() {
        return 2;
    }

    @Override
    public int getMiningLevel() {
        return 2;
    }

    @Override
    public int getEnchantability() {
        return 15;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(SteelModule.STEEL_INGOT);
    }

    @Override
    public float getSwordDamage() {
        return 6.5f;
    }

    @Override
    public float getShovelDamage() {
        return 5;
    }

    @Override
    public float getAxeDamage() {
        return 9;
    }

    @Override
    public float getHoeSpeed() {
        return 4;
    }

    @Override
    public float getPickaxeDamage() {
        return 4.5f;
    }
}
