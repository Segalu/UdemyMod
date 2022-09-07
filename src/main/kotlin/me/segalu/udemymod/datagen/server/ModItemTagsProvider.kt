package me.segalu.udemymod.datagen.server

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.ItemInit
import me.segalu.udemymod.init.TagInit
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