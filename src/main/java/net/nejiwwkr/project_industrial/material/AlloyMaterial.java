package net.nejiwwkr.project_industrial.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class AlloyMaterial<A extends ToolMaterial, B extends ToolMaterial, C extends ToolMaterial, D extends ToolMaterial> implements ToolMaterial{
    @Override
    public int getDurability() {
        return 0;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0;
    }

    @Override
    public float getAttackDamage() {
        return 0;
    }

    @Override
    public int getMiningLevel() {
        return 0;
    }

    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
