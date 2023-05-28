package net.nejiwwkr.project_industrial.entity.damage;

import net.minecraft.entity.damage.DamageSource;

public class ChemicalDamage extends DamageSource {
    public ChemicalDamage() {
        super("chemical");
    }

    public static final DamageSource INSTANCE = new ChemicalDamage().setBypassesArmor();
}
