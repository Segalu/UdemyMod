package me.segalu.udemymod.effect

import net.minecraft.world.effect.MobEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraft.world.entity.LivingEntity

class FreezeEffect(effect: MobEffectCategory, int: Int) : MobEffect(effect, int) {
    override fun applyEffectTick(pLivingEntity: LivingEntity, pAmplifier: Int) {
        if (!pLivingEntity.level.isClientSide) {
            val x = pLivingEntity.x
            val y = pLivingEntity.y
            val z = pLivingEntity.z

            pLivingEntity.teleportTo(x, y, z)
            pLivingEntity.setDeltaMovement(0.0, 0.0, 0.0)
        }
        super.applyEffectTick(pLivingEntity, pAmplifier)
    }

    override fun isDurationEffectTick(pDuration: Int, pAmplifier: Int) = true

}