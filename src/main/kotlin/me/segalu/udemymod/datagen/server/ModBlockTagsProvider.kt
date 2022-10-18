package me.segalu.udemymod.datagen.server

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.BlockInit
import me.segalu.udemymod.init.FluidInit
import me.segalu.udemymod.init.TagInit
import net.minecraft.data.DataGenerator
import net.minecraft.data.tags.BlockTagsProvider
import net.minecraft.data.tags.FluidTagsProvider
import net.minecraft.tags.BlockTags
import net.minecraft.tags.FluidTags
import net.minecraft.tags.Tag
import net.minecraft.tags.Tag.TagEntry
import net.minecraft.world.level.block.Blocks
import net.minecraftforge.common.Tags
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

        tag(TagInit.Blocks.PAXEL_MINEABLE).add(
            TagEntry(BlockTags.MINEABLE_WITH_PICKAXE.location),
        )
        tag(TagInit.Blocks.PAXEL_MINEABLE).add(
            TagEntry(BlockTags.MINEABLE_WITH_AXE.location),
        )
        tag(TagInit.Blocks.PAXEL_MINEABLE).add(
            TagEntry(BlockTags.MINEABLE_WITH_SHOVEL.location),
        )
        tag(TagInit.Blocks.CHERRY_BLOSSOM_LOGS).add(
            BlockInit.CHERRY_BLOSSOM_LOG.get(),
            BlockInit.CHERRY_BLOSSOM_WOOD.get(),
            BlockInit.STRIPPED_CHERRY_BLOSSOM_LOG.get(),
            BlockInit.STRIPPED_CHERRY_BLOSSOM_WOOD.get()
        )
    }
}

class ForgeBlockTagsProvider(generator: DataGenerator, helper: ExistingFileHelper?) :
    BlockTagsProvider(generator, "forge", helper) {

    override fun addTags() {
    }
}

class MinecraftBlockTagsProvider(generator: DataGenerator, helper: ExistingFileHelper?) :
    BlockTagsProvider(generator, "minecraft", helper) {

    override fun addTags() {
        tag(BlockTags.FENCES).add(
            BlockInit.COBALT_FENCE.get()
        )

        tag(BlockTags.NEEDS_IRON_TOOL).add(
            BlockInit.COBALT_BLOCK.get(),
            BlockInit.COBALT_ORE.get(),
            BlockInit.RAW_COBALT_BLOCK.get(),
            BlockInit.DEEPSLATE_COBALT_ORE.get(),
            BlockInit.SPEEDY_BLOCK.get(),
            BlockInit.COBALT_BUTTON.get(),
            BlockInit.COBALT_FENCE.get(),
            BlockInit.COBALT_FENCE_GATE.get(),
            BlockInit.COBALT_WALL.get(),
            BlockInit.COBALT_PRESSURE_PLATE.get(),
            BlockInit.COBALT_SLAB.get(),
            BlockInit.COBALT_STAIRS.get()
        )

        tag(BlockTags.WALLS).add(
            BlockInit.COBALT_WALL.get()
        )

        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
            BlockInit.COBALT_BLOCK.get(),
            BlockInit.COBALT_ORE.get(),
            BlockInit.RAW_COBALT_BLOCK.get(),
            BlockInit.DEEPSLATE_COBALT_ORE.get(),
            BlockInit.SPEEDY_BLOCK.get(),
            BlockInit.COBALT_BUTTON.get(),
            BlockInit.COBALT_FENCE.get(),
            BlockInit.COBALT_FENCE_GATE.get(),
            BlockInit.COBALT_WALL.get(),
            BlockInit.COBALT_PRESSURE_PLATE.get(),
            BlockInit.COBALT_SLAB.get(),
            BlockInit.COBALT_STAIRS.get()
        )

        tag(BlockTags.LOGS).add(
            TagEntry(TagInit.Blocks.CHERRY_BLOSSOM_LOGS.location)
        )

        tag(BlockTags.LOGS_THAT_BURN).add(
            TagEntry(TagInit.Blocks.CHERRY_BLOSSOM_LOGS.location)
        )
    }
}

class MinecraftFluidTagsProvider(generator: DataGenerator, helper: ExistingFileHelper?) :
    FluidTagsProvider(generator, "minecraft", helper) {

    override fun addTags() {
        tag(FluidTags.WATER).add(
            FluidInit.HONEY_FLOWING,
            FluidInit.HONEY_FLUID
        )
    }
}
