package me.segalu.udemymod.world.gen

import me.segalu.udemymod.world.feature.ModPlacedFeatures
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraftforge.common.BiomeDictionary
import net.minecraftforge.event.world.BiomeLoadingEvent

object ModFlowerGeneration {

    fun generateFlowers(event: BiomeLoadingEvent) {
        val key = ResourceKey.create(Registry.BIOME_REGISTRY, event.name)
        val types = BiomeDictionary.getTypes(key)

        if (types.contains(BiomeDictionary.Type.PLAINS)) {
            var base = event.generation.getFeatures(GenerationStep.Decoration.VEGETAL_DECORATION)
            base.add(ModPlacedFeatures.PINK_ROSE_PLACED)
        }
    }
}