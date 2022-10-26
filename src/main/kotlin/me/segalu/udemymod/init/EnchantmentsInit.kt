package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.enchantment.LightningStrikerEnchantment
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object EnchantmentsInit {
    val ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, UdemyMod.ID)

    val LIGHTNING_STRIKER by ENCHANTMENTS.registerObject("lightning_striker") {
        LightningStrikerEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentCategory.WEAPON, EquipmentSlot.MAINHAND)
    }
}