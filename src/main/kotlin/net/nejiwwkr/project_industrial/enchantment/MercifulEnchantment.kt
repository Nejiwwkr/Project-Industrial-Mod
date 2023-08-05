package net.nejiwwkr.project_industrial.enchantment

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolItem

class MercifulEnchantment : Enchantment(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, Array(1){EquipmentSlot.MAINHAND}) {
    override fun getMinPower(level : Int) : Int {
        return 0
    }

    override fun getMaxPower(level : Int) : Int {
        return 0
    }

    override fun getMaxLevel() : Int {
        return 1
    }

    override fun onTargetDamaged(user : LivingEntity, target : Entity,level : Int) {
        val item = user.mainHandStack.item
        if (item is ToolItem && target is LivingEntity) {
            val attackDamage = (item as SwordItem).attackDamage
            target.heal(attackDamage)
        }
    }
}


