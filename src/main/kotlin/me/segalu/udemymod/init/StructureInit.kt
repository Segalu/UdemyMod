package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.world.structure.SkyStructures
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object StructureInit {
    val STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, UdemyMod.ID)

    val SKY_STRUCTURES by STRUCTURES.registerObject("sky_structures") {
        SkyStructures()
    }

}