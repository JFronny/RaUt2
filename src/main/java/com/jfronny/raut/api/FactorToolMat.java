package com.jfronny.raut.api;

import net.minecraft.item.ToolMaterial;

public interface FactorToolMat extends ToolMaterial {
    public abstract float getSwordDamage();

    public abstract float getShovelDamage();

    public abstract float getAxeDamage();

    public abstract float getHoeSpeed();

    public abstract float getPickaxeDamage();
}
