package com.jfronny.raut.gui;

import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen;
import net.minecraft.client.gui.screen.ingame.ContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public class BackpackScreen extends CottonInventoryScreen<BackpackController> {
    public BackpackScreen(BackpackController container, PlayerEntity player){
        super(container, player);
    }
}
