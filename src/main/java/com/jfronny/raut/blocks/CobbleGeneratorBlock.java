package com.jfronny.raut.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

import static com.jfronny.raut.modules.MiscModule.COBBLE_GENERATOR;

public class CobbleGeneratorBlock extends Block implements BlockEntityProvider {
    public CobbleGeneratorBlock() {
        super(FabricBlockSettings.of(Material.STONE).build());
    }

    @Override
    public boolean hasBlockEntity() {
        return true;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView view) {
        return new CobbleGeneratorEntity();
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.getBlockTickScheduler().isScheduled(pos, COBBLE_GENERATOR)) {
            world.getBlockTickScheduler().schedule(pos, COBBLE_GENERATOR, 10);
        }
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            CobbleGeneratorEntity entity = (CobbleGeneratorEntity) world.getBlockEntity(pos);
            if (entity.cobbleCount > 0) {
                ItemStack tmp = new ItemStack(Items.COBBLESTONE);
                tmp.setCount(Math.min(entity.cobbleCount, 64));
                entity.cobbleCount -= tmp.getCount();
                entity.markDirty();
                player.giveItemStack(tmp);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        CobbleGeneratorEntity entity = (CobbleGeneratorEntity) world.getBlockEntity(pos);
        if (entity.cobbleCount < 512) {
            entity.cobbleCount++;
            entity.markDirty();
        }
        world.getBlockTickScheduler().schedule(pos, COBBLE_GENERATOR, 90);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        world.getBlockTickScheduler().schedule(pos, COBBLE_GENERATOR, 10);
    }

    @Override
    public void onBroken(IWorld world, BlockPos pos, BlockState state) {
        super.onBroken(world, pos, state);
        if (!world.isClient()) {
            CobbleGeneratorEntity entity = (CobbleGeneratorEntity) world.getBlockEntity(pos);
            while (entity.cobbleCount > 0) {
                ItemStack tmp = new ItemStack(Items.COBBLESTONE);
                tmp.setCount(Math.min(entity.cobbleCount, 64));
                entity.cobbleCount -= tmp.getCount();
                entity.markDirty();
                ItemEntity itemEntity = new ItemEntity(world.getWorld(), pos.getX(), pos.getY(), pos.getZ(), tmp);
                world.spawnEntity(itemEntity);
            }
            if (!world.getBlockTickScheduler().isScheduled(pos, COBBLE_GENERATOR)) {
                world.getBlockTickScheduler().schedule(pos, COBBLE_GENERATOR, 10);
            }
        }
    }
}
