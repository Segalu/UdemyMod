package me.segalu.udemymod.recipe

import com.google.gson.JsonObject
import me.segalu.udemymod.UdemyMod
import net.minecraft.core.NonNullList
import net.minecraft.network.FriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.GsonHelper
import net.minecraft.world.SimpleContainer
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.Recipe
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.item.crafting.ShapedRecipe
import net.minecraft.world.level.Level

class CobaltBlasterRecipe(
    private val pId: ResourceLocation,
    private val output: ItemStack,
    private val recipeItems: NonNullList<Ingredient>
) : Recipe<SimpleContainer> {

    override fun matches(pContainer: SimpleContainer, pLevel: Level): Boolean {
        return if (recipeItems.get(0).test(pContainer.getItem(1))) {
            recipeItems.get(1).test(pContainer.getItem(2))
        } else false
    }

    override fun assemble(pContainer: SimpleContainer) = output

    override fun canCraftInDimensions(pWidth: Int, pHeight: Int) = true

    override fun getResultItem(): ItemStack = output.copy()

    override fun getId() = pId

    override fun getSerializer() = Serializer.INSTANCE

    override fun getType() = Type.INSTANCE

    companion object {
        class Type : RecipeType<CobaltBlasterRecipe> {
            companion object {
                val INSTANCE = Type()
                const val ID = "cobalt_blasting"
            }
        }

        class Serializer : RecipeSerializer<CobaltBlasterRecipe> {
            companion object {
                val INSTANCE = Serializer()
                val ID = ResourceLocation(UdemyMod.ID, "cobalt_blasting")

                @SuppressWarnings("unchecked")
                private fun <G> castClass(cls: Class<*>) = cls as Class<G>
            }

            override fun setRegistryName(name: ResourceLocation?) = INSTANCE

            override fun getRegistryName() = ID

            override fun getRegistryType(): Class<RecipeSerializer<*>> = castClass(RecipeSerializer::class.java)

            override fun fromJson(pRecipeId: ResourceLocation, pSerializedRecipe: JsonObject): CobaltBlasterRecipe {
                val output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"))
                val ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients")
                val inputs = NonNullList.withSize(2, Ingredient.EMPTY)

                for (input in 0 until inputs.size) {
                    inputs.set(input, Ingredient.fromJson(ingredients.get(input)))
                }

                return CobaltBlasterRecipe(pRecipeId, output, inputs)
            }

            override fun fromNetwork(pRecipeId: ResourceLocation, pBuffer: FriendlyByteBuf): CobaltBlasterRecipe {
                val inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY)
                for (input in 0 until inputs.size) {
                    inputs.set(input, Ingredient.fromNetwork(pBuffer))
                }
                val output = pBuffer.readItem()

                return CobaltBlasterRecipe(pRecipeId, output, inputs)
            }

            override fun toNetwork(pBuffer: FriendlyByteBuf, pRecipe: CobaltBlasterRecipe) {
                pBuffer.writeInt(pRecipe.ingredients.size)
                for (ingredient in pRecipe.ingredients) {
                    ingredient.toNetwork(pBuffer)
                }
                pBuffer.writeItemStack(pRecipe.resultItem, false)
            }

        }
    }
}