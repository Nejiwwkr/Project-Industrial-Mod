package net.nejiwwkr.project_industrial.item.instances;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.nejiwwkr.project_industrial.item.LeadSword;
import net.nejiwwkr.project_industrial.material.MolybdenumMaterial;

import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.MOD_ID;

public class Tools {
    public static final ToolItem LEAD_SWORD = new LeadSword();

    public static final ToolItem MOLYBDENUM_SWORD = new SwordItem(MolybdenumMaterial.INSTANCE,-1,-2.4f,new FabricItemSettings());
    public static final ToolItem MOLYBDENUM_SHOVEL = new ShovelItem(MolybdenumMaterial.INSTANCE,-3,-3f,new FabricItemSettings());
    public static final ToolItem MOLYBDENUM_PICKAXE = new MolybdenumPickaxe();
    public static final ToolItem MOLYBDENUM_AXE = new MolybdenumAxe();
    public static final ToolItem MOLYBDENUM_HOE = new MolybdenumHoe();

    public static final FabricItemGroupBuilder PROJECT_INDUSTRIAL_ITEM_GROUP_TOOLS = FabricItemGroupBuilder
            .create(new Identifier(MOD_ID,"item_tab_tools"))
            .icon(() -> new ItemStack(LEAD_SWORD))
            .appendItems(stacks -> {
                Item[] ITEMS =new Item[]{
                        LEAD_SWORD,
                        MOLYBDENUM_SWORD,MOLYBDENUM_PICKAXE,MOLYBDENUM_AXE,MOLYBDENUM_SHOVEL,MOLYBDENUM_HOE,

                };
                for (Item i : ITEMS) stacks.add(new ItemStack(i));
            });


    public static void init(){
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"lead_sword"),LEAD_SWORD);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"molybdenum_sword"),MOLYBDENUM_SWORD);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"molybdenum_pickaxe"),MOLYBDENUM_PICKAXE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"molybdenum_axe"),MOLYBDENUM_AXE);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"molybdenum_shovel"),MOLYBDENUM_SHOVEL);
        Registry.register(Registry.ITEM,new Identifier(MOD_ID,"molybdenum_hoe"),MOLYBDENUM_HOE);

        PROJECT_INDUSTRIAL_ITEM_GROUP_TOOLS.build();
    }
}

class MolybdenumPickaxe extends PickaxeItem {
    public MolybdenumPickaxe () {
        super(MolybdenumMaterial.INSTANCE,-3,-2.8f,new FabricItemSettings());
    }
}

class MolybdenumAxe extends AxeItem {
    public MolybdenumAxe() {
        super(MolybdenumMaterial.INSTANCE,1,-3f,new FabricItemSettings());
    }
}

class MolybdenumHoe extends HoeItem {
    public MolybdenumHoe () {
        super(MolybdenumMaterial.INSTANCE,-7,1,new FabricItemSettings());
    }
}
