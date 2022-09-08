package me.segalu.udemymod.datagen.client

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.BlockInit
import net.minecraft.data.DataGenerator
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.common.data.ExistingFileHelper

class ModBlockStateProvider(generator: DataGenerator?, helper: ExistingFileHelper?) :
    BlockStateProvider(generator, UdemyMod.ID, helper) {
    private val cobaltResource = blockTexture(BlockInit.COBALT_BLOCK.get())
    override fun registerStatesAndModels() {
        simpleBlock(BlockInit.COBALT_BLOCK.get())
        simpleBlock(BlockInit.COBALT_ORE.get())
        simpleBlock(BlockInit.RAW_COBALT_BLOCK.get())
        simpleBlock(BlockInit.DEEPSLATE_COBALT_ORE.get())
        simpleBlock(BlockInit.SPEEDY_BLOCK.get())
        stairsBlock(BlockInit.COBALT_STAIRS.get(), cobaltResource)
        slabBlock(BlockInit.COBALT_SLAB.get(), cobaltResource, cobaltResource)
        buttonBlock(BlockInit.COBALT_BUTTON.get(), cobaltResource)
        pressurePlateBlock(BlockInit.COBALT_PRESSURE_PLATE.get(), cobaltResource)
        fenceBlock(BlockInit.COBALT_FENCE.get(), cobaltResource)
        fenceGateBlock(BlockInit.COBALT_FENCE_GATE.get(), cobaltResource)
        wallBlock(BlockInit.COBALT_WALL.get(), cobaltResource)
        doorBlock(BlockInit.CHERRY_BLOSSOM_DOOR.get(), ResourceLocation(UdemyMod.ID, "block/${BlockInit.CHERRY_BLOSSOM_DOOR.get().registryName!!.path}_bottom"), ResourceLocation(UdemyMod.ID, "block/${BlockInit.CHERRY_BLOSSOM_DOOR.get().registryName!!.path}_top"))
        trapdoorBlock(BlockInit.CHERRY_BLOSSOM_TRAPDOOR.get(), ResourceLocation(UdemyMod.ID, "block/${BlockInit.CHERRY_BLOSSOM_TRAPDOOR.get().asItem().registryName!!.path}"), true)
    }
}