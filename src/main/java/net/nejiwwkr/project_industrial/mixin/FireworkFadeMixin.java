package net.nejiwwkr.project_industrial.mixin;

import com.google.common.collect.Lists;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.FireworkStarFadeRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.world.World;
import net.nejiwwkr.project_industrial.item.ChemicalItemWithColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.ArrayList;

@Mixin(FireworkStarFadeRecipe.class)
public class FireworkFadeMixin {
    private static final Ingredient INPUT_STAR = Ingredient.ofItems(Items.FIREWORK_STAR);

    /**
     * @author Nejiwwkr
     * @reason 允许化合物参与渐变烟火制作
     */
    @Overwrite
    public boolean matches(CraftingInventory craftingInventory, World world) {
        boolean bl = false;
        boolean bl2 = false;
        for (int i = 0; i < craftingInventory.size(); ++i) {
            ItemStack itemStack = craftingInventory.getStack(i);
            if (itemStack.isEmpty()) continue;
            if (itemStack.getItem() instanceof DyeItem || itemStack.getItem() instanceof ChemicalItemWithColor) {
                bl = true;
                continue;
            }
            if (INPUT_STAR.test(itemStack)) {
                if (bl2) {
                    return false;
                }
                bl2 = true;
                continue;
            }
            return false;
        }
        return bl2 && bl;
    }

    /**
     * @author Nejiwwkr
     * @reason 允许化合物参与渐变烟火制作
     */
    @Overwrite
    public ItemStack craft(CraftingInventory craftingInventory) {
        ArrayList<Integer> list = Lists.newArrayList();
        ItemStack itemStack = null;
        for (int i = 0; i < craftingInventory.size(); ++i) {
            ItemStack itemStack2 = craftingInventory.getStack(i);
            Item item = itemStack2.getItem();
            if (item instanceof DyeItem) {
                list.add(((DyeItem)item).getColor().getFireworkColor());
                continue;
            }
            if (item instanceof ChemicalItemWithColor) {
                list.add(((ChemicalItemWithColor) item).getColor());
            }
            if (!INPUT_STAR.test(itemStack2)) continue;
            itemStack = itemStack2.copy();
            itemStack.setCount(1);
        }
        if (itemStack == null || list.isEmpty()) {
            return ItemStack.EMPTY;
        }
        itemStack.getOrCreateSubNbt("Explosion").putIntArray("FadeColors", list);
        return itemStack;
    }
}
