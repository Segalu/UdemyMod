package me.segalu.udemymod.world.feature

import me.segalu.udemymod.init.BlockInit
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer

object ModConfiguredFeature {
    val CHERRY_BLOSSOM_TREE = FeatureUtils.register("cherry_blossom", Feature.TREE,
        TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(BlockInit.CHERRY_BLOSSOM_LOG.get()),
            StraightTrunkPlacer(5, 6, 3),
            BlockStateProvider.simple(BlockInit.CHERRY_BLOSSOM_LEAVES.get()),
            BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
            TwoLayersFeatureSize(1, 0, 2)).build())
}