package net.nejiwwkr.project_industrial.fluid;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.MOD_ID;

public class ModFluids {
    public static FlowableFluid StillBoronFluid;
    public static FlowableFluid FlowingBoronFluid;
    public static Block BoronFluidBlock;
    public static Item BoronBucket;

    public static void init() {
        StillBoronFluid = Registry.register(Registry.FLUID,new Identifier(MOD_ID,"boron_fluid"),new BoronFluid.Still());
        FlowingBoronFluid = Registry.register(Registry.FLUID,new Identifier(MOD_ID,"flowing_boron_fluid"),new BoronFluid.Flowing());
        BoronFluidBlock = Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"boron_fluid_block"),new FluidBlock(StillBoronFluid, FabricBlockSettings.copyOf(Blocks.WATER)));
        BoronBucket = Registry.register(Registry.ITEM,new Identifier(MOD_ID,"boron_bucket"),new BucketItem(StillBoronFluid,new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));
    }
}
