package me.segalu.udemymod.datagen.client.lang

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.ModEnchantments
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
        add(ItemInit.BAR_BRAWL_RECORD.descriptionId + ".desc", "Bryan Tech - Bar Brawl (CC0)")
        add("tooltip.udemymod.cobalt_blaster", "My Custom BlockBench Block")
        add("udemymod.advancements.root.title", "Cobalt Ingot")
        add("udemymod.advancements.root.description", "The Power lies in the Cobalt!")
        add("udemymod.advancements.dowsing_rod.title", "Dowsing Rod")
        add("udemymod.advancements.dowsing_rod.description", "If it doesn't work, the Rod doesn't like you!")


        //Items
        add(ItemInit.COBALT_INGOT, "Cobalt Ingot")
        add(ItemInit.COBALT_NUGGET, "Cobalt Nugget")
        add(ItemInit.RAW_COBALT, "Raw Cobalt")
        add(ItemInit.DOWSING_ROD, "Dowsing Rod")
        add(ItemInit.COAL_SLIVER, "Coal Sliver")
        add(ItemInit.TURNIP, "Turnip")
        add(ItemInit.COBALT_SWORD, "Cobalt, Sword")
        add(ItemInit.COBALT_PICKAXE, "Cobalt Pickaxe")
        add(ItemInit.COBALT_AXE, "Cobalt Axe")
        add(ItemInit.COBALT_HOE, "Cobalt Hoe")
        add(ItemInit.COBALT_SHOVEL, "Cobalt Shovel")
        add(ItemInit.COBALT_PAXEL, "Cobalt Paxel")
        add(ItemInit.COBALT_HELMET, "Cobalt Helmet")
        add(ItemInit.COBALT_CHESTPLATE, "Cobalt Chestplate")
        add(ItemInit.COBALT_LEGGINGS, "Cobalt Leggings")
        add(ItemInit.COBALT_BOOTS, "Cobalt Boots")
        add(ItemInit.COBALT_HORSE_ARMOR, "Cobalt Horse Armor")
        add(ItemInit.DATA_TABLET, "Data Tablet")
        add(ItemInit.TURNIP_SEEDS, "Turnip Seeds")
        add(ItemInit.BAR_BRAWL_RECORD, "Bar Brawl Music Disc")
        add(ItemInit.COBALT_STAFF, "Cobalt Staff")
        add(ItemInit.COBALT_BOW, "Cobalt Bow")
        add(ItemInit.HONEY_BUCKET, "Honey Bucket")

        //Blocks
        add(BlockInit.COBALT_BLOCK.get(), "Cobalt Block")
        add(BlockInit.COBALT_ORE.get(), "Cobalt Ore")
        add(BlockInit.RAW_COBALT_BLOCK.get(), "Raw Cobalt Block")
        add(BlockInit.DEEPSLATE_COBALT_ORE.get(), "Deeepslate Cobalt Ore")
        add(BlockInit.SPEEDY_BLOCK.get(), "Speedy Block")
        add(BlockInit.COBALT_STAIRS.get(), "Cobalt Stairs")
        add(BlockInit.COBALT_SLAB.get(), "Cobalt Slab")
        add(BlockInit.COBALT_BUTTON.get(), "Cobalt Button")
        add(BlockInit.COBALT_PRESSURE_PLATE.get(), "Cobalt Pressure Plate")
        add(BlockInit.COBALT_FENCE.get(), "Cobalt Fence")
        add(BlockInit.COBALT_FENCE_GATE.get(), "Cobalt Fence Gate")
        add(BlockInit.COBALT_WALL.get(), "Cobalt Wall")
        add(BlockInit.CHERRY_BLOSSOM_DOOR.get(), "Cherry Blossom Door")
        add(BlockInit.CHERRY_BLOSSOM_TRAPDOOR.get(), "Cherry Blossom Trapdoor")
        add(BlockInit.COBALT_LAMP.get(), "Cobalt Lamp")
        add(BlockInit.IMPOSTOR_BLOCK.get(), "Impostor Block")
        add(BlockInit.TURNIP_CROP.get(), "Turnip Crop")
        add(BlockInit.PINK_ROSE.get(), "Pink Rose")
        add(BlockInit.POTTED_PINK_ROSE.get(), "Potted Pink Rose")
        add(BlockInit.COBALT_BLASTER.get(), "Cobalt Blaster")
        add(BlockInit.CHERRY_BLOSSOM_LOG.get(), "Cherry Blossom Log")
        add(BlockInit.CHERRY_BLOSSOM_WOOD.get(), "Cherry Blossom Wood")
        add(BlockInit.STRIPPED_CHERRY_BLOSSOM_LOG.get(), "Stripped Cherry Blossom Log")
        add(BlockInit.STRIPPED_CHERRY_BLOSSOM_WOOD.get(), "Stripped Cherry Blossom Wood")
        add(BlockInit.CHERRY_BLOSSOM_PLANKS.get(), "Cherry Blossom Planks")

        //Enchantments
        add(ModEnchantments.LIGHTNING_STRIKER, "Lightning Striker")
    }
}