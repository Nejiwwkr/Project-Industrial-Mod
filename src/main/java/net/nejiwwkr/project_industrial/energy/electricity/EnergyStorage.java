package net.nejiwwkr.project_industrial.energy.electricity;

import net.minecraft.nbt.NbtCompound;

/**
 * 代表能量的存储器，具有最大容量和当前存储量等属性，可以存储能量并控制能量的流动。
 */
public class EnergyStorage {
    private final int maxCapacity;
    private int currentCapacity;

    public EnergyStorage(int maxCapacity, int currentCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        if (currentCapacity > maxCapacity) {
            this.currentCapacity = maxCapacity;
        } else if (currentCapacity < 0) {
            this.currentCapacity = 0;
        } else {
            this.currentCapacity = currentCapacity;
        }
    }

    public boolean canReceive(Energy energy) {
        return currentCapacity < maxCapacity;
    }

    public boolean canProvide(Energy energy) {
        return currentCapacity > 0;
    }

    public int receiveEnergy(Energy energy) {
        int energyToReceive = (int) Math.min(energy.getEnergy(), maxCapacity - currentCapacity);
        currentCapacity += energyToReceive;
        return energyToReceive;
    }

    public int extractEnergy(Energy energy) {
        int energyToExtract = (int) Math.min(energy.getEnergy(), currentCapacity);
        currentCapacity -= energyToExtract;
        return energyToExtract;
    }

    /**
     * 序列化存储器的数据
     */
    public NbtCompound toTag(NbtCompound tag) {
        tag.putInt("MaxCapacity", maxCapacity);
        tag.putInt("CurrentCapacity", currentCapacity);
        return tag;
    }

    /**
     * 反序列化存储器的数据
     */
    public static EnergyStorage fromTag(NbtCompound tag) {
        int maxCapacity = tag.getInt("MaxCapacity");
        int currentCapacity = tag.getInt("CurrentCapacity");
        return new EnergyStorage(maxCapacity, currentCapacity);
    }
}

