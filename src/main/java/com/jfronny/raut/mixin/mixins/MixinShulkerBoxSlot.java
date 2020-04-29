package com.jfronny.raut.mixin.mixins;

import com.jfronny.raut.mixin.interfacing.ShulkerIllegalChecker;
import net.minecraft.block.Block;
import net.minecraft.block.ShulkerBoxBlock;
import net.minecraft.container.ShulkerBoxSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ShulkerBoxSlot.class)
public class MixinShulkerBoxSlot {
    @Overwrite
    public boolean canInsert(ItemStack stack) {
        return ShulkerIllegalChecker.isAllowed(stack);
    }
}
