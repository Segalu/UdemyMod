package me.segalu.udemymod.datagen.client.lang

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.BlockInit
import me.segalu.udemymod.init.ItemInit
import net.minecraft.data.DataGenerator
import net.minecraftforge.common.data.LanguageProvider

class ModEsEsProvider(gen: DataGenerator) : LanguageProvider(gen, UdemyMod.ID, "es_es") {

    override fun addTranslations() {
        //Items
        add(ItemInit.COBALT_INGOT, "Lingote de Cobalto")
        add(ItemInit.COBALT_NUGGET, "Pepita de Cobalto")
        add(ItemInit.RAW_COBALT, "Cobalto Crudo")

        //Blocks
        add(BlockInit.COBALT_BLOCK, "Bloque de Cobalto")
        add(BlockInit.COBALT_ORE, "Mena de Cobalto")
        add(BlockInit.RAW_COBALT_BLOCK, "Bloque de Cobalto Crudo")
        add(BlockInit.DEEPSLATE_COBALT_ORE, "Deeepslate Cobalt Ore")
    }
}