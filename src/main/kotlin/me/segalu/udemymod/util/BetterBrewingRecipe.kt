package me.segalu.udemymod.util

import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.alchemy.Potion
import net.minecraft.world.item.alchemy.PotionUtils
import net.minecraftforge.common.brewing.IBrewingRecipe

// BetterBrewingRecipe Class by CAS-ual-TY from https://github.com/CAS-ual-TY/Extra-Potions (GPL-3.0 License)
// https://github.com/CAS-ual-TY/Extra-Potions/blob/main/LICENSE
class BetterBrewingRecipe(private val input: Potion, private val ingredient: Item, private val output: Potion) :
    IBrewingRecipe {
    override fun isInput(input: ItemStack): Boolean {
        return PotionUtils.getPotion(input) == this.input
    }
    
    override fun isIngredient(ingredient: ItemStack): Boolean {
        return ingredient.item == this.ingredient
    }

    override fun getOutput(input: ItemStack, ingredient: ItemStack): ItemStack {
        if (!this.isInput(input) || !this.isIngredient(ingredient)) {
            return ItemStack.EMPTY
        }

        val itemStack = ItemStack(input.item)
        itemStack.tag = CompoundTag()
        PotionUtils.setPotion(itemStack, this.output)
        return itemStack
    }

}