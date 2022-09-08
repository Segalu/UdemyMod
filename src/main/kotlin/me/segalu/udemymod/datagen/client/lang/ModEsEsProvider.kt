package me.segalu.udemymod.datagen.client.lang

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.BlockInit
import me.segalu.udemymod.init.ItemInit
import net.minecraft.data.DataGenerator
import net.minecraft.world.item.Item
import net.minecraftforge.common.data.LanguageProvider

class ModEsEsProvider(gen: DataGenerator) : LanguageProvider(gen, UdemyMod.ID, "es_es") {

    override fun addTranslations() {
        //UI
        add("itemGroup.udemymod", "UdemyMod Tab")
        add("item.udemymod.dowsing_rod.no_valuables", "No se encontro bloque valioso")
        add("tooltip.udemymod.dowsing_rod.shift", "Click derecho para encontrar bloques valiosos")
        add("tooltip.udemymod.dowsing_rod", "Presiona §eSHIFT§r para mas info")
        add("tooltip.udemymod.speedy_block.shift", "se pone REALMENTE rapido!")
        add("tooltip.udemymod.speedy_block", "Presiona §eSHIFT§r para mas info")

        //Items
        add(ItemInit.COBALT_INGOT, "Lingote de Cobalto")
        add(ItemInit.COBALT_NUGGET, "Pepita de Cobalto")
        add(ItemInit.RAW_COBALT, "Cobalto Crudo")
        add(ItemInit.DOWSING_ROD, "Vara Cantramposo")
        add(ItemInit.COAL_SLIVER, "Carbon de Bobos")
        add(ItemInit.TURNIP, "Turnip")

        //Blocks
        add(BlockInit.COBALT_BLOCK.get(), "Bloque de Cobalto")
        add(BlockInit.COBALT_ORE.get(), "Mena de Cobalto")
        add(BlockInit.RAW_COBALT_BLOCK.get(), "Bloque de Cobalto Crudo")
        add(BlockInit.DEEPSLATE_COBALT_ORE.get(), "Mineral Profundo de Cobalto")
        add(BlockInit.SPEEDY_BLOCK.get(), "Block Rapido")
        add(BlockInit.COBALT_STAIRS.get(), "Escalera de Cobalto")
        add(BlockInit.COBALT_SLAB.get(), "Escalon de Cobalto")
        add(BlockInit.COBALT_BUTTON.get(), "Boton de Cobalto")
        add(BlockInit.COBALT_PRESSURE_PLATE.get(), "Placa de Presion de Cobalto")
        add(BlockInit.COBALT_FENCE.get(), "Valla de Cobalto")
        add(BlockInit.COBALT_FENCE_GATE.get(), "Puerta de Valla de Cobalto")
        add(BlockInit.COBALT_WALL.get(), "Muro de Cobalto")
        add(BlockInit.CHERRY_BLOSSOM_DOOR.get(), "Puerta de Cerezo")
        add(BlockInit.CHERRY_BLOSSOM_TRAPDOOR.get(), "Trampilla de Cerezo")
    }
}