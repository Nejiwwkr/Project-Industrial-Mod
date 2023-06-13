package net.nejiwwkr.project_industrial.energy.electricity;

import net.minecraft.util.math.Direction;

/**
 * 代表能量的接收方，具有接收能量的能力，可以接收从其他设备或储能单元输出的能量。
 */
public interface EnergyReceiver {
    boolean canReceiveEnergy(Direction direction);

    void receiveEnergy(Direction direction, Energy energy);
}
