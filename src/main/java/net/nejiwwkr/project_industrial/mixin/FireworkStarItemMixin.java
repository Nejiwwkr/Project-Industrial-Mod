package net.nejiwwkr.project_industrial.mixin;

import net.minecraft.item.FireworkStarItem;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.nejiwwkr.project_industrial.util.interfaces.ContainsTranslatableText.COLOR_TEXT_PREFIX;

@Mixin(FireworkStarItem.class)
public class FireworkStarItemMixin {
    /**
     * @author Nejiwwkr
     * @reason Show compound color in text
     */
    @Overwrite
    private static Text getColorText(int color) {
        DyeColor dyeColor = DyeColor.byFireworkColor(color);
        if (dyeColor == null) {
            if (color == 0xff989ccd) return new TranslatableText(COLOR_TEXT_PREFIX + "lead_light_blue.star");
            if (color == 0xffec6941) return new TranslatableText(COLOR_TEXT_PREFIX + "calcium_orange.star");
            if (color == 0xffa781df) return new TranslatableText(COLOR_TEXT_PREFIX + "potassium_purple.star");
            if (color == 0xffabcd98) return new TranslatableText(COLOR_TEXT_PREFIX + "molybdenum_olivine.star");
            return new TranslatableText("item.minecraft.firework_star.custom_color");
        }
        return new TranslatableText("item.minecraft.firework_star." + dyeColor.getName());
    }
}
