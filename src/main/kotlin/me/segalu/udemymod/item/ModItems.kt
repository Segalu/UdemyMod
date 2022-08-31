package me.segalu.udemymod.item

import me.segalu.udemymod.UdemyMod
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.Item
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ModItems {
    val REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, UdemyMod.ID)

    val COBALT_INGOT by REGISTRY.registerObject("cobalt_ingot") {
        Item(Item.Properties().tab(CreativeModeTab.TAB_MISC))
    }

    val COBALT_NUGGET by REGISTRY.registerObject("cobalt_nugget") {
        Item(Item.Properties().tab(CreativeModeTab.TAB_MISC))
    }

}