package net.nejiwwkr.project_industrial.mixin;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Oxidizable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Optional;
import java.util.function.Supplier;

@Mixin(Oxidizable.class)
public interface OxidizableMixin {
    /**
     * @author Nejiwwkr
     * @reason Allow bricks to saltpetering
     */
    @Overwrite
    static Optional<Block> getIncreasedOxidationBlock(Block block) {
        Supplier<BiMap<Block, Block>> OXIDATION_LEVEL_INCREASES = Suppliers.memoize(
            () -> ImmutableBiMap
                .<Block,Block>builder()
                .put(Blocks.COPPER_BLOCK, Blocks.EXPOSED_COPPER)
                .put(Blocks.EXPOSED_COPPER, Blocks.WEATHERED_COPPER).put(Blocks.WEATHERED_COPPER, Blocks.OXIDIZED_COPPER).put(Blocks.CUT_COPPER, Blocks.EXPOSED_CUT_COPPER).put(Blocks.EXPOSED_CUT_COPPER, Blocks.WEATHERED_CUT_COPPER).put(Blocks.WEATHERED_CUT_COPPER, Blocks.OXIDIZED_CUT_COPPER).put(Blocks.CUT_COPPER_SLAB, Blocks.EXPOSED_CUT_COPPER_SLAB).put(Blocks.EXPOSED_CUT_COPPER_SLAB, Blocks.WEATHERED_CUT_COPPER_SLAB).put(Blocks.WEATHERED_CUT_COPPER_SLAB, Blocks.OXIDIZED_CUT_COPPER_SLAB).put(Blocks.CUT_COPPER_STAIRS, Blocks.EXPOSED_CUT_COPPER_STAIRS).put(Blocks.EXPOSED_CUT_COPPER_STAIRS, Blocks.WEATHERED_CUT_COPPER_STAIRS).put(Blocks.WEATHERED_CUT_COPPER_STAIRS, Blocks.OXIDIZED_CUT_COPPER_STAIRS)
                //.put(Blocks.BRICKS,SALTPETERING_BRICKS)
                .build()
        );
        return Optional.ofNullable((Block)((BiMap<?, ?>)OXIDATION_LEVEL_INCREASES.get()).get(block));
    }
}
