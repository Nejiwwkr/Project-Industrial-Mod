package net.nejiwwkr.project_industrial.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.nejiwwkr.project_industrial.crafting.alloy.MetalType;

public class NuggetItem extends Item {
    private final MetalType type;
    public NuggetItem(MetalType type) {
        super(new FabricItemSettings());
        this.type = type;
    }

    public MetalType getType() {
        return type;
    }
}
