package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.recipe.CobaltBlasterRecipe
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object RecipeInit {
    val SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, UdemyMod.ID)

    val COBALT_BLASTER_SERIALIZER by SERIALIZERS.registerObject("cobalt_blasting") {
        CobaltBlasterRecipe.Companion.Serializer.INSTANCE
    }
}