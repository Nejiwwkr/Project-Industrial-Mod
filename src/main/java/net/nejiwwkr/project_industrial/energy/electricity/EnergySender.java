package net.nejiwwkr.project_industrial.energy.electricity;

import net.minecraft.util.math.Direction;

/**
 * 代表能量发送方
 */
public interface EnergySender {
    boolean canExtractEnergy(Direction direction);

    Energy extractEnergy(Direction direction, Energy energy);

    Energy getEnergy(Direction direction);
}
