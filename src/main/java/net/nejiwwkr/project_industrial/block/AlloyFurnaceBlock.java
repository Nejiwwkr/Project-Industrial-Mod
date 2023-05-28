package net.nejiwwkr.project_industrial.block;

import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nejiwwkr.project_industrial.block.entity.AlloyFurnaceBlockEntity;
import org.jetbrains.annotations.Nullable;

import static net.nejiwwkr.project_industrial.block.entity.AlloyFurnaceBlockEntity.tick;


public class AlloyFurnaceBlock extends BlockWithEntity{
    public AlloyFurnaceBlock(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AlloyFurnaceBlockEntity(pos,state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.isOf(newState.getBlock())) {
            return;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof Inventory) {
            ItemScatterer.spawn(world,pos, (Inventory) blockEntity);
            world.updateComparators(pos,this);
        }
        super.onStateReplaced(state,world,pos,newState,moved);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof AlloyFurnaceBlockEntity) {
            player.openHandledScreen((AlloyFurnaceBlockEntity) blockEntity);
            player.incrementStat(Stats.INTERACT_WITH_SMITHING_TABLE);
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        //return world.isClient ? null : checkType(type, ProjectIndustrialMod.ALLOY_FURNACE_ENTITY,AlloyFurnaceBlockEntity::tick);
        //return super.getTicker(world, state, type);
        return (world1,pos,state1,blockEntity) -> tick(world1,pos,state1, (AlloyFurnaceBlockEntity) blockEntity);
    }
}
