package net.nejiwwkr.project_industrial.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.nejiwwkr.project_industrial.material.LeadPlatedMaterial;
import net.nejiwwkr.project_industrial.util.NBT_TAG_Util;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.LEAD_POISON_ENCHANTMENT;

public class LeadSword extends SwordItem {
    public LeadSword() {
        super(LeadPlatedMaterial.INSTANCE,0,-2.4f,new FabricItemSettings().maxCount(1));
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (stack.hasEnchantments() && !NBT_TAG_Util.containsEnchantment(stack,"lead_poison")) stack.addEnchantment(LEAD_POISON_ENCHANTMENT, 1);

        var text = new TranslatableText("item.project_industrial.lead_sword.tooltip").formatted(Formatting.GRAY);

        tooltip.add(text);
        if (NBT_TAG_Util.containsEnchantment(stack,"lead_poison")) tooltip.remove(text);
    }
}
