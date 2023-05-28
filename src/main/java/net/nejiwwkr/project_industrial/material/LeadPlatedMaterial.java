package net.nejiwwkr.project_industrial.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import static net.nejiwwkr.project_industrial.ProjectIndustrialMod.LEAD_INGOT;

public class LeadPlatedMaterial implements ToolMaterial {

    public static final LeadPlatedMaterial INSTANCE = new LeadPlatedMaterial();

    @Override
    public int getDurability() {
        return 500;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 6F;
    }

    @Override
    public float getAttackDamage() {
        return 5.5F;
    }

    @Override
    public int getMiningLevel() {
        return 3;
    }

    @Override
    public int getEnchantability() {
        return 18;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(LEAD_INGOT);
    }
}
