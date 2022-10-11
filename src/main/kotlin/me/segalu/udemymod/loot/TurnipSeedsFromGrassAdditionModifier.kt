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

class TurnipSeedsFromGrassAdditionModifier(conditionsIn: Array<out LootItemCondition>, private val addition: Item) :
    LootModifier(conditionsIn) {
    override fun doApply(generatedLoot: MutableList<ItemStack>, context: LootContext): MutableList<ItemStack> {
        generatedLoot.add(ItemStack(addition, 1))
        return generatedLoot
    }

    object Serializer : GlobalLootModifierSerializer<TurnipSeedsFromGrassAdditionModifier>() {
        override fun read(
            location: ResourceLocation,
            `object`: JsonObject,
            ailootcondition: Array<out LootItemCondition>
        ): TurnipSeedsFromGrassAdditionModifier {
            val addition = ForgeRegistries.ITEMS.getValue(
                ResourceLocation(GsonHelper.getAsString(`object`, "addition"))
            )
            return TurnipSeedsFromGrassAdditionModifier(ailootcondition, addition!!)
        }

        override fun write(instance: TurnipSeedsFromGrassAdditionModifier): JsonObject {
            val json = makeConditions(instance.conditions)
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString())
            return json
        }

    }
}