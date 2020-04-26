package com.jfronny.raut.mixin.mixins;

import com.jfronny.raut.mixin.interfacing.ItemGroupExtension;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

//Thanks to @Draylar for helping me with this (https://discordapp.com/channels/507304429255393322/507982478276034570/702540027594539079)
@Mixin(ItemGroup.class)
public abstract class MixinItemGroup implements ItemGroupExtension {
    List<ItemStack> hackStack = new ArrayList<>();

    public void addStack(ItemStack stack) {
        hackStack.add(stack);
    }

    @Inject(method = "appendStacks", at = @At("RETURN"))
    private void appendStacks(DefaultedList<ItemStack> stacks, CallbackInfo ci) {
        stacks.addAll(hackStack);
    }
}
