package me.segalu.udemymod.datagen.server

import net.minecraft.data.DataGenerator
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeProvider
import java.util.function.Consumer

class ModRecipeProvider(generator: DataGenerator) : RecipeProvider(generator) {

    override fun buildCraftingRecipes(consumer: Consumer<FinishedRecipe>) {
    }
}