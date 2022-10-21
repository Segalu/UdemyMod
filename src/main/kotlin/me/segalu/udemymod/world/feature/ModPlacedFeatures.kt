package me.segalu.udemymod.world.feature

import me.segalu.udemymod.init.BlockInit
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement


object ModPlacedFeatures {

    val CHERRY_BLOSSOM_PLACED = PlacementUtils.register(
        "cherry_blossom_placed",
        ModConfiguredFeature.CHERRY_BLOSSOM_TREE,
        treePlacement(PlacementUtils.countExtra(1, 0.1F, 2), BlockInit.CHERRY_BLOSSOM_SAPLING.get())
    )

}