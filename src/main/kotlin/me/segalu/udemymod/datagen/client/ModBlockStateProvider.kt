package me.segalu.udemymod.datagen.client

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.BlockInit
import net.minecraft.data.DataGenerator
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.common.data.ExistingFileHelper

class ModBlockStateProvider(generator: DataGenerator?, helper: ExistingFileHelper?) :
    BlockStateProvider(generator, UdemyMod.ID, helper) {
    override fun registerStatesAndModels() {
        simpleBlock(BlockInit.COBALT_BLOCK)
        simpleBlock(BlockInit.COBALT_ORE)
        simpleBlock(BlockInit.RAW_COBALT_BLOCK)
        simpleBlock(BlockInit.DEEPSLATE_COBALT_ORE)
        simpleBlock(BlockInit.SPEEDY_BLOCK)
    }
}