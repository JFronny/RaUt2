package com.jfronny.raut.gui;

import com.jfronny.raut.trinket.BackpackTrinket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.math.Direction;

public class BackpackInventory implements ImplementedInventory {
    private ItemStack theStack;
    private DefaultedList<ItemStack> theList;
    private Inventory theInventory;
    private int theSlot;
    private boolean trinket;

    public BackpackInventory(ItemStack stack, Size size, Inventory pInv, int slotNumber) {
        theStack = stack;
        theInventory = pInv;
        theSlot = slotNumber;
        switch (size) {
            case SMALL:
                theList = DefaultedList.ofSize(9, ItemStack.EMPTY);
                break;
            case MEDIUM:
                theList = DefaultedList.ofSize(27, ItemStack.EMPTY);
                break;
            case LARGE:
                theList = DefaultedList.ofSize(45, ItemStack.EMPTY);
                break;
            default:
                theList = DefaultedList.ofSize(9, ItemStack.EMPTY);
                break;
        }
        if (stack.getTag().contains("Inventory"))
            Inventories.fromTag(theStack.getTag().getCompound("Inventory"), theList);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return theList;
    }

    @Override
    public boolean canInsertInvStack(int slot, ItemStack stack, Direction side) {
        if (!(stack.getItem() instanceof BackpackTrinket)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void markDirty() {
        CompoundTag invTag = theStack.getTag().getCompound("Inventory");
        if (theList != null) invTag = Inventories.toTag(invTag, theList);
        else {
            theList = DefaultedList.ofSize(9, ItemStack.EMPTY);
            invTag = Inventories.toTag(invTag, theList);
        }
        theStack.getTag().put("Inventory", invTag);
            theInventory.setInvStack(theSlot, theStack);
            theInventory.markDirty();
    }

    @Override
    public void onInvClose(PlayerEntity playerEntity_1) {
        markDirty();
    }

    public enum Size {
        SMALL, MEDIUM, LARGE
    }

}
