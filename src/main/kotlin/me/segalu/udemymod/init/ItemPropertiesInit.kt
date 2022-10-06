package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.renderer.item.ItemProperties
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.BowItem
import net.minecraft.world.item.ItemStack

object ItemPropertiesInit {
    fun addCustomItemProperties() {
        ItemProperties.register(ItemInit.DATA_TABLET, ResourceLocation(UdemyMod.ID, "on")) { pStack, _, _, _ ->
            if (pStack.hasTag()) 1F else 0F
        }
        makeBow(ItemInit.COBALT_BOW)
    }

    private fun makeBow(bow: BowItem) {
        ItemProperties.register(
            bow, ResourceLocation("pull")
        ) { stack: ItemStack, _: ClientLevel?, entity: LivingEntity?, _: Int ->
            if (entity == null) {
                return@register 0.0f
            } else {
                return@register if (entity.useItem != stack) 0.0f else (stack.useDuration - entity.useItemRemainingTicks).toFloat() / 20.0f
            }
        }
        ItemProperties.register(
            bow, ResourceLocation("pulling")
        ) { stack: ItemStack, _: ClientLevel?, entity: LivingEntity?, _: Int -> if (entity != null && entity.isUsingItem && entity.useItem == stack) 1.0f else 0.0f }
    }
}