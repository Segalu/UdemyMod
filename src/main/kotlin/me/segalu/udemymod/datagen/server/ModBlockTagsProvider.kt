package me.segalu.udemymod.datagen.server

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.TagInit
import net.minecraft.data.DataGenerator
import net.minecraft.data.tags.BlockTagsProvider
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraftforge.common.data.ExistingFileHelper

class ModBlockTagsProvider(generator: DataGenerator, helper: ExistingFileHelper?) :
    BlockTagsProvider(generator, UdemyMod.ID, helper) {

    override fun addTags() {
        tag(TagInit.Blocks.DOWSING_ROD_VALUABLES).add(
            Blocks.DIAMOND_ORE,
            Blocks.GOLD_ORE,
            Blocks.DEEPSLATE_DIAMOND_ORE,
            Blocks.DEEPSLATE_GOLD_ORE,
            Blocks.EMERALD_ORE,
            Blocks.DEEPSLATE_EMERALD_ORE
        )
    }

}

class ForgeBlockTagsProvider(generator: DataGenerator, helper: ExistingFileHelper?) :
    BlockTagsProvider(generator, "forge", helper) {

    override fun addTags() {
    }

}