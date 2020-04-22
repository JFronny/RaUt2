package com.jfronny.raut.tools;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AngelBlock extends Block {
    public AngelBlock() {
        super(FabricBlockSettings.of(Material.STONE).build());
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if (!world.isClient && !player.abilities.creativeMode) {
            player.giveItemStack(new ItemStack(this));
        }
    }
}
