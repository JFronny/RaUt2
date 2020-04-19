package com.jfronny.raut.api;

import net.minecraft.item.ToolMaterial;

public abstract class FactorToolMat implements ToolMaterial {
    public abstract float getSwordDamage();
    public abstract float getShovelDamage();
    public abstract float getAxeDamage();
    public abstract float getHoeSpeed();
    public abstract float getPickaxeDamage();
    @Override public float getAttackDamage() { return 0f; }
}
