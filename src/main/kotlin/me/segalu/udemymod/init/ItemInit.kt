package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.UdemyMod.UDEMYMOD_TAB
import net.minecraft.world.item.Item
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ItemInit {
    val ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UdemyMod.ID)

    val COBALT_INGOT by ITEMS.registerObject("cobalt_ingot") {
        Item(Item.Properties().tab(UDEMYMOD_TAB))
    }

    val COBALT_NUGGET by ITEMS.registerObject("cobalt_nugget") {
        Item(Item.Properties().tab(UDEMYMOD_TAB))
    }

    val RAW_COBALT by ITEMS.registerObject("raw_cobalt") {
        Item(Item.Properties().tab(UDEMYMOD_TAB))
    }
}