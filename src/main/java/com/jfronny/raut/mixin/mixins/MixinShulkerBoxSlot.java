package com.jfronny.raut.mixin.mixins;

import com.jfronny.raut.mixin.interfacing.ShulkerIllegalChecker;
import net.minecraft.container.ShulkerBoxSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ShulkerBoxSlot.class)
public class MixinShulkerBoxSlot {
    /**
     * @reason Required for custom blocked items to work properly.
     * None of the original content of the method is useful and may actually break this
     * @author JFronny
     */
    @Overwrite
    public boolean canInsert(ItemStack stack) {
        return ShulkerIllegalChecker.isAllowed(stack);
    }
}
