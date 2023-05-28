package net.nejiwwkr.project_industrial.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.nejiwwkr.project_industrial.crafting.alloy.MetalType;

public class IngotItem extends Item {
    private final MetalType type;
    public IngotItem(MetalType type) {
        super(new FabricItemSettings());
        this.type = type;
    }

    public MetalType getType() {
        return type;
    }
}
