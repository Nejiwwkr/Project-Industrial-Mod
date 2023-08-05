package net.nejiwwkr.project_industrial.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nejiwwkr.project_industrial.util.C;

public class CalciumOxideItem extends ChemicalItemWithColor {
    public CalciumOxideItem() {
        super(C.color.Ca,"calcium_orange");
    }
}

class CalciumOxideItemEntity extends ItemEntity {
    public CalciumOxideItemEntity(EntityType<? extends ItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public CalciumOxideItemEntity(World world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, stack);
    }

    public CalciumOxideItemEntity(World world, double x, double y, double z, ItemStack stack, double velocityX, double velocityY, double velocityZ) {
        super(world, x, y, z, stack, velocityX, velocityY, velocityZ);
    }
}