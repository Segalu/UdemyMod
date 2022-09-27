package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvent
import net.minecraftforge.common.util.ForgeSoundType
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object SoundInit {
    val SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, UdemyMod.ID)

    fun registerSound(name: String) = SOUND_EVENTS.register(name) { SoundEvent(ResourceLocation(UdemyMod.ID, name)) }

    val DOWSING_ROD_FOUND_ORE = registerSound("dowsing_rod_found_ore")
    val COBALT_LAMP_BREAK = registerSound("cobalt_lamp_break")
    val COBALT_LAMP_STEP = registerSound("cobalt_lamp_step")
    val COBALT_LAMP_PLACE = registerSound("cobalt_lamp_place")
    val COBALT_LAMP_HIT = registerSound("cobalt_lamp_hit")
    val COBALT_LAMP_FALL = registerSound("cobalt_lamp_fall")

    val COBALT_LAMP_SOUNDS = ForgeSoundType(
        1F,
        1F,
        COBALT_LAMP_BREAK,
        COBALT_LAMP_STEP,
        COBALT_LAMP_PLACE,
        COBALT_LAMP_HIT,
        COBALT_LAMP_FALL
    )
}