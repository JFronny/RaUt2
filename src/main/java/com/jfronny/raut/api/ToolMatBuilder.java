package com.jfronny.raut.api;

import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class ToolMatBuilder implements ToolMaterial {
    int durability;
    float miningSpeed;
    float attackDamage;
    int miningLevel;
    int enchantability;
    Ingredient repairIngredient;

    public ToolMatBuilder(ToolMaterial base) {
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

    public ToolMatBuilder setMiningSpeed(float miningSpeed) {
        this.miningSpeed = miningSpeed;
        return this;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    public ToolMatBuilder setAttackDamage(float attackDamage) {
        this.attackDamage = attackDamage;
        return this;
    }

    @Override
    public int getMiningLevel() {
        return miningLevel;
    }

    public ToolMatBuilder setMiningLevel(int miningLevel) {
        this.miningLevel = miningLevel;
        return this;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    public ToolMatBuilder setEnchantability(int enchantability) {
        this.enchantability = enchantability;
        return this;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }

    public ToolMatBuilder setRepairIngredient(Ingredient repairIngredient) {
        this.repairIngredient = repairIngredient;
        return this;
    }

    public ToolMatBuilder setDurability(int durability, Block.Settings set) {
        this.durability = durability;
        return this;
    }
}
