package net.nejiwwkr.project_industrial.crafting;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import net.nejiwwkr.project_industrial.item.alloy.AlloyIngotItem;
import net.nejiwwkr.project_industrial.util.NbtTagUtil;
import org.jetbrains.annotations.Contract;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Objects;

import static net.minecraft.item.Items.AIR;
import static net.minecraft.item.Items.IRON_INGOT;
import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.*;

/**
 * 包含所有的合成配方及其实例<br>
 * 这里通过<i>物品</i>记录配方,但是对于AlloyItem来说,配方通过<i>MetalType</i>记录<br>
 * <p>因为一个MetalType可以对应多个物品，配方的物品是<strong>多样的</strong>，但属性只与MetalType有关</p>
 * @see net.nejiwwkr.project_industrial.crafting.alloy.MetalType
 * @see net.nejiwwkr.project_industrial.crafting.alloy.MetalType#getTypeByLiteral(String)
 * @see net.nejiwwkr.project_industrial.crafting.alloy.MetalType#getTypeByItem(Item)
 * @see AlloyIngotItem
 * @since 0.1a
 */
public class AlloyRecipe {
    public static final AlloyRecipe SustainableSteel = new AlloyRecipe(IRON_INGOT, STAINLESS_STEEL_INGOT, 200, MOLYBDENUM_NUGGET, NICKEL_NUGGET, CHROME_NUGGET, MANGANESE_NUGGET);


    private final Item mainIngredient;
    private final ItemStack resultItem;
    private final Item[] sideIngredients;
    private final int smeltingTime;
    @Contract(pure = true)
    AlloyRecipe(Item mainIngredient, ItemStack resultItem, int smeltingTime, Item... sideIngredients){
        this.mainIngredient = mainIngredient;
        this.resultItem = resultItem;
        this.sideIngredients = sideIngredients;
        this.smeltingTime = smeltingTime;
    }

    @Contract(pure = true)
    AlloyRecipe(Item mainIngredient, Item resultItem, int smeltingTime, Item... sideIngredients){
        this.mainIngredient = mainIngredient;
        this.resultItem = resultItem.getDefaultStack();
        this.sideIngredients = sideIngredients;
        this.smeltingTime = smeltingTime;
    }

    @SuppressWarnings("FieldMayBeFinal")
    private static ArrayList<AlloyRecipe> recipes = new ArrayList<>();
    static {
        recipes.add(SustainableSteel);
    }

    @Contract("null, _ -> false; _, null -> false")
    public static boolean matches(Item mainIngredient, Item... sideIngredients) {
        boolean res = false;
        for (AlloyRecipe a : recipes) {
            if (Objects.equals(a.mainIngredient, mainIngredient) && NbtTagUtil.compareArrays(a.sideIngredients, sideIngredients)) {
                res = true;
                break;
            }
        }
        return res;
    }

    public static Pair<ItemStack,Integer> matchesWhich(Item mainIngredient, Item... sideIngredients) {
        var res = new Pair<>(AIR.getDefaultStack(),(int) Short.MAX_VALUE);
        for (AlloyRecipe a : recipes) {
            if (Objects.equals(a.mainIngredient, mainIngredient) && NbtTagUtil.compareArrays(a.sideIngredients, sideIngredients)) {
                res.setLeft(a.resultItem);
                res.setRight(a.smeltingTime);
            }
        }
        return res;
    }

    @SuppressWarnings("unused")
    @Contract(pure = true)
    public static void addRecipe (@Nonnull AlloyRecipe a) {
        recipes.add(Objects.requireNonNull(a));
    }
}