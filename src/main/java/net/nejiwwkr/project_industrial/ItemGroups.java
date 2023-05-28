package net.nejiwwkr.project_industrial;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.*;
import static net.nejiwwkr.project_industrial.fluid.ModFluids.BoronBucket;

public class ItemGroups {
    public static final FabricItemGroupBuilder PROJECT_INDUSTRIAL_ITEM_GROUP_BASIC = FabricItemGroupBuilder
            .create(new Identifier(MOD_ID,"item_tab_basic"))
            .icon(() -> new ItemStack(COAL_NUGGET))
            .appendItems(stacks -> {
                Item[] ITEMS =new Item[]{
                        COAL_NUGGET,
                        RAW_IRON_WITH_COAL,RAW_COPPER_WITH_COAL,
                        RAW_LEAD_WITH_COAL,

                        IRON_REAGENT_ITEM,COPPER_REAGENT_ITEM,
                        LEAD_REAGENT_ITEM,

                        LEAD_ORE_ITEM,NICKEL_ORE_ITEM,
                        MANGANESE_ORE_ITEM,MOLYBDENUM_ORE_ITEM,
                        CHROME_ORE_ITEM,

                        DEEPSLATE_LEAD_ORE_ITEM,DEEPSLATE_NICKEL_ORE_ITEM,
                        DEEPSLATE_MANGANESE_ORE_ITEM,DEEPSLATE_MOLYBDENUM_ORE_ITEM,
                        DEEPSLATE_CHROME_ORE_ITEM,

                        LEAD_BLOCK_ITEM,CHROME_BLOCK_ITEM,

                        RAW_LEAD_BLOCK_ITEM,RAW_MANGANESE_BLOCK_ITEM,

                        LEAD_INGOT,NICKEL_INGOT,MANGANESE_INGOT,MOLYBDENUM_INGOT,CHROME_INGOT,

                        LEAD_NUGGET,NICKEL_NUGGET,MANGANESE_NUGGET,MOLYBDENUM_NUGGET,

                        RAW_LEAD,RAW_MANGANESE,RAW_MOLYBDENUM,

                        BoronBucket,
                        NICKEL_CRUCIBLE,
                        THEME_DISC,
                };
                for (Item i : ITEMS) stacks.add(new ItemStack(i));
            });

    public static final FabricItemGroupBuilder PROJECT_INDUSTRIAL_ITEM_GROUP_CONSTRUCTION = FabricItemGroupBuilder
            .create(new Identifier(MOD_ID,"item_tab_construction"))
            .icon(() -> new ItemStack(BORON_BRICK_ITEM))
            .appendItems(stacks -> {
                Item[] ITEMS =new Item[]{
                        BORON_STONE_ITEM,CRYSTALLIZED_BORON_STONE_ITEM,
                        POLISHED_BORON_STONE_ITEM,BORON_BRICK_ITEM,
                        REFRACTORY_CERAMIC_ITEM,REFRACTORY_CERAMIC_STAIR_ITEM,REFRACTORY_CERAMIC_SLAB_ITEM,

                        SALTPETERING_BRICKS_ITEM,
                        ALLOY_FURNACE_ITEM,
                };
                for (Item i : ITEMS) stacks.add(new ItemStack(i));
            });

    public static final FabricItemGroupBuilder PROJECT_INDUSTRIAL_ITEM_GROUP_COMPOUND = FabricItemGroupBuilder
            .create(new Identifier(MOD_ID,"item_tab_compound"))
            .icon(() -> new ItemStack(LEAD_OXIDE))
            .appendItems(stacks -> {
                Item[] ITEMS =new Item[]{
                        LEAD_OXIDE,
                        CALCIUM_OXIDE,
                        POTASH,RAW_POTASSIUM_CARBONATE,
                        LEAD_GLASS_REAGENT,
                        BORAX,
                        MOLYBDENUM_CALCINE
                };
                for (Item i : ITEMS) stacks.add(new ItemStack(i));
            });

    public static void init() {
        PROJECT_INDUSTRIAL_ITEM_GROUP_BASIC.build();
        PROJECT_INDUSTRIAL_ITEM_GROUP_CONSTRUCTION.build();
        PROJECT_INDUSTRIAL_ITEM_GROUP_COMPOUND.build();
    }
}
