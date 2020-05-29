package com.jfronny.raut.mixin.mixins;

import com.jfronny.raut.RaUt;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerInventory.class)
public abstract class MixinPlayerInventory {
    @Final
    @Shadow
    public DefaultedList<ItemStack> main;
    //This is the intermediary name for the below method. This shouldn't change
    @Environment(EnvType.SERVER)
    public int method_7395(ItemStack stack) {
        for(int i = 0; i < this.main.size(); ++i) {
            if (!this.main.get(i).isEmpty() && this.areItemsEqual(stack, this.main.get(i))) {
                return i;
            }
        }
        return -1;
    }
    //This is the yarn name for this method. Used in debug environments
    @Environment(EnvType.SERVER)
    public int getSlotWithStack(ItemStack stack) {
        return method_7395(stack);
    }

    @Shadow
    private boolean areItemsEqual(ItemStack stack1, ItemStack stack2) {
        RaUt.logger.devWarn("areItemsEqual was not shadowed!");
        return stack1.getItem() == stack2.getItem() && ItemStack.areTagsEqual(stack1, stack2);
    }
}
//getSlotWithStack