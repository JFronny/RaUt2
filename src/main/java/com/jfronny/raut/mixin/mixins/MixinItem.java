package com.jfronny.raut.mixin.mixins;

import com.jfronny.raut.mixin.interfacing.ItemExtension;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public abstract class MixinItem implements ItemExtension {
    @Shadow
    private int maxCount;

    public void SetCount(int count) {
        maxCount = count;
    }
}
