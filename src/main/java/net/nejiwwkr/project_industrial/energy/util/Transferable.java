package net.nejiwwkr.project_industrial.energy.util;

public interface Transferable<T> {
    void transfer(Unit unit);
    void transfer(Unit unit,T something);
}
