package net.nejiwwkr.project_industrial.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.util.math.Vec3d;
import net.nejiwwkr.project_industrial.entity.damage.ChemicalDamage;

public class PlasmaEnchantment extends Enchantment {
    public PlasmaEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity livingEntity) {
            LightningEntity lightningEntity = EntityType.LIGHTNING_BOLT.create(livingEntity.getWorld());
            assert lightningEntity != null;
            lightningEntity.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(livingEntity.getBlockPos().up()));

            user.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE,60));
            livingEntity.getWorld().spawnEntity(lightningEntity);
            user.extinguish();
        }
        if (target instanceof WitchEntity) {
            target.damage(ChemicalDamage.INSTANCE,10f);
        }
        if (target instanceof EnderDragonEntity) {
            target.damage(ChemicalDamage.INSTANCE,30f);
        }
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        super.onUserDamaged(user, attacker, level);
    }
}
