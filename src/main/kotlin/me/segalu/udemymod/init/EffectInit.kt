package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.effect.FreezeEffect
import net.minecraft.world.effect.MobEffectCategory
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object EffectInit {
    val EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, UdemyMod.ID)

    val FREEZE by EFFECTS.registerObject("freeze") {
        FreezeEffect(MobEffectCategory.HARMFUL, 3124687)
    }
}