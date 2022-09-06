package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.UdemyMod.UDEMYMOD_TAB
import me.segalu.udemymod.item.CoalSliver
import me.segalu.udemymod.item.DowsingRodItem
import me.segalu.udemymod.item.ModFoods
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

    val DOWSING_ROD by ITEMS.registerObject("dowsing_rod") {
        DowsingRodItem(Item.Properties().tab(UDEMYMOD_TAB).durability(16))
    }

    val COAL_SLIVER by ITEMS.registerObject("coal_sliver") {
        CoalSliver(Item.Properties().tab(UDEMYMOD_TAB))
    }

    val TURNIP by ITEMS.registerObject("turnip") {
        Item(Item.Properties().tab(UDEMYMOD_TAB).food(ModFoods.TURNIP))
    }
}