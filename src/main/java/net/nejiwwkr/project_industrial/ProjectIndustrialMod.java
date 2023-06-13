package net.nejiwwkr.project_industrial;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.potion.Potion;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nejiwwkr.project_industrial.block.AlloyFurnaceBlock;
import net.nejiwwkr.project_industrial.block.LeadGlass;
import net.nejiwwkr.project_industrial.block.SaltpeteredBlock;
import net.nejiwwkr.project_industrial.block.entity.AlloyFurnaceBlockEntity;
import net.nejiwwkr.project_industrial.crafting.alloy.MetalType;
import net.nejiwwkr.project_industrial.effect.AntidoteStatusEffect;
import net.nejiwwkr.project_industrial.effect.LeadStatusEffect;
import net.nejiwwkr.project_industrial.enchantment.LeadPoisonEnchantment;
import net.nejiwwkr.project_industrial.fluid.ModFluids;
import net.nejiwwkr.project_industrial.item.*;
import net.nejiwwkr.project_industrial.item.instances.Tools;
import net.nejiwwkr.project_industrial.screen.handler.AlloyFurnaceBlockScreenHandler;
import net.nejiwwkr.project_industrial.util.OverworldMetalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.nejiwwkr.project_industrial.Sounds.*;


public class ProjectIndustrialMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "project_industrial";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Item.Settings COMPOUND_SETTINGS = new FabricItemSettings().maxCount(16);

	public static final Item COAL_NUGGET = new CoalNuggetItem();
	public static final Item RAW_IRON_WITH_COAL = new Item(new FabricItemSettings());
	public static final Item RAW_COPPER_WITH_COAL = new Item(new FabricItemSettings());
	//LEAD
	public static final Item RAW_LEAD = new Item(new FabricItemSettings());
	public static final Item LEAD_NUGGET = new NuggetItem(MetalType.LEAD);
	public static final Item LEAD_INGOT = new IngotItem(MetalType.LEAD);
	public static final Item RAW_LEAD_WITH_COAL = new Item(new FabricItemSettings());
	//MANGANESE
	public static final Item RAW_MANGANESE = new Item(new FabricItemSettings());
	public static final Item MANGANESE_NUGGET = new NuggetItem(MetalType.MANGANESE);
	public static final Item MANGANESE_INGOT = new IngotItem(MetalType.MANGANESE);
	//CHROME
	public static final Item CHROME_INGOT = new IngotItem(MetalType.CHROME);
	//MOLYBDENUM
	public static final Item RAW_MOLYBDENUM = new Item(new FabricItemSettings());
	public static final Item MOLYBDENUM_NUGGET = new NuggetItem(MetalType.MOLYBDENUM);
	public static final Item MOLYBDENUM_INGOT = new IngotItem(MetalType.MOLYBDENUM);
	//NICKEL
	public static final Item NICKEL_NUGGET = new NuggetItem(MetalType.NICKEL);
	public static final Item NICKEL_INGOT = new IngotItem(MetalType.NICKEL);

	public static final ChemicalItemWithColor LEAD_OXIDE = new ChemicalItemWithColor(0xff989ccd,"lead_light_blue");
	public static final ChemicalItemWithColor CALCIUM_OXIDE = new ChemicalItemWithColor(0xffec6941,"calcium_orange");
	public static final Item POTASH = new Item(COMPOUND_SETTINGS);
	public static final ChemicalItemWithColor RAW_POTASSIUM_CARBONATE = new ChemicalItemWithColor(0xffa781df,"potassium_purple");
	public static final Item LEAD_GLASS_REAGENT = new Item(COMPOUND_SETTINGS);
	public static final ChemicalItemWithColor MOLYBDENUM_CALCINE = new ChemicalItemWithColor(0xffabcd98,"molybdenum_olivine");


	//BORON
	public static final Block BORON_STONE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(0.1F,0.5F).sounds(ULEXITE_BSG));
	public static final BlockItem BORON_STONE_ITEM = new BlockItem(BORON_STONE,new FabricItemSettings());
	public static final Block CRYSTALLIZED_BORON_STONE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(0.5F,1.0F).sounds(ULEXITE_BSG));
	public static final BlockItem CRYSTALLIZED_BORON_STONE_ITEM = new BlockItem(CRYSTALLIZED_BORON_STONE,new FabricItemSettings());
	public static final Block POLISHED_BORON_STONE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(0.6F,1.2F).sounds(ULEXITE_BSG));
	public static final BlockItem POLISHED_BORON_STONE_ITEM = new BlockItem(POLISHED_BORON_STONE,new FabricItemSettings());
	public static final Block BORON_BRICK = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(0.6F,1.2F).sounds(ULEXITE_BSG));
	public static final BlockItem BORON_BRICK_ITEM = new BlockItem(BORON_BRICK,new FabricItemSettings());
	public static final Item BORAX = new Item(COMPOUND_SETTINGS);

	//REFRACTORY CERAMIC
	public static final Block REFRACTORY_CERAMIC = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(4f,6).sounds(CERAMIC_BSG));
	public static final BlockItem REFRACTORY_CERAMIC_ITEM = new BlockItem(REFRACTORY_CERAMIC,new FabricItemSettings());
	public static final StairsBlock REFRACTORY_CERAMIC_STAIR = new StairsBlock(REFRACTORY_CERAMIC.getDefaultState(), FabricBlockSettings.copy(REFRACTORY_CERAMIC).strength(4f,6));
	public static final BlockItem REFRACTORY_CERAMIC_STAIR_ITEM = new BlockItem(REFRACTORY_CERAMIC_STAIR,new FabricItemSettings());
	public static final SlabBlock REFRACTORY_CERAMIC_SLAB = new SlabBlock(FabricBlockSettings.copy(REFRACTORY_CERAMIC).strength(4f,6));
	public static final BlockItem REFRACTORY_CERAMIC_SLAB_ITEM = new BlockItem(REFRACTORY_CERAMIC_SLAB,new FabricItemSettings());

	public static final MusicDiscItem THEME_DISC = new ThemeSongMusicDisc();

	public static final Block IRON_REAGENT = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(1.0F,4F).sounds(BlockSoundGroup.STONE));
	public static final BlockItem IRON_REAGENT_ITEM = new BlockItem(IRON_REAGENT,new FabricItemSettings());
	public static final Block COPPER_REAGENT = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(1.1F,4F).sounds(BlockSoundGroup.STONE));
	public static final BlockItem COPPER_REAGENT_ITEM = new BlockItem(COPPER_REAGENT,new FabricItemSettings());
	public static final Block LEAD_REAGENT = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(1.1F,4F).sounds(BlockSoundGroup.STONE));
	public static final BlockItem LEAD_REAGENT_ITEM = new BlockItem(LEAD_REAGENT,new FabricItemSettings());

	public static final Block LEAD_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(2.0F,4F).sounds(BlockSoundGroup.STONE));
	public static final Block DEEPSLATE_LEAD_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(2.5F,6F).sounds(BlockSoundGroup.DEEPSLATE));
	public static final Block RAW_LEAD_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(2.5F,6F).sounds(BlockSoundGroup.STONE));
	public static final Block LEAD_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3F,6F).sounds(METAL_BLOCK_BSG));
	public static final BlockItem LEAD_ORE_ITEM = new BlockItem(LEAD_ORE,new FabricItemSettings());
	public static final BlockItem DEEPSLATE_LEAD_ORE_ITEM = new BlockItem(DEEPSLATE_LEAD_ORE,new FabricItemSettings());
	public static final BlockItem RAW_LEAD_BLOCK_ITEM = new BlockItem(RAW_LEAD_BLOCK,new FabricItemSettings());
	public static final BlockItem LEAD_BLOCK_ITEM = new BlockItem(LEAD_BLOCK,new FabricItemSettings());

	public static final Block NICKEL_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(2.8F,6F).sounds(BlockSoundGroup.STONE));
	public static final Block DEEPSLATE_NICKEL_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.5F,8F).sounds(BlockSoundGroup.DEEPSLATE));
	public static final BlockItem NICKEL_ORE_ITEM = new BlockItem(NICKEL_ORE,new FabricItemSettings());
	public static final BlockItem DEEPSLATE_NICKEL_ORE_ITEM = new BlockItem(DEEPSLATE_NICKEL_ORE,new FabricItemSettings());

	public static final Block MANGANESE_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(1.8F,6F).sounds(BlockSoundGroup.STONE));
	public static final Block DEEPSLATE_MANGANESE_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(2.0F,8F).sounds(BlockSoundGroup.DEEPSLATE));
	public static final Block RAW_MANGANESE_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(2.0F,8F).sounds(BlockSoundGroup.STONE));
	public static final BlockItem MANGANESE_ORE_ITEM = new BlockItem(MANGANESE_ORE,new FabricItemSettings());
	public static final BlockItem DEEPSLATE_MANGANESE_ORE_ITEM = new BlockItem(DEEPSLATE_MANGANESE_ORE,new FabricItemSettings());
	public static final BlockItem RAW_MANGANESE_BLOCK_ITEM = new BlockItem(RAW_MANGANESE_BLOCK,new FabricItemSettings());

	public static final Block MOLYBDENUM_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0F,6F).sounds(BlockSoundGroup.STONE));
	public static final Block DEEPSLATE_MOLYBDENUM_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(4.5F,8F).sounds(BlockSoundGroup.DEEPSLATE));
	public static final BlockItem MOLYBDENUM_ORE_ITEM = new BlockItem(MOLYBDENUM_ORE,new FabricItemSettings());
	public static final BlockItem DEEPSLATE_MOLYBDENUM_ORE_ITEM = new BlockItem(DEEPSLATE_MOLYBDENUM_ORE,new FabricItemSettings());

	public static final Block CHROME_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(1.8F,6F).sounds(BlockSoundGroup.STONE));
	public static final Block DEEPSLATE_CHROME_ORE = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(2.0F,8F).sounds(BlockSoundGroup.DEEPSLATE));
	public static final Block CHROME_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3F,6F).sounds(METAL_BLOCK_BSG));
	public static final BlockItem CHROME_ORE_ITEM = new BlockItem(CHROME_ORE,new FabricItemSettings());
	public static final BlockItem DEEPSLATE_CHROME_ORE_ITEM = new BlockItem(DEEPSLATE_CHROME_ORE,new FabricItemSettings());
	public static final BlockItem CHROME_BLOCK_ITEM = new BlockItem(CHROME_BLOCK,new FabricItemSettings());

	public static final Item NICKEL_CRUCIBLE = new Item(new FabricItemSettings().maxCount(1));

	public static final Block SALTPETERING_BRICKS = new SaltpeteredBlock(Blocks.BRICKS);
	public static final BlockItem SALTPETERING_BRICKS_ITEM = new BlockItem(SALTPETERING_BRICKS,new FabricItemSettings());

	public static final Block ALLOY_FURNACE = new AlloyFurnaceBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(2.5f,4));
	public static final BlockItem ALLOY_FURNACE_ITEM = new BlockItem(ALLOY_FURNACE,new FabricItemSettings());

	public static final Block LEAD_GLASS = new LeadGlass(FabricBlockSettings.copy(Blocks.GLASS).blockVision(AbstractBlock.AbstractBlockState::isTranslucent));
	public static final BlockItem LEAD_GLASS_ITEM = new BlockItem(LEAD_GLASS,new FabricItemSettings());

	public static final StatusEffect LEAD_POISON = new LeadStatusEffect();
	public static final Enchantment LEAD_POISON_ENCHANTMENT = new LeadPoisonEnchantment();
	public static final Potion LEAD_SOLUTION = new Potion(
			"lead_solution",
			new StatusEffectInstance(LEAD_POISON,2400,0));

	public static final StatusEffect ANTIDOTE = new AntidoteStatusEffect();
	public static final Potion APPLE_VINEGAR = new Potion(
			"apple_vinegar",
			new StatusEffectInstance(StatusEffects.REGENERATION,1200,0),
			new StatusEffectInstance(StatusEffects.SPEED,1200,0),
			new StatusEffectInstance(ANTIDOTE));

	public static BlockEntityType<AlloyFurnaceBlockEntity> ALLOY_FURNACE_ENTITY;
	public static ScreenHandlerType<AlloyFurnaceBlockScreenHandler> AF_SCREEN_HANDLER;

	static {
		//noinspection deprecation
		AF_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(MOD_ID,"alloy_furnace"), AlloyFurnaceBlockScreenHandler::new);
	}

	//TODO 红砖块与芒硝
	//TODO 钼焙砂+
	//TODO BORAX+
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ALLOY_FURNACE_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,new Identifier(MOD_ID,"alloy_furnace_entity"), FabricBlockEntityTypeBuilder.create(AlloyFurnaceBlockEntity::new,ALLOY_FURNACE).build(null));

		Sounds.init();
		ModFluids.init();
		
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"coal_nugget"),COAL_NUGGET);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"raw_iron_with_coal"),RAW_IRON_WITH_COAL);
		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"iron_reagent"),IRON_REAGENT);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"iron_reagent"),IRON_REAGENT_ITEM);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"raw_copper_with_coal"),RAW_COPPER_WITH_COAL);
		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"copper_reagent"),COPPER_REAGENT);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"copper_reagent"),COPPER_REAGENT_ITEM);

		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"lead_oxide"),LEAD_OXIDE);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"calcium_oxide"),CALCIUM_OXIDE);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"potash"),POTASH);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"raw_potassium_carbonate"),RAW_POTASSIUM_CARBONATE);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"lead_glass_reagent"),LEAD_GLASS_REAGENT);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"borax"),BORAX);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"molybdenum_calcine"),MOLYBDENUM_CALCINE);

		//BORON
		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"boron_stone"),BORON_STONE);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"boron_stone"),BORON_STONE_ITEM);
		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"crystallized_boron_stone"),CRYSTALLIZED_BORON_STONE);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"crystallized_boron_stone"),CRYSTALLIZED_BORON_STONE_ITEM);
		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"polished_boron_stone"),POLISHED_BORON_STONE);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"polished_boron_stone"),POLISHED_BORON_STONE_ITEM);
		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"boron_brick"),BORON_BRICK);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"boron_brick"),BORON_BRICK_ITEM);

		//CERAMIC
		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"refractory_ceramic"),REFRACTORY_CERAMIC);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"refractory_ceramic"),REFRACTORY_CERAMIC_ITEM);
		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"refractory_ceramic_stairs"),REFRACTORY_CERAMIC_STAIR);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"refractory_ceramic_stairs"),REFRACTORY_CERAMIC_STAIR_ITEM);
		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"refractory_ceramic_slab"),REFRACTORY_CERAMIC_SLAB);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"refractory_ceramic_slab"),REFRACTORY_CERAMIC_SLAB_ITEM);

		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"nickel_crucible"),NICKEL_CRUCIBLE);

		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"saltpetering_bricks"),SALTPETERING_BRICKS);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"saltpetering_bricks"),SALTPETERING_BRICKS_ITEM);

		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"alloy_furnace"),ALLOY_FURNACE);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"alloy_furnace"),ALLOY_FURNACE_ITEM);

		Registry.register(Registry.BLOCK,new Identifier(MOD_ID,"lead_glass"),LEAD_GLASS);
		Registry.register(Registry.ITEM,new Identifier(MOD_ID,"lead_glass"),LEAD_GLASS_ITEM);

		Registry.register(Registry.STATUS_EFFECT,new Identifier(MOD_ID,"lead_poison"),LEAD_POISON);
		Registry.register(Registry.ENCHANTMENT,new Identifier(MOD_ID,"lead_poison_enchantment"),LEAD_POISON_ENCHANTMENT);
		Registry.register(Registry.POTION,new Identifier(MOD_ID,"lead_solution"),LEAD_SOLUTION);
		Registry.register(Registry.STATUS_EFFECT,new Identifier(MOD_ID,"antidote"),ANTIDOTE);
		Registry.register(Registry.POTION,new Identifier(MOD_ID,"apple_vinegar"),APPLE_VINEGAR);

		OverworldMetalUtil.init();
		ItemGroups.init();
		Tools.init();
	}
}