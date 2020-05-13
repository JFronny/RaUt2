package com.jfronny.raut.blocks;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;

import static com.jfronny.raut.modules.MiscModule.COBBLE_GENERATOR_ENTITY;

public class CobbleGeneratorEntity extends BlockEntity {
    public int cobbleCount = 0;

    public CobbleGeneratorEntity() {
        super(COBBLE_GENERATOR_ENTITY);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        cobbleCount = tag.getInt("c");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putInt("c", cobbleCount);
        return tag;
    }
}
