package net.nejiwwkr.project_industrial.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.MOLYBDENUM_INGOT;

public class MolybdenumMaterial implements ToolMaterial {

    public static final MolybdenumMaterial INSTANCE = new MolybdenumMaterial();

    @Override
    public int getDurability() {
        return 2560;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 10F;
    }

    @Override
    public float getAttackDamage() {
        return 9F;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 8;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(MOLYBDENUM_INGOT);
    }
}
