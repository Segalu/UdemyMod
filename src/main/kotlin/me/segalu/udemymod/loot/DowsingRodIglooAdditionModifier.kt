package me.segalu.udemymod.loot

import com.google.gson.JsonObject
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.GsonHelper
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraftforge.common.loot.GlobalLootModifierSerializer
import net.minecraftforge.common.loot.LootModifier
import net.minecraftforge.registries.ForgeRegistries

class DowsingRodIglooAdditionModifier(conditionsIn: Array<out LootItemCondition>, private val addition: Item) : LootModifier(conditionsIn) {
    override fun doApply(generatedLoot: MutableList<ItemStack>, context: LootContext): MutableList<ItemStack> {
        if (context.random.nextFloat() > 0.05) {
            generatedLoot.add(ItemStack(addition, 1))
        }
        return generatedLoot
    }

    object Serializer: GlobalLootModifierSerializer<DowsingRodIglooAdditionModifier>() {
        override fun read(
            location: ResourceLocation,
            `object`: JsonObject,
            ailootcondition: Array<out LootItemCondition>
        ): DowsingRodIglooAdditionModifier {
            val addition = ForgeRegistries.ITEMS.getValue(
                ResourceLocation(GsonHelper.getAsString(`object`, "addition"))
            )
            return DowsingRodIglooAdditionModifier(ailootcondition, addition!!)
        }

        override fun write(instance: DowsingRodIglooAdditionModifier): JsonObject {
            val json = makeConditions(instance.conditions)
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString())
            return json
        }

    }
}