package me.segalu.udemymod.item

import me.segalu.udemymod.init.ModArmorMaterials
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.AirItem
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class ModArmorItem(
    pMaterial: ArmorMaterial, pSlot: EquipmentSlot,
    pProperties: Properties
) : ArmorItem(pMaterial, pSlot, pProperties) {

    override fun onArmorTick(stack: ItemStack?, level: Level?, player: Player?) {
        val armor = player!!.inventory.armor.toList().map { it.item }
        if (armor.all { it !is AirItem } &&
            armor.map { it as ArmorItem }.all { it.material == ModArmorMaterials.COBALT } &&
            !player.hasEffect(MobEffects.GLOWING))
            player.addEffect(MobEffectInstance(MobEffects.LUCK, 200))
    }


}