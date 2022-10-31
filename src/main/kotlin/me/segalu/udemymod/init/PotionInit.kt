package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.item.alchemy.Potion
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object PotionInit {
    val POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, UdemyMod.ID)

    val FREEZE_POTION by POTIONS.registerObject("freeze_potion") {
        Potion(MobEffectInstance(EffectInit.FREEZE, 200, 0))
    }
    
}