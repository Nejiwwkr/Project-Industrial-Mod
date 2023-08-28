package net.nejiwwkr.project_industrial.item.alloy;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.nejiwwkr.project_industrial.crafting.AlloyUtil;
import net.nejiwwkr.project_industrial.item.abstract_mod_item.ProjectIndustrialInstructedFlatItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AlloyNuggetItem extends ProjectIndustrialInstructedFlatItem {

    public AlloyNuggetItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        AlloyUtil.appendTooltip(stack, world, tooltip, context);
    }
}
