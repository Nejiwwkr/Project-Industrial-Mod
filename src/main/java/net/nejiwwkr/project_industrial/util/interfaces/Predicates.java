package net.nejiwwkr.project_industrial.util.interfaces;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Deprecated
public interface Predicates {
    static Boolean blockNever(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return false;
    }

    static boolean blockNever(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }
}
