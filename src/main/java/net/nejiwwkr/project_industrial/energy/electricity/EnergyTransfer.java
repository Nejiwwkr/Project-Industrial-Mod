package net.nejiwwkr.project_industrial.energy.electricity;

import net.minecraft.util.math.Direction;
import net.nejiwwkr.project_industrial.energy.util.Transferable;
import net.nejiwwkr.project_industrial.energy.util.Unit;

/**
 * 代表能量的转移，包含能量从一个设备或储能单元传输到另一个设备或储能单元的过程，
 * 有能量的传输速率等属性，可以控制能量在方块间的传输。
 */
public class EnergyTransfer implements Transferable<Energy> {
    public static final int MAX_TRANSFER = 1000;

    public static int transferEnergy(EnergySender sender, EnergyReceiver receiver, Direction direction, int maxTransfer) {
        Energy energy = sender.getEnergy(direction);
        if (energy == null || !receiver.canReceiveEnergy(direction)) {
            return 0;
        }

        int transferredEnergy = (int) Math.min(Math.min(energy.getEnergy(), maxTransfer), MAX_TRANSFER);
        Energy transferEnergy = new Energy(transferredEnergy, energy.getUnit());

        if (transferredEnergy > 0) {
            sender.extractEnergy(direction, transferEnergy);
            receiver.receiveEnergy(direction, transferEnergy);
        }

        return transferredEnergy;
    }

    @Override
    public void transfer(Unit unit) {

    }

    @Override
    public void transfer(Unit unit, Energy something) {

    }
}
