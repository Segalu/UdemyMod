package me.segalu.udemymod.datagen.server

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.recipe.CobaltBlasterRecipe
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.AdvancementRewards
import net.minecraft.advancements.CriterionTriggerInstance
import net.minecraft.advancements.RequirementsStrategy
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.level.ItemLike
import java.util.function.Consumer

class CobaltBlasterRecipeBuilder(private val ingredient: Ingredient, result: ItemLike, private val count: Int) : RecipeBuilder {
    private val result: Item
    private val advancement = Advancement.Builder.advancement()

    init {
        this.result = result.asItem()
    }

    override fun unlockedBy(pCriterionName: String, pCriterionTrigger: CriterionTriggerInstance): RecipeBuilder {
        advancement.addCriterion(pCriterionName, pCriterionTrigger)
        return this
    }

    override fun group(pGroupName: String?): RecipeBuilder {
        return this
    }

    override fun getResult(): Item {
        return result
    }

    override fun save(pFinishedRecipeConsumer: Consumer<FinishedRecipe>, pRecipeId: ResourceLocation) {
        advancement.parent(ResourceLocation("recipes/root"))
            .addCriterion(
                "has_the_recipe",
                RecipeUnlockedTrigger.unlocked(pRecipeId)
            )
            .rewards(AdvancementRewards.Builder.recipe(pRecipeId)).requirements(RequirementsStrategy.OR)

        pFinishedRecipeConsumer.accept(
            Result(
                pRecipeId, result, count, ingredient,
                advancement, ResourceLocation(
                    pRecipeId.namespace, "recipes/" +
                            result.itemCategory!!.recipeFolderName + "/" + pRecipeId.path
                )
            )
        )
    }

    class Result(
        private val id: ResourceLocation,
        private val result: Item,
        private val count: Int,
        private val ingredient: Ingredient,
        private val advancement: Advancement.Builder,
        private val advancementId: ResourceLocation
    ) : FinishedRecipe {
        override fun serializeRecipeData(pJson: JsonObject) {
            pJson.add("ingredients", ingredient.toJson())
            val jsonobject = JsonObject()
            jsonobject.addProperty("item", result.registryName.toString())
            if (count > 1) {
                jsonobject.addProperty("count", count)
            }
            pJson.add("output", jsonobject)
        }

        override fun getId(): ResourceLocation {
            return ResourceLocation(
                UdemyMod.ID,
                result.registryName!!.path + "_from_cobalt_blaster"
            )
        }

        override fun getType(): RecipeSerializer<*> {
            return CobaltBlasterRecipe.Companion.Serializer.INSTANCE
        }

        override fun serializeAdvancement(): JsonObject? {
            return advancement.serializeToJson()
        }

        override fun getAdvancementId(): ResourceLocation? {
            return advancementId
        }
    }

}