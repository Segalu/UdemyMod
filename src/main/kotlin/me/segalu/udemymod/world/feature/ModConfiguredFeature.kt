package me.segalu.udemymod.world.feature

import me.segalu.udemymod.config.UdemyModCommonConfigs
import me.segalu.udemymod.init.BlockInit
import net.minecraft.data.worldgen.features.FeatureUtils
import net.minecraft.data.worldgen.features.OreFeatures
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer

object ModConfiguredFeature {

    val CHERRY_BLOSSOM_TREE = FeatureUtils.register(
        "cherry_blossom", Feature.TREE,
        TreeConfiguration.TreeConfigurationBuilder(
            BlockStateProvider.simple(BlockInit.CHERRY_BLOSSOM_LOG.get()),
            StraightTrunkPlacer(5, 6, 3),
            BlockStateProvider.simple(BlockInit.CHERRY_BLOSSOM_LEAVES.get()),
            BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 4),
            TwoLayersFeatureSize(1, 0, 2)
        ).build()
    )

    val PINK_ROSE = FeatureUtils.register(
        "flower_pink_rose",
        Feature.FLOWER,
        RandomPatchConfiguration(
            32, 6, 2, PlacementUtils.onlyWhenEmpty(
                Feature.SIMPLE_BLOCK, SimpleBlockConfiguration(
                    BlockStateProvider.simple(BlockInit.PINK_ROSE.get())
                )
            )
        )

    )

    val OVERWORLD_COBALT_ORES = listOf(
        OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.COBALT_ORE.get().defaultBlockState()),
        OreConfiguration.target(
            OreFeatures.DEEPSLATE_ORE_REPLACEABLES,
            BlockInit.DEEPSLATE_COBALT_ORE.get().defaultBlockState()
        )
    )

    val COBALT_ORE = FeatureUtils.register(
        "cobalt_ore",
        Feature.ORE,
        OreConfiguration(OVERWORLD_COBALT_ORES, UdemyModCommonConfigs.COBALT_ORE_VEIN_SIZE.get())
    )

}