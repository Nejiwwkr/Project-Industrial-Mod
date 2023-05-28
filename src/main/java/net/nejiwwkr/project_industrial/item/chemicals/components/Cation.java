package net.nejiwwkr.project_industrial.item.chemicals.components;

import net.nejiwwkr.project_industrial.item.chemicals.instances.Elements;

public class Cation {
    Electron e;
    Elements elements;

    public Cation (int valenceState,Elements elements){
        e = new Electron(Sign.Positive,valenceState);
        this.elements = elements;
    }
}
