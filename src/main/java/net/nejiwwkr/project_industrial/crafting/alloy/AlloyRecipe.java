package net.nejiwwkr.project_industrial.crafting.alloy;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;

public class AlloyRecipe implements Recipe<Inventory> {
    private final Identifier id;
    private final Ingredient base;
    private final List<Ingredient> additives;
    private final ItemStack output;
    private final int cookTime;

    public AlloyRecipe(Identifier id, Ingredient base, List<Ingredient> additives, ItemStack output, int cookTime) {
        this.id = id;
        this.base = base;
        this.additives = additives;
        this.output = output;
        this.cookTime = cookTime;
    }

    @Override
    public boolean matches(Inventory inv, World world) {
        // 验证配方材料是否匹配. 略
        return false;
    }

    @Override
    public ItemStack craft(Inventory inv) {
        return this.getOutput().copy(); // 返回制作出的物品，注意这里需要复制一份以免影响配方原有信息
    }

    @Override
    public boolean fits(int width, int height) {
        // 略
        return false;
    }

    @Override
    public ItemStack getOutput() {
        return this.output;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeType<?> getType() {
        //TODO return YourModRecipeTypes.ALLOY_TYPE;
        return null;
    }

    public int getCookTime() {
        return this.cookTime;
    }

    public Ingredient getBase() {
        return this.base;
    }

    public List<Ingredient> getAdditives() {
        return this.additives;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        //TODO return YourModRecipeSerializers.ALLOY;
        return null;
    }
}
