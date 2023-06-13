package net.nejiwwkr.project_industrial.energy.electricity;

import net.minecraft.util.math.Direction;

/**
 * 代表能量的提供方，具有提供能量的能力，可以将储存的能量输出到其他设备或储能单元。
 */
public interface EnergyProvider {
    boolean canProvideEnergy(Direction direction);

    Energy getEnergy(Direction direction);

    void provideEnergy(Direction direction, Energy energy);
}
