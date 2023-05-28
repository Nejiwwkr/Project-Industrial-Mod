package net.nejiwwkr.project_industrial.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;

public class AntidoteStatusEffect extends StatusEffect {
    public AntidoteStatusEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xcad39d);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity instanceof PlayerEntity player){
            player.removeStatusEffect(StatusEffects.POISON);
        }
    }
}
