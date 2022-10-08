package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import net.minecraft.world.entity.decoration.Motive
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object PaintingInit {
    val PAINTING_MOTIVES = DeferredRegister.create(ForgeRegistries.PAINTING_TYPES, UdemyMod.ID)

    val PLANT = PAINTING_MOTIVES.registerObject("plant") {
        Motive(16, 16)
    }
    val WANDERER = PAINTING_MOTIVES.registerObject("wanderer") {
        Motive(16, 32)
    }
    val SUNSET = PAINTING_MOTIVES.registerObject("sunset") {
        Motive(32, 16)
    }
}