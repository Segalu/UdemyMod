package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.crafting.Ingredient
import net.minecraftforge.common.util.Lazy
import java.util.function.Supplier


enum class ModArmorMaterials(
    private val pname: String,
    private val durabilityMultiplier: Int,
    private val slotProtections: IntArray,
    private val enchantmentValue: Int,
    private val sound: SoundEvent,
    private val toughness: Float,
    private val knockbackResistance: Float,
    repairIngredient: Supplier<Ingredient>
) : ArmorMaterial {
    COBALT("cobalt", 13, intArrayOf(2, 3, 5, 3), 21,
        SoundEvents.ARMOR_EQUIP_GOLD, 0.5f, 0.0f, Supplier<Ingredient> { Ingredient.of(ItemInit.COBALT_INGOT) });

    private val repairIngredient: Lazy<Ingredient>

    init {
        this.repairIngredient = Lazy.of(repairIngredient)
    }

    override fun getDurabilityForSlot(pSlot: EquipmentSlot): Int {
        return HEALTH_PER_SLOT[pSlot.index] * durabilityMultiplier
    }

    override fun getDefenseForSlot(pSlot: EquipmentSlot): Int {
        return slotProtections[pSlot.index]
    }

    override fun getEnchantmentValue(): Int {
        return enchantmentValue
    }

    override fun getEquipSound(): SoundEvent {
        return sound
    }

    override fun getRepairIngredient(): Ingredient {
        return repairIngredient.get()
    }

    override fun getName(): String {
        return UdemyMod.ID + ":" + pname
    }

    override fun getToughness(): Float {
        return toughness
    }

    override fun getKnockbackResistance(): Float {
        return knockbackResistance
    }

    companion object {
        private val HEALTH_PER_SLOT = intArrayOf(13, 15, 16, 11)
    }
}

