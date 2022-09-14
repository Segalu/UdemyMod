package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import net.minecraft.client.renderer.item.ItemProperties
import net.minecraft.resources.ResourceLocation

object ItemPropertiesInit {
    fun addCustomItemProperties() {
        ItemProperties.register(ItemInit.DATA_TABLET, ResourceLocation(UdemyMod.ID, "on")) { pStack, _, _, _ ->
            if (pStack.hasTag()) 1F else 0F
        }
    }
}