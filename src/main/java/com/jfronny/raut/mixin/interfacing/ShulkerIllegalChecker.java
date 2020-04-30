package com.jfronny.raut.mixin.interfacing;

import com.jfronny.raut.RaUt;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;

public class ShulkerIllegalChecker {
    public static boolean isAllowed(ItemStack stack) {
        //Tag
        ItemTags.CachingTag tag = new ItemTags.CachingTag(new Identifier(RaUt.MOD_ID, "shulker_boxes_illegal"));
        return !tag.contains(stack.getItem());
        //Should be equal to
        //!(stack.getItem() instanceof BackpackTrinket) && !(Block.getBlockFromItem(stack.getItem()) instanceof ShulkerBoxBlock);
    }
}
