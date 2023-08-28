package net.nejiwwkr.project_industrial.crafting.alloy

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

fun interface UsageFunc {
    fun onUse(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack?>?

    companion object {
        @JvmField
        val USELESS = UsageFunc { _: World?, user: PlayerEntity?, hand: Hand? -> TypedActionResult.success(user?.getStackInHand(hand)) }
    }
}

