package com.jfronny.raut.tools;

import com.jfronny.raut.modules.MiscModule;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class AngelBlockItem extends BlockItem {
    public AngelBlockItem() {
        super(MiscModule.ANGEL_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            Vec3d head = user.getRotationVec(0f);
            double x = user.getX() + 3 * head.x;
            double y = 1.5 + user.getY() + 3 * head.y;
            double z = user.getZ() + 3 * head.z;
            BlockPos pos = new BlockPos(x, y, z);
            if (world.canSetBlock(pos) && world.isAir(pos) || !world.getFluidState(pos).isEmpty()) {
                world.setBlockState(pos, MiscModule.ANGEL_BLOCK.getDefaultState());
                if (!user.abilities.creativeMode) {
                    user.getStackInHand(hand).decrement(1);
                }
            }
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, user.getStackInHand(hand));
    }
}
