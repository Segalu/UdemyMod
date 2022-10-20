package me.segalu.udemymod.world.feature.tree

import me.segalu.udemymod.world.feature.ModConfiguredFeature
import net.minecraft.core.Holder
import net.minecraft.world.level.block.grower.AbstractTreeGrower
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import java.util.*

class CherryBlossomTreeGrower : AbstractTreeGrower() {

    override fun getConfiguredFeature(pRandom: Random, pLargeHive: Boolean): Holder<out ConfiguredFeature<*, *>>? {
        return ModConfiguredFeature.CHERRY_BLOSSOM_TREE
    }


}