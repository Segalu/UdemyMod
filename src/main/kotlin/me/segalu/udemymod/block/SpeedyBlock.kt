package me.segalu.udemymod.block

import net.minecraft.core.BlockPos
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.IntegerProperty

class SpeedyBlock(properties: Properties): Block(properties) {
    override fun stepOn(pLevel: Level, pPos: BlockPos, pState: BlockState, pEntity: Entity) {
        if (!pLevel.isClientSide && pEntity is LivingEntity) {
            val entity: LivingEntity = pEntity
            entity.addEffect(MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1))
        }

        super.stepOn(pLevel, pPos, pState, pEntity)
    }
}