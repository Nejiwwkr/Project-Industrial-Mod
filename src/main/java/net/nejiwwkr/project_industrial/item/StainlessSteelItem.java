package net.nejiwwkr.project_industrial.item;

import net.nejiwwkr.project_industrial.crafting.alloy.MetalType;

public class StainlessSteelItem extends AlloyItem {
    private MetalType firstIngredient;
    private MetalType secondIngredient;
    private MetalType thirdIngredient;
    private MetalType forthIngredient;


    public StainlessSteelItem(Settings settings) {
        super(settings);
    }
}
