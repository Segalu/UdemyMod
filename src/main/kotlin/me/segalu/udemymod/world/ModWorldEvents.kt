package me.segalu.udemymod.world

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.world.gen.ModFlowerGeneration
import me.segalu.udemymod.world.gen.ModTreeGeneration
import net.minecraftforge.event.world.BiomeLoadingEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber

@EventBusSubscriber(modid = UdemyMod.ID)
object ModWorldEvents {
    @SubscribeEvent
    fun biomeLoadingEvent(event: BiomeLoadingEvent) {
        ModTreeGeneration.generateTrees(event)
        ModFlowerGeneration.generateFlowers(event)
    }
}