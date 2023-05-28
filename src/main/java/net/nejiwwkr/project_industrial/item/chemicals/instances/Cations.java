package net.nejiwwkr.project_industrial.item.chemicals.instances;

import net.nejiwwkr.project_industrial.item.chemicals.components.Cation;

public enum Cations {
    Calcium(new Cation(2,Elements.Calcium));



    public final Cation cation;
    Cations (Cation cation){
        this.cation = cation;
    }
}
