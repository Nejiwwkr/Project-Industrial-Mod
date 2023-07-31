package net.nejiwwkr.project_industrial.energy.electricity;

import net.nejiwwkr.project_industrial.energy.util.Unit;

/**
 * 代表能量的基本单位，包含能量值、单位等属性，可以被储存和传输。
 */
public class Energy {
    private double energy;
    private final Unit unit;

    public Energy(double energy, Unit unit) {
        this.energy = energy;
        this.unit = unit;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public Unit getUnit() {
        return unit;
    }
}
