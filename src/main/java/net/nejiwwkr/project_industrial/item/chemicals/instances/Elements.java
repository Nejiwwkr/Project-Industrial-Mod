package net.nejiwwkr.project_industrial.item.chemicals.instances;

import net.nejiwwkr.project_industrial.item.chemicals.components.Element;

public enum Elements {
    Calcium("ca"),
    Oxygen("o");

    public final Element element;
    Elements (String name){
        this.element = new Element(name,null);
    }
}
