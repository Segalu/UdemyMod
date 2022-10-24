package me.segalu.udemymod.world.feature

import net.minecraft.world.level.levelgen.placement.*

object ModOrePlacement {
    private fun orePlacement(placementModifier: PlacementModifier, placementModifier2: PlacementModifier): List<PlacementModifier> {
        return listOf(placementModifier, InSquarePlacement.spread(), placementModifier2, BiomeFilter.biome())
    }

    fun commonOrePlacement(pCount: Int, pHeightRange: PlacementModifier): List<PlacementModifier> {
        return orePlacement(CountPlacement.of(pCount), pHeightRange)
    }

    fun rareOrePlacement(pChance: Int, pHeightRange: PlacementModifier): List<PlacementModifier> {
        return orePlacement(RarityFilter.onAverageOnceEvery(pChance), pHeightRange)
    }
}