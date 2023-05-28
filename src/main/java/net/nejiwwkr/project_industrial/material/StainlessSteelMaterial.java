package net.nejiwwkr.project_industrial.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.nejiwwkr.project_industrial.item.StainlessSteelItem;

public class StainlessSteelMaterial implements ToolMaterial {
    private final int stiffness;
    private final StainlessSteelItem materialIngot;

    @Override
    public int getDurability() {
        return 750 + stiffness * 50;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 6f + (stiffness / 4.0f);
    }

    @Override
    public float getAttackDamage() {
        return 5f + (stiffness / 2.0f);
    }

    @Override
    public int getMiningLevel() {
        return 4;
    }

    @Override
    public int getEnchantability() {
        return 15 - (stiffness / 2);
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(materialIngot);
    }

    public StainlessSteelMaterial(int stiffness, StainlessSteelItem materialIngot) {
        this.stiffness = stiffness;
        this.materialIngot = materialIngot;
    }
}
