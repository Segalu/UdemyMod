package me.segalu.udemymod.world.feature

import me.segalu.udemymod.init.BlockInit
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement
import net.minecraft.world.level.levelgen.placement.BiomeFilter
import net.minecraft.world.level.levelgen.placement.InSquarePlacement
import net.minecraft.world.level.levelgen.placement.RarityFilter


object ModPlacedFeatures {

    val CHERRY_BLOSSOM_PLACED = PlacementUtils.register(
        "cherry_blossom_placed",
        ModConfiguredFeature.CHERRY_BLOSSOM_TREE,
        treePlacement(PlacementUtils.countExtra(1, 0.1F, 2), BlockInit.CHERRY_BLOSSOM_SAPLING.get())
    )

    val PINK_ROSE_PLACED = PlacementUtils.register(
        "pink_rose_placed",
        ModConfiguredFeature.PINK_ROSE,
        InSquarePlacement.spread(),
        PlacementUtils.HEIGHTMAP,
        RarityFilter.onAverageOnceEvery(32),
        BiomeFilter.biome()
    )
}