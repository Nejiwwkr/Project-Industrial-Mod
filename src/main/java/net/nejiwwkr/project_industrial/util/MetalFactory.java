package net.nejiwwkr.project_industrial.util;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.nejiwwkr.project_industrial.util.annotation.Essential;

import static net.nejiwwkr.project_industrial.util.OverworldMetalUtil.CreateOverworldOreAndRegistryFeatures;

public class MetalFactory {
    private final String metalName;
    private Item rawMetal;
    private Item rawMetalWithCoal;
    private Item metalIngot;
    private Item metalNugget;
    private Block metalOre;
    private BlockItem metalOreItem;
    private Block deepslateMetalOre;
    private BlockItem deepslateMetalOreItem;
    private Block rawMetalBlock;
    private BlockItem rawMetalBlockItem;
    private Block metalBlock;
    private BlockItem metalBlockItem;
    private Block metalReagent;
    private BlockItem metalReagentItem;
    private final MetalPos pos;
    private boolean initInvoked = false;

    MetalFactory(MetalPos pos,String metalName) {
        this.metalName = metalName;
        this.pos = pos;
    }

    public MetalFactory appendRawMetal(Item rawMetal,Block rawMetalBlock,BlockItem rawMetalBlockItem) {
        this.rawMetal = rawMetal;
        this.rawMetalBlock = rawMetalBlock;
        this.rawMetalBlockItem = rawMetalBlockItem;
        return this;
    }

    public MetalFactory appendReagent(Item rawMetalWithCoal,Block metalReagent,BlockItem metalReagentItem) {
        this.rawMetalWithCoal = rawMetalWithCoal;
        this.metalReagent = metalReagent;
        this.metalReagentItem = metalReagentItem;
        return this;
    }

    public MetalFactory appendBlock(Block metalBlock,BlockItem metalBlockItem) {
        this.metalBlock = metalBlock;
        this.metalBlockItem = metalBlockItem;
        return this;
    }

    public MetalFactory appendIngot(Item metalIngot,Item metalNugget) {
        this.metalIngot = metalIngot;
        this.metalNugget = metalNugget;
        return this;
    }

    public MetalFactory appendOre(Block metalOre, BlockItem metalOreItem, Block deepslateMetalOre, BlockItem deepslateMetalOreItem) {
        this.metalOre = metalOre;
        this.metalOreItem = metalOreItem;
        this.deepslateMetalOre = deepslateMetalOre;
        this.deepslateMetalOreItem = deepslateMetalOreItem;
        return this;
    }

    public MetalFactory worldGen(OreRarity rarity,int maxYOffset) {
        if (pos == MetalPos.OVERWORLD) CreateOverworldOreAndRegistryFeatures(metalName, metalOre, deepslateMetalOre, rarity, maxYOffset);
        return this;
    }

    @Essential
    public void init() {
        OverworldMetalUtil.RegistryOverworldMetalRelatives(metalName,rawMetal, rawMetalWithCoal, metalIngot, metalNugget, metalOre, metalOreItem, deepslateMetalOre, deepslateMetalOreItem, rawMetalBlock, rawMetalBlockItem, metalBlock, metalBlockItem, metalReagent, metalReagentItem);
        this.initInvoked = true;
    }

    //static {
    //    for (MetalFactory m : OverworldMetalUtil.INSTANCES) if (!m.initInvoked) throw new EssentialMethodNotInvokedException(MetalFactory.class);
    //}
}

enum MetalPos {
    OVERWORLD,
    @Deprecated
    NETHERLANDS,
    @Deprecated
    END
}
