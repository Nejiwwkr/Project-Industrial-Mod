package net.nejiwwkr.project_industrial.crafting;

import net.minecraft.item.Item;
import net.minecraft.util.Pair;
import net.nejiwwkr.project_industrial.util.NbtTagUtil;

import java.util.ArrayList;
import java.util.Objects;

import static net.minecraft.item.Items.AIR;
import static net.minecraft.item.Items.IRON_INGOT;
import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.*;

public class AlloyRecipe {
    public static final AlloyRecipe SustainableSteel = new AlloyRecipe(IRON_INGOT, STAINLESS_STEEL_INGOT, 200, MOLYBDENUM_NUGGET, NICKEL_NUGGET, CHROME_NUGGET, MANGANESE_NUGGET);


    private final Item mainIngredient;
    private final Item resultItem;
    private final Item[] sideIngredients;
    private final int smeltingTime;
    AlloyRecipe(Item mainIngredient, Item resultItem, int smeltingTime, Item... sideIngredients){
        this.mainIngredient = mainIngredient;
        this.resultItem = resultItem;
        this.sideIngredients = sideIngredients;
        this.smeltingTime = smeltingTime;
    }

    @SuppressWarnings("FieldMayBeFinal")
    private static ArrayList<AlloyRecipe> recipes = new ArrayList<>();
    static {
        recipes.add(SustainableSteel);
    }

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

    public static Pair<Item,Integer> matchesWhich(Item mainIngredient, Item... sideIngredients) {
        var res = new Pair<>(AIR,(int) Short.MAX_VALUE);
        for (AlloyRecipe a : recipes) {
            if (Objects.equals(a.mainIngredient, mainIngredient) && NbtTagUtil.compareArrays(a.sideIngredients, sideIngredients)) {
                res.setLeft(a.resultItem);
                res.setRight(a.smeltingTime);
            }
        }
        return res;
    }

    @SuppressWarnings("unused")
    public static void addRecipe (AlloyRecipe a) {
        recipes.add(a);
    }
}