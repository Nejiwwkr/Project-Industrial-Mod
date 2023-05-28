package net.nejiwwkr.project_industrial.item.chemicals.components;

import net.minecraft.block.Material;

import javax.annotation.Nullable;

public record Element(String name, @Nullable Material material) {
}
