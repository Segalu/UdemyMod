package me.segalu.udemymod.datagen.server

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.BlockInit
import me.segalu.udemymod.init.ItemInit
import me.segalu.udemymod.init.TagInit
import net.minecraft.data.DataGenerator
import net.minecraft.data.tags.BlockTagsProvider
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraft.tags.Tag.TagEntry
import net.minecraftforge.common.data.ExistingFileHelper

class ModItemTagsProvider(
    generator: DataGenerator,
    blocks: BlockTagsProvider,
    helper: ExistingFileHelper?
) : ItemTagsProvider(generator, blocks, UdemyMod.ID, helper) {

    override fun addTags() {
        tag(TagInit.Items.CHERRY_BLOSSOM_LOGS).add(
            BlockInit.CHERRY_BLOSSOM_LOG.get().asItem(),
            BlockInit.CHERRY_BLOSSOM_WOOD.get().asItem(),
            BlockInit.STRIPPED_CHERRY_BLOSSOM_LOG.get().asItem(),
            BlockInit.STRIPPED_CHERRY_BLOSSOM_WOOD.get().asItem()
        )
    }
}

class ForgeItemTagsProvider(
    generator: DataGenerator,
    blocks: BlockTagsProvider,
    helper: ExistingFileHelper?
) : ItemTagsProvider(generator, blocks, "forge", helper) {

    override fun addTags() {
        tag(TagInit.Items.COBALT_INGOTS).add(ItemInit.COBALT_INGOT)
        tag(TagInit.Items.COBALT_NUGGETS).add(ItemInit.COBALT_NUGGET)
    }
}

class MinecraftItemTagsProvider(
    generator: DataGenerator,
    blocks: BlockTagsProvider,
    helper: ExistingFileHelper?
) : ItemTagsProvider(generator, blocks, "minecraft", helper) {

    override fun addTags() {
    }
}