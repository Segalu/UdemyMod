package me.segalu.udemymod.datagen

import me.segalu.udemymod.datagen.client.ModBlockStateProvider
import me.segalu.udemymod.datagen.client.ModItemModelProvider
import me.segalu.udemymod.datagen.client.lang.ModEnUsProvider
import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.datagen.client.lang.ModEsEsProvider
import me.segalu.udemymod.datagen.server.*
import net.minecraft.data.DataGenerator
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent


@Mod.EventBusSubscriber(modid = UdemyMod.ID, bus = Bus.MOD)
object UdemyModDataGeneration {

    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
       val generator: DataGenerator = event.generator
        val helper: ExistingFileHelper = event.existingFileHelper

        if (event.includeClient()) {
            generator.addProvider(ModBlockStateProvider(generator, helper))
            generator.addProvider(ModItemModelProvider(generator, helper))
            generator.addProvider(ModEnUsProvider(generator))
            generator.addProvider(ModEsEsProvider(generator))
        }

        if (event.includeServer()) {
            val blockTags = ModBlockTagsProvider(generator, helper)
            val forgeBlockTags = ForgeBlockTagsProvider(generator, helper)
            val minecraftFluidTags = MinecraftFluidTagsProvider(generator, helper)
            val minecraftBlockTags = MinecraftBlockTagsProvider(generator, helper)

            generator.addProvider(ModRecipeProvider(generator))
            generator.addProvider(blockTags)
            generator.addProvider(forgeBlockTags)
            generator.addProvider(minecraftBlockTags)
            generator.addProvider(ModItemTagsProvider(generator, blockTags, helper))
            generator.addProvider(ForgeItemTagsProvider(generator, forgeBlockTags, helper))
            generator.addProvider(MinecraftItemTagsProvider(generator, minecraftBlockTags, helper))
            generator.addProvider(ModLootTableProvider(generator))
            generator.addProvider(ModAdvancementProvider(generator, helper))
            generator.addProvider(ModGlobalLootModifierProvider(generator))
            generator.addProvider(minecraftFluidTags)
        }

    }

}