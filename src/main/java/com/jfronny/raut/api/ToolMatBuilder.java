package com.jfronny.raut.api;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class ToolMatBuilder implements ToolMaterial {
    int durability;
    float miningSpeed;
    float attackDamage;
    int miningLevel;
    int enchantability;
    Ingredient repairIngredient;
    public ToolMatBuilder(ToolMaterial base){
        durability = base.getDurability();
        miningSpeed = base.getMiningSpeed();
        attackDamage = base.getAttackDamage();
        miningLevel = base.getMiningLevel();
        enchantability = base.getEnchantability();
        repairIngredient = base.getRepairIngredient();
    }

    @Override
    public int getDurability() {
        return durability;
    }

    @Override
    public float getMiningSpeed() {
        return miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }

    public ToolMatBuilder setDurability(int durability) {
        this.durability = durability;
        return this;
    }

    public ToolMatBuilder setMiningSpeed(float miningSpeed) {
        this.miningSpeed = miningSpeed;
        return this;
    }

    public ToolMatBuilder setAttackDamage(float attackDamage) {
        this.attackDamage = attackDamage;
        return this;
    }

    public ToolMatBuilder setMiningLevel(int miningLevel) {
        this.miningLevel = miningLevel;
        return this;
    }

    public ToolMatBuilder setEnchantability(int enchantability) {
        this.enchantability = enchantability;
        return this;
    }

    public ToolMatBuilder setRepairIngredient(Ingredient repairIngredient) {
        this.repairIngredient = repairIngredient;
        return this;
    }
}
