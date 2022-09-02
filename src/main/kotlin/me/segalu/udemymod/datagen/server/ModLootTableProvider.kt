package me.segalu.udemymod.datagen.server

import me.segalu.udemymod.datagen.BaseLootTableProvider
import me.segalu.udemymod.init.BlockInit
import net.minecraft.data.DataGenerator
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

class ModLootTableProvider(generator: DataGenerator) : BaseLootTableProvider(generator) {

    override fun addTables() {
        dropSelf(BlockInit.COBALT_BLOCK)
        dropSelf(BlockInit.RAW_COBALT_BLOCK)
        dropSelf(BlockInit.DEEPSLATE_COBALT_ORE)
        dropSelf(BlockInit.COBALT_ORE)
    }

    private fun silkTouch(block: Block, silk: Item, min: Float, max: Float) {
        add(block, createSilkTouchTable(block.registryName!!.path, block, silk, min, max))
    }
    private fun dropSelf(block: Block) {
        add(block, createSimpleTable(block.registryName!!.path, block))
    }

}