package me.segalu.udemymod.enchantment

import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.*
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory

class LightningStrikerEnchantment(
    pRarity: Rarity,
    pCategory: EnchantmentCategory,
    vararg pApplicableSlots: EquipmentSlot
) : Enchantment(pRarity, pCategory, pApplicableSlots) {
    override fun doPostAttack(pAttacker: LivingEntity, pTarget: Entity, pLevel: Int) {
        if (!pAttacker.level.isClientSide) {
            val world = pAttacker.level as ServerLevel
            val player = pAttacker as ServerPlayer
            val position = pTarget.blockPosition()

            if (pLevel == 1) EntityType.LIGHTNING_BOLT.spawn(world, null, player, position, MobSpawnType.TRIGGERED, true, true)
            if (pLevel == 2) repeat(2) { EntityType.LIGHTNING_BOLT.spawn(world, null, player, position, MobSpawnType.TRIGGERED, true, true) }
        }
    }

    override fun getMaxLevel(): Int {
        return 2
    }
}