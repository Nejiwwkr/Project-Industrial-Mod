package net.nejiwwkr.project_industrial.block;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementProgress;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.nejiwwkr.project_industrial.util.C;
import net.nejiwwkr.project_industrial.util.NBT_TAG_Util;

import java.util.Objects;

import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.LEAD_POISON;

public class LeadGlass extends Block {
    public LeadGlass(Settings settings) {
        super(settings);
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return 1.0f;
    }

    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
       return true;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1.0f, 1.0f);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);

        var d = world.getDifficulty();
        double χ = switch (d) {
            case PEACEFUL -> 0.01;
            case EASY -> 0.1;
            case NORMAL -> 0.2;
            case HARD -> 0.3;
        };

        if (!NBT_TAG_Util.containsEnchantment(player.getMainHandStack(),"silk") && Math.random() <= χ) {
            player.addStatusEffect(new StatusEffectInstance(LEAD_POISON, (int) (200 * Math.random() + 1),0));
            conditionIsMet(player);
        }
    }

    private void conditionIsMet(PlayerEntity player) {
        if(!player.world.isClient()) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            Identifier id = new Identifier(C.MOD_ID, "lead_glass");
            Advancement advancement = Objects.requireNonNull(serverPlayer.getServer()).getAdvancementLoader().get(id);
            if(advancement == null) return;

            AdvancementProgress progress = serverPlayer.getAdvancementTracker().getProgress(advancement);
            if(!progress.isDone()) {
                serverPlayer.getAdvancementTracker().grantCriterion(advancement,"lead_glass");
            }
        }
    }
}

