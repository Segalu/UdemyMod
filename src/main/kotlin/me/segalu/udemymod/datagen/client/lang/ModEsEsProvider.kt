package me.segalu.udemymod.datagen.client.lang

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.ModEnchantments
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
        add(ItemInit.BAR_BRAWL_RECORD.descriptionId + ".desc", "Bryan Tech - Pelea de Bar (CC0)")
        add("tooltip.udemymod.cobalt_blaster", "Mi Bloque Block Bench Personalizado")
        add("udemymod.advancements.root.title", "Lingote de Cobalto")
        add("udemymod.advancements.root.description", "El poder yace en el cobalto!")
        add("udemymod.advancements.dowsing_rod.title", "Vara Adivinadora")
        add("udemymod.advancements.dowsing_rod.description", "Si no funciona, no le agradas a la vara")

        //Items
        add(ItemInit.COBALT_INGOT, "Lingote de Cobalto")
        add(ItemInit.COBALT_NUGGET, "Pepita de Cobalto")
        add(ItemInit.RAW_COBALT, "Cobalto Crudo")
        add(ItemInit.DOWSING_ROD, "Vara Cantramposo")
        add(ItemInit.COAL_SLIVER, "Carbon de Bobos")
        add(ItemInit.TURNIP, "Nabo")
        add(ItemInit.COBALT_SWORD, "Espada de Cobalto")
        add(ItemInit.COBALT_PICKAXE, "Pico de CObalto")
        add(ItemInit.COBALT_AXE, "Hacha de Cobalto")
        add(ItemInit.COBALT_HOE, "Azada de Cobalto")
        add(ItemInit.COBALT_SHOVEL, "Pala de Cobalto")
        add(ItemInit.COBALT_PAXEL, "Pachala de Cobalto")
        add(ItemInit.COBALT_HELMET, "Casco de Cobalto")
        add(ItemInit.COBALT_CHESTPLATE, "Pechera de Cobalto")
        add(ItemInit.COBALT_LEGGINGS, "Pierneras de Cobalto")
        add(ItemInit.COBALT_BOOTS, "Botas de Cobalto")
        add(ItemInit.COBALT_HORSE_ARMOR, "Armadura de Caballo de Cobalto")
        add(ItemInit.DATA_TABLET, "Tableta de Datos")
        add(ItemInit.TURNIP_SEEDS, "Semillas de Nabo")
        add(ItemInit.BAR_BRAWL_RECORD, "Disco Pelea de Bar")
        add(ItemInit.COBALT_STAFF, "Bastón de Cobalto")
        add(ItemInit.COBALT_BOW, "Arco de Cobalto")
        add(ItemInit.HONEY_BUCKET, "Cubeta de Miel")


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
        add(BlockInit.COBALT_LAMP.get(), "Lámpara de Cobalto")
        add(BlockInit.IMPOSTOR_BLOCK.get(), "Bloque Impostor")
        add(BlockInit.TURNIP_CROP.get(), "Cosecha de Nabo")
        add(BlockInit.PINK_ROSE.get(), "Rosa Rosa")
        add(BlockInit.POTTED_PINK_ROSE.get(), "Maceta Rosa Rosa")
        add(BlockInit.COBALT_BLASTER.get(), "Altohorno de Cobalto")
        add(BlockInit.CHERRY_BLOSSOM_LOG.get(), "Tronco de Cerezo")
        add(BlockInit.CHERRY_BLOSSOM_WOOD.get(), "Leño de Cerezo")
        add(BlockInit.STRIPPED_CHERRY_BLOSSOM_LOG.get(), "Tronco Sin Corteza de Cerezo")
        add(BlockInit.STRIPPED_CHERRY_BLOSSOM_WOOD.get(), "Leño Sin Corteza de Cerezo")
        add(BlockInit.CHERRY_BLOSSOM_PLANKS.get(), "Madera de Cerezo")

        //Enchantments
        add(ModEnchantments.LIGHTNING_STRIKER, "Golpea Truenos")
    }
}