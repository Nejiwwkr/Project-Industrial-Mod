package net.nejiwwkr.project_industrial.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.MessageType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.nejiwwkr.project_industrial.entity.damage.ChemicalDamage;

public class LeadStatusEffect extends StatusEffect {
    public LeadStatusEffect() {
        super(StatusEffectCategory.HARMFUL, 0x5b4976);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 25 >> amplifier;
        if (i > 0) {
            return duration % i == 0;
        } else {
            return true;
        }
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.getHealth() >= 10) {
            entity.damage(ChemicalDamage.INSTANCE, 1.0F * (amplifier + 1));
        }else {
            entity.damage(ChemicalDamage.INSTANCE, 1.0F * (amplifier + 2));
        }

        if (entity instanceof PlayerEntity) ((PlayerEntity)entity).addExhaustion(0.005F * (float)(amplifier + 1));
    }
}
