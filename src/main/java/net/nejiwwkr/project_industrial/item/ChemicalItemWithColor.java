package net.nejiwwkr.project_industrial.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import net.nejiwwkr.project_industrial.util.interfaces.MColor;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.COMPOUND_SETTINGS;

/**
 * <p>具有焰色反应的化合物</p>
 * @author Nejiwwkr
 * @since 1.0.0
 */
public class ChemicalItemWithColor extends Item implements MColor {
    int color;
    String text;
    public ChemicalItemWithColor(int color,String colorText) {
        super(COMPOUND_SETTINGS);
        this.color = color;
        this.text = colorText;
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public String getTranslatableTextKey() {
        return COLOR_TEXT_PREFIX + text;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("color.project_industrial.firework").formatted(Formatting.GRAY));
        tooltip.add(new TranslatableText(this.getTranslatableTextKey()).formatted(Formatting.ITALIC,Formatting.AQUA));
    }
}