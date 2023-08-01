package net.nejiwwkr.project_industrial.item

import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.particle.ParticleTypes
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World


class MagicStickItem(settings: Settings?) : Item(settings) {
    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        var handPos: Vec3d? = user?.pos?.add(0.0, user.getEyeHeight(user.pose).toDouble(), 0.0) // 获取玩家的眼睛位置

        handPos = handPos!!.add(user?.rotationVector?.multiply(1.5)) // 根据玩家的朝向确定手的位置


        world?.addParticle(ParticleTypes.FLAME,handPos.x, handPos.y, handPos.z, 0.0, 0.0, 0.0)
        return super.use(world, user, hand)
    }
}