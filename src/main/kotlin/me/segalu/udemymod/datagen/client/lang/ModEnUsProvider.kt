package me.segalu.udemymod.datagen.client.lang

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.BlockInit
import me.segalu.udemymod.init.ItemInit
import net.minecraft.data.DataGenerator
import net.minecraftforge.common.data.LanguageProvider

class ModEnUsProvider(gen: DataGenerator) : LanguageProvider(gen, UdemyMod.ID, "en_us") {

    override fun addTranslations() {
        //UI
        add("itemGroup.udemymod", "UdemyMod Tab")
        add("item.udemymod.dowsing_rod.no_valuables", "No valuable block found")
        add("tooltip.udemymod.dowsing_rod.shift", "Right click to find valuables")
        add("tooltip.udemymod.dowsing_rod", "Press §eSHIFT§r for info")
        add("tooltip.udemymod.speedy_block.shift", "it gets REALLY speedy!")
        add("tooltip.udemymod.speedy_block", "Press §eSHIFT§r for info")

        //Items
        add(ItemInit.COBALT_INGOT, "Cobalt Ingot")
        add(ItemInit.COBALT_NUGGET, "Cobalt Nugget")
        add(ItemInit.RAW_COBALT, "Raw Cobalt")
        add(ItemInit.DOWSING_ROD, "Dowsing Rod")
        add(ItemInit.COAL_SLIVER, "Coal Sliver")
        add(ItemInit.TURNIP, "Turnip")

        //Blocks
        add(BlockInit.COBALT_BLOCK.get(), "Cobalt Block")
        add(BlockInit.COBALT_ORE.get(), "Cobalt Ore")
        add(BlockInit.RAW_COBALT_BLOCK.get(), "Raw Cobalt Block")
        add(BlockInit.DEEPSLATE_COBALT_ORE.get(), "Deeepslate Cobalt Ore")
        add(BlockInit.SPEEDY_BLOCK.get(), "Speedy Block")
    }
}