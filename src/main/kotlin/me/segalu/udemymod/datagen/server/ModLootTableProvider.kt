package me.segalu.udemymod.datagen.server

import me.segalu.udemymod.datagen.BaseLootTableProvider
import net.minecraft.data.DataGenerator
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Block

class ModLootTableProvider(generator: DataGenerator) : BaseLootTableProvider(generator) {

    override fun addTables() {
    }

    private fun silkTouch(block: Block, silk: Item, min: Float, max: Float) {
        add(block, createSilkTouchTable(block.registryName!!.path, block, silk, min, max))
    }
    private fun dropSelf(block: Block) {
        add(block, createSimpleTable(block.registryName!!.path, block))
    }

}