package com.jfronny.raut.mixin.mixins;

import com.jfronny.raut.mixin.interfacing.ShulkerIllegalChecker;
import net.minecraft.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ShulkerBoxBlockEntity.class)
public class MixinShulkerBoxBlockEntity {
    /**
     * @reason Required for custom blocked items to work properly.
     * None of the original content of the method is useful and may actually break this
     * @author JFronny
     */
    @Overwrite
    public boolean canInsertInvStack(int slot, ItemStack stack, Direction dir) {
        return ShulkerIllegalChecker.isAllowed(stack);
    }
}
