package me.segalu.udemymod.item

import me.segalu.udemymod.entity.ModBoatEntity
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntitySelector
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.phys.HitResult

class ModBoatItem(val type: ModBoatEntity.Type, pProperties: Properties) : Item(pProperties) {
    companion object {
        val ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::canBeCollidedWith)
    }

    override fun use(pLevel: Level, pPlayer: Player, pUsedHand: InteractionHand): InteractionResultHolder<ItemStack> {
        val itemStack = pPlayer.getItemInHand(pUsedHand)
        val rayTraceResult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.ANY)

        if (rayTraceResult.type == HitResult.Type.MISS) return InteractionResultHolder.pass(itemStack)
        else {
            val vec3d = pPlayer.getEyePosition(1.0F)
            val d0 = 5.0
            val list = pLevel.getEntities(
                pPlayer,
                pPlayer.boundingBox.expandTowards(vec3d.scale(d0)).inflate(1.0),
                ENTITY_PREDICATE
            )
            if (list.isNotEmpty()) {
                val vec3d1 = pPlayer.getEyePosition(1.0F)
                for (entity in list) {
                    val axisAlignedBB = entity.boundingBox.inflate(entity.pickRadius.toDouble())
                    if (axisAlignedBB.contains(vec3d1)) {
                        return InteractionResultHolder.pass(itemStack)
                    }
                }
            }

            if (rayTraceResult.type == HitResult.Type.BLOCK) {
                val boatEntity = ModBoatEntity(
                    pLevel,
                    rayTraceResult.location.x,
                    rayTraceResult.location.y,
                    rayTraceResult.location.z
                )
                boatEntity.setBoatType(type)
                boatEntity.yRot = pPlayer.yRot
                return if (!pLevel.noCollision(
                        boatEntity,
                        boatEntity.boundingBox.inflate(-0.1)
                    )
                ) InteractionResultHolder.fail(itemStack)
                else {
                    if (!pLevel.isClientSide) {
                        pLevel.addFreshEntity(boatEntity)
                    }
                    if (!pPlayer.isCreative) {
                        itemStack.shrink(1)
                    }
                    pPlayer.awardStat(Stats.ITEM_USED.get(this))
                    InteractionResultHolder.success(itemStack)
                }
            } else return InteractionResultHolder.pass(itemStack)
        }
    }

}