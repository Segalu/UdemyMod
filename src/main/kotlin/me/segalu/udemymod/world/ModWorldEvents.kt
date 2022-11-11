package me.segalu.udemymod.world

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.world.gen.ModEntityGeneration
import me.segalu.udemymod.world.gen.ModFlowerGeneration
import me.segalu.udemymod.world.gen.ModOreGeneration
import me.segalu.udemymod.world.gen.ModTreeGeneration
import net.minecraftforge.event.world.BiomeLoadingEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber

@EventBusSubscriber(modid = UdemyMod.ID)
object ModWorldEvents {
    @SubscribeEvent
    fun biomeLoadingEvent(event: BiomeLoadingEvent) {
        ModOreGeneration.generateOres(event)
        ModTreeGeneration.generateTrees(event)
        ModFlowerGeneration.generateFlowers(event)
        ModEntityGeneration.onEntitySpawn(event)
    }
}