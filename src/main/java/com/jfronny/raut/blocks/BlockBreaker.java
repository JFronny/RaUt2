package com.jfronny.raut.blocks;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import static com.jfronny.raut.modules.MiscModule.BLOCK_BREAKER;
import static com.jfronny.raut.modules.MiscModule.UNBREAKABLE;

public class BlockBreaker extends FacingBlock {
    public BlockBreaker() {
        super(FabricBlockSettings.of(Material.STONE).build());
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.SOUTH));
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos neighborPos, boolean moved) {
        if (!world.isClient) {
            Direction direction = state.get(FACING);
            BlockPos mov = pos.offset(direction);
            BlockState target = world.getBlockState(mov);
            if (checkPowered(world, pos, direction) && (neighborPos.getX() != mov.getX() || neighborPos.getY() != mov.getY() || neighborPos.getZ() != mov.getZ())
                    && target.getBlock().getHardness(target, world, mov) >= 0 && !target.getBlock().matches(UNBREAKABLE)) {
                world.breakBlock(mov, true);
            }
        }
    }

    private boolean checkPowered(World world, BlockPos pos, Direction face) {
        Direction[] var4 = Direction.values();
        int var5 = var4.length;

        int var6;
        for (var6 = 0; var6 < var5; ++var6) {
            Direction direction = var4[var6];
            if (direction != face && world.isEmittingRedstonePower(pos.offset(direction), direction)) {
                return true;
            }
        }

        if (world.isEmittingRedstonePower(pos, Direction.DOWN)) {
            return true;
        } else {
            BlockPos blockPos = pos.up();
            Direction[] var10 = Direction.values();
            var6 = var10.length;

            for (int var11 = 0; var11 < var6; ++var11) {
                Direction direction2 = var10[var11];
                if (direction2 != Direction.DOWN && world.isEmittingRedstonePower(blockPos.offset(direction2), direction2)) {
                    return true;
                }
            }

            return false;
        }
    }

    @Override
    public boolean isSimpleFullBlock(BlockState state, BlockView view, BlockPos pos) {
        return false;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public boolean canPlaceAtSide(BlockState world, BlockView view, BlockPos pos, BlockPlacementEnvironment env) {
        return false;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerLookDirection().getOpposite().getOpposite());
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean moved) {
        neighborUpdate(state, world, pos, BLOCK_BREAKER, pos, moved);
    }
}
