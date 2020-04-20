package com.jfronny.raut.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DebugClear extends Item {
    public DebugClear() {
        super(new Item.Settings().group(ItemGroup.MISC));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity PlayerEntity, Hand hand) {
        Vec3d player = PlayerEntity.getPos();
        for (int xOff = -10; xOff < 10; xOff++) {
            int x = (int) (player.x + xOff);
            for (int yOff = -10; yOff < 10; yOff++) {
                int y = (int) (player.y + yOff);
                for (int zOff = -10; zOff < 10; zOff++) {
                    int z = (int) (player.z + zOff);
                    BlockPos pos = new BlockPos(x, y, z);
                    if (world.canSetBlock(pos)) {
                        world.removeBlock(pos, false);
                    }
                }
            }
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, PlayerEntity.getStackInHand(hand));
    }
}
