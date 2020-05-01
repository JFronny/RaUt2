package com.jfronny.raut.api;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class GenericPlant extends CropBlock {
    public BlockItem seed;

    public GenericPlant() {
        super(FabricBlockSettings.copy(Blocks.WHEAT).build());
        this.seed = new AliasedBlockItem(this, new Item.Settings().group(ItemGroup.MATERIALS));
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return seed;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
        if (!world.isClient && isMature(state)) {
            List<ItemStack> dropList = getDroppedStacks(state, (ServerWorld) world, pos, null, player, player.getStackInHand(hand));
            DefaultedList<ItemStack> drops = DefaultedList.of();
            drops.addAll(dropList);

            for (ItemStack stack : drops) {
                if (stack.getItem() == seed) {
                    ItemStack seedStack = stack.copy();
                    drops.remove(stack);
                    seedStack.decrement(1);
                    drops.add(seedStack);
                    break;
                }
            }

            world.setBlockState(pos, state.with(AGE, 0));
            ItemScatterer.spawn(world, pos, drops);
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hitResult);
    }
}
