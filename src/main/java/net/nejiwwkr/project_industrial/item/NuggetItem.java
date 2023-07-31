package net.nejiwwkr.project_industrial.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.nejiwwkr.project_industrial.crafting.alloy.MetalType;
import net.nejiwwkr.project_industrial.item.abstract_mod_item.ProjectIndustrialInstructedFlatItem;

public class NuggetItem extends ProjectIndustrialInstructedFlatItem {
    private final MetalType type;
    public NuggetItem(MetalType type) {
        super(new FabricItemSettings());
        this.type = type;
    }

    public MetalType getType() {
        return type;
    }
}
