package com.jfronny.raut.gui;

import com.jfronny.raut.RaUt;
import dev.emi.trinkets.api.TrinketsApi;
import io.github.cottonmc.cotton.gui.CottonCraftingController;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WPlayerInvPanel;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;


public class BackpackController extends CottonCraftingController {
    ItemStack theStack;

    public BackpackController(int syncId, PlayerInventory playerInventory, ItemStack stack, int size, int slotNumber) {
        super(RecipeType.SMELTING, syncId, playerInventory);
        theStack = stack;
        this.playerInventory = playerInventory;
        Inventory inv;
        if (slotNumber == -1) {
            inv = TrinketsApi.getTrinketsInventory(playerInventory.player);
            for (int i = 0; i < inv.getInvSize(); i++) {
                if (inv.getInvStack(i).isItemEqual(stack)) {
                    slotNumber = i;
                }
            }
        } else {
            inv = playerInventory;
        }
        this.blockInventory = new BackpackInventory(theStack, BackpackInventory.Size.values()[size], inv, slotNumber);

        WGridPanel rootPanel = (WGridPanel) getRootPanel();
        Text label = stack.hasCustomName() ? stack.getName() : new TranslatableText("gui." + RaUt.MOD_ID + ".backpack");
        rootPanel.add(new WLabel(label, WLabel.DEFAULT_TEXT_COLOR), 0, 0);
        int rows = (int) Math.ceil(blockInventory.getInvSize() / 9.0);
        for (int i = 1; i <= rows; i++) {
            for (int j = 0; j < 9; j++) {
                WItemSlot slot = WItemSlot.of(blockInventory, ((i - 1) * 9) + j);
                rootPanel.add(slot, j, i);
            }
        }

        rootPanel.add(new WLabel(new TranslatableText("container.inventory"), WLabel.DEFAULT_TEXT_COLOR), 0, rows + 1);
        WPlayerInvPanel playerInv = this.createPlayerInventoryPanel();
        //playerInv.
        rootPanel.add(playerInv, 0, rows + 2);
        rootPanel.validate(this);
    }

    @Override
    public int getCraftingResultSlotIndex() {
        return -1;
    }

}
