package net.nejiwwkr.project_industrial.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

public class HandInEnchantment extends Enchantment {
    public HandInEnchantment() {
        super(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity livingEntity && !(livingEntity instanceof VillagerEntity)) {
            if (!livingEntity.getMainHandStack().isEmpty()) {
                if (livingEntity.getMainHandStack().getItem().isDamageable()) {

                    var stack = livingEntity.getMainHandStack();
                    stack.getOrCreateNbt().putInt("Damage", (int) (stack.getMaxDamage() * (Math.random() / 2 + 0.4)));
                    livingEntity.dropStack(stack);
                }else{
                    livingEntity.dropStack(livingEntity.getMainHandStack());
                }
                livingEntity.setStackInHand(Hand.MAIN_HAND,ItemStack.EMPTY);
            }
        }
    }
}
