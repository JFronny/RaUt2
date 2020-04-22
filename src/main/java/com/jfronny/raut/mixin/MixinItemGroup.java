package com.jfronny.raut.mixin;

import com.jfronny.raut.api.ItemGroupExtension;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(ItemGroup.class)
public abstract class MixinItemGroup implements ItemGroupExtension {
    List<ItemStack> hackStack = new ArrayList<>();

    public void addStack(ItemStack stack){
        hackStack.add(stack);
    }

    @Inject(method = "appendStacks", at = @At("RETURN"))
    private void appendStacks(DefaultedList<ItemStack> stacks, CallbackInfo ci) {
        stacks.addAll(hackStack);
    }
}
