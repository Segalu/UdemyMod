package me.segalu.udemymod.datagen.client.lang

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.BlockInit
import me.segalu.udemymod.init.ItemInit
import net.minecraft.data.DataGenerator
import net.minecraftforge.common.data.LanguageProvider

class ModEnUsProvider(gen: DataGenerator) : LanguageProvider(gen, UdemyMod.ID, "en_us") {

    override fun addTranslations() {
        //Items
        add(ItemInit.COBALT_INGOT, "Cobalt Ingot")
        add(ItemInit.COBALT_NUGGET, "Cobalt Nugget")
        add(ItemInit.RAW_COBALT, "Raw Cobalt")

        //Blocks
        add(BlockInit.COBALT_BLOCK, "Cobalt Block")
        add(BlockInit.COBALT_ORE, "Cobalt Ore")
        add(BlockInit.RAW_COBALT_BLOCK, "Raw Cobalt Block")
        add(BlockInit.DEEPSLATE_COBALT_ORE, "Deeepslate Cobalt Ore")
    }
}