package net.nejiwwkr.project_industrial.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;
import net.nejiwwkr.project_industrial.crafting.alloy.MetalType;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CoalNuggetItem extends NuggetItem {
    public CoalNuggetItem() {
        super(MetalType.COAL);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("item.project_industrial.coal_nugget.tooltip"));
    }
}
