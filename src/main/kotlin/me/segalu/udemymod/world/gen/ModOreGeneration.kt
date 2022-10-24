package me.segalu.udemymod.world.gen

import me.segalu.udemymod.world.feature.ModPlacedFeatures
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraftforge.common.BiomeDictionary
import net.minecraftforge.event.world.BiomeLoadingEvent

object ModOreGeneration {
    fun generateOres(event: BiomeLoadingEvent) {
        var base = event.generation.getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES)
        base.add(ModPlacedFeatures.COBALT_ORE_PLACED)
    }
}