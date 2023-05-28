package net.nejiwwkr.project_industrial.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.RAW_POTASSIUM_CARBONATE;

public class SaltpeteredBlock extends Block {
    private final Block originBlock;


    public SaltpeteredBlock(Block originBlock) {
        super(Settings.of(Material.STONE).requiresTool().strength(2.0F, 6.0F));
        this.originBlock = originBlock;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.setBlockState(pos,originBlock.getDefaultState());

        player.giveItemStack(new ItemStack(RAW_POTASSIUM_CARBONATE,1));
        //ItemEntity i = player.dropItem(new ItemStack(RAW_POTASSIUM_CARBONATE, 1), true, false);
        //if (i != null) i.teleport(pos.getX(), pos.getY() + 0.9, pos.getZ());

        return ActionResult.SUCCESS;
    }
}
