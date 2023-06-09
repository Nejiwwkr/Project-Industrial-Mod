package net.nejiwwkr.project_industrial.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.nejiwwkr.project_industrial.util.Testing;

import java.util.Random;

public class SaltpeterableBlock extends Block{
    private final Block goalBlock;
    private int currentTick;
    public static final int TICK_TIME = 20*5;

    public SaltpeterableBlock(Block goalBlock) {
        super(Settings.of(Material.STONE).requiresTool().strength(2.0F, 6.0F));
        this.goalBlock = goalBlock;
    }


    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);

        if (currentTick < (TICK_TIME + 20)) {
            currentTick++;
        }else {
            return;
        }
        Testing.checkTickerIsWorking(world);

        if (currentTick == TICK_TIME) {
            world.setBlockState(pos,goalBlock.getDefaultState());
        }
    }
}
