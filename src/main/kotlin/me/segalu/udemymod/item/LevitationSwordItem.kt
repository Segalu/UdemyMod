package me.segalu.udemymod.item

import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.SwordItem
import net.minecraft.world.item.Tier

class LevitationSwordItem(
    pTier: Tier,
    pAttackDamageModifier: Int,
    pAttackSpeedModifier: Float,
    pProperties: Properties
) : SwordItem(
    pTier, pAttackDamageModifier,
    pAttackSpeedModifier,
    pProperties
) {
    override fun hurtEnemy(pStack: ItemStack, pTarget: LivingEntity, pAttacker: LivingEntity): Boolean {
        pTarget.addEffect(MobEffectInstance(MobEffects.LEVITATION, 200), pAttacker)
        return super.hurtEnemy(pStack, pTarget, pAttacker)
    }
}