package me.segalu.udemymod.datagen.server.loot

import me.segalu.udemymod.init.BlockInit
import me.segalu.udemymod.init.ItemInit
import net.minecraft.advancements.critereon.StatePropertiesPredicate
import net.minecraft.data.loot.BlockLoot
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition

class ModBlockLootTables : BlockLoot() {

    override fun addTables() {
        dropSelf(BlockInit.COBALT_BLOCK.get())
        dropSelf(BlockInit.RAW_COBALT_BLOCK.get())
        add(BlockInit.COBALT_ORE.get()) { createOreDrop(it, ItemInit.RAW_COBALT) }
        add(BlockInit.DEEPSLATE_COBALT_ORE.get()) { createOreDrop(it, ItemInit.RAW_COBALT) }
        dropSelf(BlockInit.COBALT_WALL.get())
        dropSelf(BlockInit.COBALT_FENCE.get())
        dropSelf(BlockInit.COBALT_FENCE_GATE.get())
        dropSelf(BlockInit.COBALT_PRESSURE_PLATE.get())
        dropSelf(BlockInit.COBALT_BUTTON.get())
        dropSelf(BlockInit.COBALT_SLAB.get())
        dropSelf(BlockInit.COBALT_STAIRS.get())
        dropSelf(BlockInit.CHERRY_BLOSSOM_TRAPDOOR.get())
        dropSelf(BlockInit.CHERRY_BLOSSOM_DOOR.get())
        dropSelf(BlockInit.SPEEDY_BLOCK.get())
        dropSelf((BlockInit.COBALT_LAMP.get()))
        add(BlockInit.IMPOSTOR_BLOCK.get()) { noDrop() }
        add(BlockInit.TURNIP_CROP.get()) {
            createCropDrops(
                it, ItemInit.TURNIP, ItemInit.TURNIP_SEEDS,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(
                    BlockInit.TURNIP_CROP.get()
                ).setProperties(
                    StatePropertiesPredicate.Builder.properties()
                        .hasProperty(BlockInit.TURNIP_CROP.get().ageProperty, 3)
                )
            )
        }
        dropSelf(BlockInit.PINK_ROSE.get())
        dropPottedContents(BlockInit.POTTED_PINK_ROSE.get())
        dropSelf(BlockInit.COBALT_BLASTER.get())
        dropSelf(BlockInit.CHERRY_BLOSSOM_LOG.get())
        dropSelf(BlockInit.CHERRY_BLOSSOM_WOOD.get())
        dropSelf(BlockInit.STRIPPED_CHERRY_BLOSSOM_LOG.get())
        dropSelf(BlockInit.STRIPPED_CHERRY_BLOSSOM_WOOD.get())
        dropSelf(BlockInit.CHERRY_BLOSSOM_PLANKS.get())
    }

    override fun getKnownBlocks(): Iterable<Block> {
        return BlockInit.BLOCKS.entries.map { it.get() }
    }
}