package net.nejiwwkr.project_industrial.item.tools;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.SwordItem;
import net.nejiwwkr.project_industrial.material.AlloyMaterial;

public class AlloySword extends SwordItem {
    public AlloySword(AlloyMaterial toolMaterial) {
        super(toolMaterial, -1, -2.4f,new FabricItemSettings());
    }
}
