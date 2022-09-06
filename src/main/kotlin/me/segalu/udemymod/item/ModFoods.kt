package me.segalu.udemymod.item

import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.food.FoodProperties

object ModFoods {
    val TURNIP = FoodProperties.Builder().nutrition(2).saturationMod(0.2f)
        .effect({ MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, )}, 0.1F).build()
}