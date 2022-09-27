package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.UdemyMod.UDEMYMOD_TAB
import me.segalu.udemymod.item.*
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.AxeItem
import net.minecraft.world.item.HoeItem
import net.minecraft.world.item.HorseArmorItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemNameBlockItem
import net.minecraft.world.item.PickaxeItem
import net.minecraft.world.item.RecordItem
import net.minecraft.world.item.ShovelItem
import net.minecraft.world.item.SwordItem
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object ItemInit {
    val ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, UdemyMod.ID)

    val COBALT_INGOT by ITEMS.registerObject("cobalt_ingot") {
        Item(Item.Properties().tab(UDEMYMOD_TAB))
    }

    val COBALT_NUGGET by ITEMS.registerObject("cobalt_nugget") {
        Item(Item.Properties().tab(UDEMYMOD_TAB))
    }

    val RAW_COBALT by ITEMS.registerObject("raw_cobalt") {
        Item(Item.Properties().tab(UDEMYMOD_TAB))
    }

    val DOWSING_ROD by ITEMS.registerObject("dowsing_rod") {
        DowsingRodItem(Item.Properties().tab(UDEMYMOD_TAB).durability(16))
    }

    val COAL_SLIVER by ITEMS.registerObject("coal_sliver") {
        CoalSliver(Item.Properties().tab(UDEMYMOD_TAB))
    }

    val TURNIP by ITEMS.registerObject("turnip") {
        Item(Item.Properties().tab(UDEMYMOD_TAB).food(ModFoods.TURNIP))
    }

    val COBALT_SWORD by ITEMS.registerObject("cobalt_sword") {
        LevitationSwordItem(TierInit.COBALT, 2, 3f, Item.Properties().tab(UDEMYMOD_TAB))
    }

    val COBALT_PICKAXE by ITEMS.registerObject("cobalt_pickaxe") {
        PickaxeItem(TierInit.COBALT, 2, 3f, Item.Properties().tab(UDEMYMOD_TAB))
    }

    val COBALT_AXE by ITEMS.registerObject("cobalt_axe") {
        AxeItem(TierInit.COBALT, 4F, -1f, Item.Properties().tab(UDEMYMOD_TAB))
    }

    val COBALT_HOE by ITEMS.registerObject("cobalt_hoe") {
        HoeItem(TierInit.COBALT, 2, 3f, Item.Properties().tab(UDEMYMOD_TAB))
    }

    val COBALT_SHOVEL by ITEMS.registerObject("cobalt_shovel") {
        ShovelItem(TierInit.COBALT, 0f, 0f, Item.Properties().tab(UDEMYMOD_TAB))
    }

    val COBALT_PAXEL by ITEMS.registerObject("cobalt_paxel") {
        PaxelItem(TierInit.COBALT, 0f, 0f, Item.Properties().tab(UDEMYMOD_TAB))
    }

    val COBALT_HELMET by ITEMS.registerObject("cobalt_helmet") {
        ModArmorItem(ModArmorMaterials.COBALT, EquipmentSlot.HEAD, Item.Properties().tab(UDEMYMOD_TAB))
    }

    val COBALT_CHESTPLATE by ITEMS.registerObject("cobalt_chestplate") {
        ArmorItem(ModArmorMaterials.COBALT, EquipmentSlot.CHEST, Item.Properties().tab(UDEMYMOD_TAB))
    }

    val COBALT_LEGGINGS by ITEMS.registerObject("cobalt_leggings") {
        ArmorItem(ModArmorMaterials.COBALT, EquipmentSlot.LEGS, Item.Properties().tab(UDEMYMOD_TAB))
    }

    val COBALT_BOOTS by ITEMS.registerObject("cobalt_boots") {
        ArmorItem(ModArmorMaterials.COBALT, EquipmentSlot.FEET, Item.Properties().tab(UDEMYMOD_TAB))
    }

    val COBALT_HORSE_ARMOR by ITEMS.registerObject("cobalt_horse_armor") {
        HorseArmorItem(9, "cobalt", Item.Properties().tab(UDEMYMOD_TAB))
    }

    val DATA_TABLET by ITEMS.registerObject("data_tablet") {
        DataTabletItem(Item.Properties().stacksTo(1).tab(UDEMYMOD_TAB))
    }

    val TURNIP_SEEDS by ITEMS.registerObject("turnip_seeds") {
        ItemNameBlockItem(BlockInit.TURNIP_CROP.get(), Item.Properties().tab(UDEMYMOD_TAB))
    }

    val BAR_BRAWL_RECORD by ITEMS.registerObject("bar_brawl_music_disc") {
        RecordItem(4, SoundInit.BAR_BRAWL, Item.Properties().tab(UDEMYMOD_TAB))
    }
}