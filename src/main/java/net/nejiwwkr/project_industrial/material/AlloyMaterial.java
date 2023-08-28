package net.nejiwwkr.project_industrial.material;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.nejiwwkr.project_industrial.crafting.alloy.MetalType;
import net.nejiwwkr.project_industrial.util.interfaces.AbstractAlloy;

import static net.nejiwwkr.project_industrial.util.interfaces.AbstractAlloy.getStiffness;

public class AlloyMaterial implements ToolMaterial, AbstractAlloy {
    private final MetalType mainIngredient;
    private final MetalType[] sideIngredients;
    private final double stiffness;

    public AlloyMaterial(MetalType mainIngredient, MetalType[] sideIngredients) {
        this.mainIngredient = mainIngredient;
        this.sideIngredients = sideIngredients;
        stiffness = getStiffness(mainIngredient,sideIngredients);
    }

    @Override
    public int getDurability() {
        return (int) (750 + stiffness * 50);
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return (float) (6f + (stiffness / 4.0f));
    }

    @Override
    public float getAttackDamage() {
        return (float) (5f + (stiffness / 2.0f));
    }

    @Override
    public int getMiningLevel() {
        return 4;
    }

    @Override
    public int getEnchantability() {
        return (int) (15 - (stiffness / 2));
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    public MetalType getMainIngredient() {
        return mainIngredient;
    }

    public MetalType[] getSideIngredients() {
        return sideIngredients;
    }
}
