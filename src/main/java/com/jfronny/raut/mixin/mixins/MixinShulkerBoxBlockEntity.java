package com.jfronny.raut.mixin.mixins;

import com.jfronny.raut.mixin.interfacing.ShulkerIllegalChecker;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(ShulkerBoxBlockEntity.class)
public class MixinShulkerBoxBlockEntity {
    @Overwrite
    public boolean canInsertInvStack(int slot, ItemStack stack, Direction dir) {
        return ShulkerIllegalChecker.isAllowed(stack);
    }
}
