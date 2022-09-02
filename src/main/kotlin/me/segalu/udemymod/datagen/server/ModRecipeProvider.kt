package me.segalu.udemymod.datagen.server

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.BlockInit
import me.segalu.udemymod.init.ItemInit
import net.minecraft.data.DataGenerator
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeProvider
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder
import net.minecraft.data.recipes.SingleItemRecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.SmeltingRecipe
import java.util.function.Consumer

class ModRecipeProvider(generator: DataGenerator) : RecipeProvider(generator) {

    override fun buildCraftingRecipes(consumer: Consumer<FinishedRecipe>) {
        //Items
        ShapedRecipeBuilder.shaped(Items.NETHER_STAR)
            .define('0', Items.DIAMOND)
            .define('1', Items.LAVA_BUCKET)
            .define('2', ItemInit.COBALT_INGOT)
            .pattern("202")
            .pattern("010")
            .pattern("202")
            .unlockedBy("has_${ItemInit.COBALT_INGOT.registryName!!.path}", has(ItemInit.COBALT_INGOT))
            .save(
                consumer, ResourceLocation(
                    UdemyMod.ID,
                    Items.NETHER_STAR.registryName!!.path + "_from_cobalt"
                )
            )

        //Stacks
        ShapedRecipeBuilder.shaped(BlockInit.COBALT_BLOCK.asItem())
            .define('#', ItemInit.COBALT_INGOT)
            .pattern("###")
            .pattern("###")
            .pattern("###")
            .unlockedBy("has_${ItemInit.COBALT_INGOT.registryName!!.path}", has(ItemInit.COBALT_INGOT))
            .save(
                consumer, ResourceLocation(
                    UdemyMod.ID,
                    BlockInit.COBALT_BLOCK.asItem().registryName!!.path
                )
            )

        ShapedRecipeBuilder.shaped(ItemInit.COBALT_INGOT)
            .define('#', ItemInit.COBALT_NUGGET)
            .pattern("###")
            .pattern("###")
            .pattern("###")
            .unlockedBy("has_${ItemInit.COBALT_NUGGET.registryName!!.path}", has(ItemInit.COBALT_NUGGET))
            .save(
                consumer, ResourceLocation(
                    UdemyMod.ID,
                    ItemInit.COBALT_INGOT.registryName!!.path + "_from_nugget"
                )
            )

        ShapelessRecipeBuilder.shapeless(ItemInit.COBALT_NUGGET, 9)
            .requires(ItemInit.COBALT_INGOT)
            .unlockedBy("has_${ItemInit.COBALT_INGOT.registryName!!.path}", has(ItemInit.COBALT_INGOT))
            .save(
                consumer, ResourceLocation(
                    UdemyMod.ID,
                    ItemInit.COBALT_NUGGET.registryName!!.path
                )
            )

        //Ores smelting & blasting
        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(BlockInit.COBALT_ORE.asItem()),
            ItemInit.COBALT_INGOT,
            10f,  200
        )
            .unlockedBy("has_${BlockInit.COBALT_ORE.asItem().registryName!!.path}",
            has(BlockInit.COBALT_ORE.asItem()))
            .save(
                consumer, ResourceLocation(
                    UdemyMod.ID,
                    ItemInit.COBALT_INGOT.registryName!!.path + "_from_cobalt_ore_smelting"
                )
            )

        SimpleCookingRecipeBuilder.blasting(
            Ingredient.of(BlockInit.COBALT_ORE.asItem()),
            ItemInit.COBALT_INGOT,
            1f,  100
        )
            .unlockedBy("has_${BlockInit.COBALT_ORE.asItem().registryName!!.path}",
                has(BlockInit.COBALT_ORE.asItem()))
            .save(
                consumer, ResourceLocation(
                    UdemyMod.ID,
                    ItemInit.COBALT_INGOT.registryName!!.path + "_from_cobalt_ore_blasting"
                )
            )

        SimpleCookingRecipeBuilder.smelting(
            Ingredient.of(BlockInit.DEEPSLATE_COBALT_ORE.asItem()),
            ItemInit.COBALT_INGOT,
            20f,  300
        )
            .unlockedBy("has_${BlockInit.DEEPSLATE_COBALT_ORE.asItem().registryName!!.path}",
                has(BlockInit.DEEPSLATE_COBALT_ORE.asItem()))
            .save(
                consumer, ResourceLocation(
                    UdemyMod.ID,
                    ItemInit.COBALT_INGOT.registryName!!.path + "_from_deepslate_cobalt_ore_smelting"
                )
            )

        SimpleCookingRecipeBuilder.blasting(
            Ingredient.of(BlockInit.DEEPSLATE_COBALT_ORE.asItem()),
            ItemInit.COBALT_INGOT,
            10f, 150
        )
            .unlockedBy("has_${BlockInit.DEEPSLATE_COBALT_ORE.asItem().registryName!!.path}",
                has(BlockInit.DEEPSLATE_COBALT_ORE.asItem()))
            .save(
                consumer, ResourceLocation(
                    UdemyMod.ID,
                    ItemInit.COBALT_INGOT.registryName!!.path + "_from_deepslate_cobalt_ore_blasting"
                )
            )
    }
}