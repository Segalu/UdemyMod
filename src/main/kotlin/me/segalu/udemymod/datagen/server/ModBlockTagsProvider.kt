package me.segalu.udemymod.datagen.server

import me.segalu.udemymod.UdemyMod
import net.minecraft.data.DataGenerator
import net.minecraft.data.tags.BlockTagsProvider
import net.minecraftforge.common.data.ExistingFileHelper

class ModBlockTagsProvider(generator: DataGenerator, helper: ExistingFileHelper?) :
    BlockTagsProvider(generator, UdemyMod.ID, helper) {

    override fun addTags() {
    }

}