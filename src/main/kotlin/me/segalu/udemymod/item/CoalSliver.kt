package me.segalu.udemymod.item

import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeType

class CoalSliver(pProperties: Properties): Item(pProperties) {
    override fun getBurnTime(itemStack: ItemStack?, recipeType: RecipeType<*>?): Int {
        return 400
    }
}