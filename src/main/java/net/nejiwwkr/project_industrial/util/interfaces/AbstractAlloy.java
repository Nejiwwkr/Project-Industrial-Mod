package net.nejiwwkr.project_industrial.util.interfaces;

import net.nejiwwkr.project_industrial.crafting.alloy.MetalType;

import java.util.Arrays;

public interface AbstractAlloy {
    static double getStiffness(MetalType mainIngredient, MetalType... sideIngredients) {
        return mainIngredient.getStiffness() * 0.8 + Arrays.stream(sideIngredients).map(MetalType::getStiffness).reduce(0.0,Double::sum) * 0.3 / sideIngredients.length + 1;
    }

    MetalType getMainIngredient();
    MetalType[] getSideIngredients();
}
