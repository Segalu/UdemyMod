package me.segalu.udemymod.datagen.server

import me.segalu.udemymod.datagen.BaseLootTableProvider
import me.segalu.udemymod.init.BlockInit
import me.segalu.udemymod.init.ItemInit
import net.minecraft.data.DataGenerator
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

class ModLootTableProvider(generator: DataGenerator) : BaseLootTableProvider(generator) {

    override fun addTables() {
        dropSelf(BlockInit.COBALT_BLOCK.get())
        dropSelf(BlockInit.RAW_COBALT_BLOCK.get())
        add(BlockInit.COBALT_ORE.get(), createSimpleTable(BlockInit.COBALT_ORE.get().registryName?.path, ItemInit.RAW_COBALT))
        add(BlockInit.DEEPSLATE_COBALT_ORE.get(), createSimpleTable(BlockInit.DEEPSLATE_COBALT_ORE.get().registryName?.path, ItemInit.RAW_COBALT))
    }

    private fun silkTouch(block: Block, silk: Item, min: Float, max: Float) {
        add(block, createSilkTouchTable(block.registryName?.path, block, silk, min, max))
    }
    private fun dropSelf(block: Block) {
        add(block, createSimpleTable(block.registryName?.path, block))
    }

}