package net.nejiwwkr.project_industrial.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.nejiwwkr.project_industrial.item.chemicals.Chemical;
import net.nejiwwkr.project_industrial.item.chemicals.instances.Anions;
import net.nejiwwkr.project_industrial.item.chemicals.instances.Cations;
import java.util.Arrays;

@Deprecated
public class IonicChemicalItem extends Item implements Chemical {
    Anions anion;
    Cations[] cation = new Cations[5];

    public IonicChemicalItem(Anions anion,Cations cation) {
        super(new FabricItemSettings().maxCount(16));

        this.cation[0] = cation;
        this.anion = anion;
    }

    public IonicChemicalItem(Anions anion,Cations... cations) {
        super(new FabricItemSettings().maxCount(16));

        this.cation = Arrays.copyOf(cations,5);
        this.anion = anion;
    }

    @Override
    public Text getText() {
        return null;
    }

    @Override
    public int getColor() {
        return 0;
    }
}
