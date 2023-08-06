package net.nejiwwkr.project_industrial.util;


import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.nejiwwkr.project_industrial.util.annotation.Essential;

import javax.annotation.Nullable;
import java.util.Arrays;

import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.*;

@Essential
public class OverworldMetalUtil {
    private static void RegistryOverworldOre (String metalName, ConfiguredFeature<?, ?> CF, PlacedFeature PF,ConfiguredFeature<?, ?> DeepslateCF, PlacedFeature DeepslatePF) {
        Identifier oreId = new Identifier(C.MOD_ID,"overworld_"+metalName.toLowerCase()+"ore");
        Identifier deepslateOreId = new Identifier(C.MOD_ID,"overworld_deepslate"+metalName.toLowerCase()+"ore");

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,oreId, CF);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, oreId, PF);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, oreId));


        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,deepslateOreId, DeepslateCF);
        Registry.register(BuiltinRegistries.PLACED_FEATURE, deepslateOreId, DeepslatePF);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, deepslateOreId));
    }


    /**
     *
     * @param metalName 小写金属名
     * @param ore 普通矿石对应方块
     * @param deepslateOre 深板岩对应方块
     * @param size 普通矿石矿脉大小
     * @param deepslateSize 深板岩矿石矿脉大小
     * @param count 普通矿石每区块生产量
     * @param deepslateCount 深板岩矿石每区块生成量
     * @param maxYOffset 最大生成高度
     */
    @Deprecated
    public static void CreateOverworldOreAndRegistryFeatures(String metalName, Block ore, Block deepslateOre, int size, int deepslateSize, int count, int deepslateCount, int maxYOffset) {
        ConfiguredFeature<?, ?> CF_ore = new ConfiguredFeature<>(
                Feature.ORE, new OreFeatureConfig(
                OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                ore.getDefaultState(),
                size)); // 矿脉大小

        PlacedFeature PF_ore = new PlacedFeature(
                RegistryEntry.of(CF_ore),
                Arrays.asList(
                        CountPlacementModifier.of(count), // 每个区块的矿脉数量
                        SquarePlacementModifier.of(), // 水平传播
                        HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(maxYOffset))
                )); // 高度


        ConfiguredFeature<?, ?> CF_deepslate = new ConfiguredFeature<>(
                Feature.ORE, new OreFeatureConfig(
                OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                deepslateOre.getDefaultState(),
                deepslateSize)); // 矿脉大小

        PlacedFeature PF_deepslate = new PlacedFeature(
                RegistryEntry.of(CF_deepslate),
                Arrays.asList(
                        CountPlacementModifier.of(deepslateCount), // 每个区块的矿脉数量
                        SquarePlacementModifier.of(), // 水平传播
                        HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(0))
                )); // 高度

        RegistryOverworldOre(metalName,CF_ore,PF_ore,CF_deepslate,PF_deepslate);
    }


    /**
     *
     * @param metalName 小写金属名
     * @param ore 普通矿石对应方块
     * @param deepslateOre 深板岩对应方块
     * @param rarity 生成概率
     *               <p>参考：</p>
     *               <p>钼-Legendary</p>
     *               <p>锰-Rare</p>
     *               <p>镍-Uncommon</p>
     *               <p>铅-Normal</p>
     *               <p>铜-Common</br>
     * @param maxYOffset 最大生成高度
     */
    public static void CreateOverworldOreAndRegistryFeatures(String metalName, Block ore, Block deepslateOre, OreRarity rarity, int maxYOffset) {
        int size = rarity.getSize();
        int deepslateSize = rarity.getDeepslateSize();
        int count = rarity.getCount();
        int deepslateCount = rarity.getDeepslateCount();
        ConfiguredFeature<?, ?> CF_ore = new ConfiguredFeature<>(
                Feature.ORE, new OreFeatureConfig(
                OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                ore.getDefaultState(),
                size)); // 矿脉大小

        PlacedFeature PF_ore = new PlacedFeature(
                RegistryEntry.of(CF_ore),
                Arrays.asList(
                        CountPlacementModifier.of(count), // 每个区块的矿脉数量
                        SquarePlacementModifier.of(), // 水平传播
                        HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.fixed(maxYOffset))
                )); // 高度


        ConfiguredFeature<?, ?> CF_deepslate = new ConfiguredFeature<>(
                Feature.ORE, new OreFeatureConfig(
                OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                deepslateOre.getDefaultState(),
                deepslateSize)); // 矿脉大小

        PlacedFeature PF_deepslate = new PlacedFeature(
                RegistryEntry.of(CF_deepslate),
                Arrays.asList(
                        CountPlacementModifier.of(deepslateCount), // 每个区块的矿脉数量
                        SquarePlacementModifier.of(), // 水平传播
                        HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(0))
                )); // 高度

        RegistryOverworldOre(metalName,CF_ore,PF_ore,CF_deepslate,PF_deepslate);
    }


    /**
     * @param metalName 小写金属名
     * @param rawMetal 粗矿石
     * @param rawMetalWithCoal 粗矿与碳
     * @param metalIngot 金属锭
     * @param metalNugget 金属粒
     * @param metalOre 普通矿石方块
     * @param metalOreItem 普通矿石物品
     * @param deepslateMetalOre 深板岩矿石方块
     * @param deepslateMetalOreItem 深板岩矿石物品
     * @param rawMetalBlock 粗矿块
     * @param rawMetalBlockItem 粗矿块物品
     * @param metalBlock 金属块
     * @param metalBlockItem 金属块物品
     * @param metalReagent 金属反应物方块
     * @param metalReagentItem 金属反应物
     * @since 1.0.0a
     */
    public static void RegistryOverworldMetalRelatives (
            @Nullable String metalName,
            @Nullable Item rawMetal, @Nullable Item rawMetalWithCoal, @Nullable Item metalIngot, @Nullable Item metalNugget,
            @Nullable Block metalOre, @Nullable BlockItem metalOreItem,
            @Nullable Block deepslateMetalOre, @Nullable BlockItem deepslateMetalOreItem,
            @Nullable Block rawMetalBlock, @Nullable BlockItem rawMetalBlockItem,
            @Nullable Block metalBlock, @Nullable BlockItem metalBlockItem,
            @Nullable Block metalReagent, @Nullable BlockItem metalReagentItem
    ) {
        if (rawMetal != null)Registry.register(Registry.ITEM,new Identifier(C.MOD_ID,"raw_"+metalName),rawMetal);
        if (metalIngot != null)Registry.register(Registry.ITEM,new Identifier(C.MOD_ID,metalName+"_ingot"),metalIngot);
        if (rawMetalWithCoal != null)Registry.register(Registry.ITEM,new Identifier(C.MOD_ID,"raw_"+metalName+"_with_coal"),rawMetalWithCoal);
        if (metalNugget != null)Registry.register(Registry.ITEM,new Identifier(C.MOD_ID,metalName+"_nugget"),metalNugget);

        if (metalOre != null)Registry.register(Registry.BLOCK,new Identifier(C.MOD_ID,metalName+"_ore"),metalOre);
        if (deepslateMetalOre != null)Registry.register(Registry.BLOCK,new Identifier(C.MOD_ID,"deepslate_"+metalName+"_ore"),deepslateMetalOre);
        if (rawMetalBlock != null)Registry.register(Registry.BLOCK,new Identifier(C.MOD_ID,"raw_"+metalName+"_block"),rawMetalBlock);
        if (metalBlock != null)Registry.register(Registry.BLOCK,new Identifier(C.MOD_ID,metalName+"_block"),metalBlock);
        if (metalOreItem != null)Registry.register(Registry.ITEM,new Identifier(C.MOD_ID,metalName+"_ore"),metalOreItem);
        if (deepslateMetalOreItem != null)Registry.register(Registry.ITEM,new Identifier(C.MOD_ID,"deepslate_"+metalName+"_ore"),deepslateMetalOreItem);
        if (rawMetalBlockItem != null)Registry.register(Registry.ITEM,new Identifier(C.MOD_ID,"raw_"+metalName+"_block"),rawMetalBlockItem);
        if (metalBlockItem != null)Registry.register(Registry.ITEM,new Identifier(C.MOD_ID,metalName+"_block"),metalBlockItem);

        if (metalReagent != null)Registry.register(Registry.BLOCK,new Identifier(C.MOD_ID,metalName+"_reagent"),metalReagent);
        if (metalReagentItem != null)Registry.register(Registry.ITEM,new Identifier(C.MOD_ID,metalName+"_reagent"),metalReagentItem);
    }

    private static final MetalFactory LEAD_FACTORY =  new MetalFactory(MetalPos.OVERWORLD,"lead");
    private static final MetalFactory NICKEL_FACTORY =  new MetalFactory(MetalPos.OVERWORLD,"nickel");
    private static final MetalFactory MANGANESE_FACTORY =  new MetalFactory(MetalPos.OVERWORLD,"manganese");
    private static final MetalFactory MOLYBDENUM_FACTORY =  new MetalFactory(MetalPos.OVERWORLD,"molybdenum");
    private static final MetalFactory CHROME_FACTORY =  new MetalFactory(MetalPos.OVERWORLD,"chrome");
    public static final MetalFactory[] INSTANCES = {LEAD_FACTORY,NICKEL_FACTORY,MANGANESE_FACTORY,MOLYBDENUM_FACTORY,CHROME_FACTORY};


    /**
     * 初始化所有矿物，矿物生成
     * @see #RegistryOverworldMetalRelatives(String, Item, Item, Item, Item, Block, BlockItem, Block, BlockItem, Block, BlockItem, Block, BlockItem, Block, BlockItem)
     * @see #CreateOverworldOreAndRegistryFeatures(String, Block, Block, OreRarity, int)
     */
    public static void init(){
        CreateOverworldOreAndRegistryFeatures("boron",BORON_STONE,BORON_STONE,OreRarity.SUPERNATANT_STONE,80);

        LEAD_FACTORY.appendRawMetal(RAW_LEAD,RAW_LEAD_BLOCK,RAW_LEAD_BLOCK_ITEM)
                .appendReagent(RAW_LEAD_WITH_COAL,LEAD_REAGENT,LEAD_REAGENT_ITEM)
                .appendBlock(LEAD_BLOCK,LEAD_BLOCK_ITEM)
                .appendIngot(LEAD_INGOT,LEAD_NUGGET)
                .appendOre(LEAD_ORE,LEAD_ORE_ITEM,DEEPSLATE_LEAD_ORE,DEEPSLATE_LEAD_ORE_ITEM)
                .worldGen(OreRarity.NORMAL,64)
                .init();

        NICKEL_FACTORY.appendIngot(NICKEL_INGOT,NICKEL_NUGGET)
                .appendOre(NICKEL_ORE,NICKEL_ORE_ITEM,DEEPSLATE_NICKEL_ORE,DEEPSLATE_NICKEL_ORE_ITEM)
                .worldGen(OreRarity.UNCOMMON,32)
                .init();

        MANGANESE_FACTORY.appendRawMetal(RAW_MANGANESE,RAW_MANGANESE_BLOCK,RAW_MANGANESE_BLOCK_ITEM)
                .appendIngot(MANGANESE_INGOT,MANGANESE_NUGGET)
                .appendOre(MANGANESE_ORE,MANGANESE_ORE_ITEM,DEEPSLATE_MANGANESE_ORE,DEEPSLATE_MANGANESE_ORE_ITEM)
                .worldGen(OreRarity.RARE,64)
                .init();

        MOLYBDENUM_FACTORY.appendRawMetal(RAW_MOLYBDENUM,null,null)
                .appendIngot(MOLYBDENUM_INGOT,MOLYBDENUM_NUGGET)
                .appendOre(MOLYBDENUM_ORE,MOLYBDENUM_ORE_ITEM,DEEPSLATE_MOLYBDENUM_ORE,DEEPSLATE_MOLYBDENUM_ORE_ITEM)
                .worldGen(OreRarity.LEGENDARY,16)
                .init();

        CHROME_FACTORY.appendIngot(CHROME_INGOT,CHROME_NUGGET)
                .appendBlock(CHROME_BLOCK,CHROME_BLOCK_ITEM)
                .appendOre(CHROME_ORE,CHROME_ORE_ITEM,DEEPSLATE_CHROME_ORE,DEEPSLATE_CHROME_ORE_ITEM)
                .worldGen(OreRarity.UNCOMMON,32)
                .init();
    }
}

