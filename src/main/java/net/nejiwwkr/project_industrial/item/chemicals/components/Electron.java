package net.nejiwwkr.project_industrial.item.chemicals.components;

public class Electron {
    final Sign property;
    final int valenceState;

    public Electron(Sign property, int valenceState) {
        this.property = property;
        this.valenceState = valenceState;
    }
}

enum Sign {
    Negative,Positive;
}
