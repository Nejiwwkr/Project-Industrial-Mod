package net.nejiwwkr.project_industrial.item.chemicals.components;

import net.nejiwwkr.project_industrial.item.chemicals.instances.Elements;

public class Anion {
    Electron e;
    Elements elements;

    public Anion (int valenceState,Elements elements){
        e = new Electron(Sign.Negative,valenceState);
        this.elements = elements;
    }
}
