package me.segalu.udemymod.datagen.server

import me.segalu.udemymod.UdemyMod
import net.minecraft.data.DataGenerator
import net.minecraft.data.tags.BlockTagsProvider
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraftforge.common.data.ExistingFileHelper

class ModItemTagsProvider(
    generator: DataGenerator,
    blocks: BlockTagsProvider,
    helper: ExistingFileHelper?
) : ItemTagsProvider(generator, blocks, UdemyMod.ID, helper) {

    override fun addTags() {
    }
}