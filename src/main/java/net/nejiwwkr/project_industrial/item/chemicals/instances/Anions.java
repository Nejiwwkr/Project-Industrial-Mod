package net.nejiwwkr.project_industrial.item.chemicals.instances;

import net.nejiwwkr.project_industrial.item.chemicals.components.Anion;

public enum Anions {
    Oxygen(new Anion(2,Elements.Oxygen));



    public final Anion anion;
    Anions (Anion anion){
        this.anion = anion;
    }
}
