package me.segalu.udemymod.datagen

import kotlinx.serialization.builtins.serializer
import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.datagen.client.ModBlockStateProvider
import me.segalu.udemymod.datagen.client.ModItemModelProvider
import me.segalu.udemymod.datagen.client.lang.ModEnUsProvider
import me.segalu.udemymod.datagen.client.lang.ModEsEsProvider
import me.segalu.udemymod.datagen.client.lang.ModEsMxProvider
import me.segalu.udemymod.datagen.client.lang.ModEsProvider
import me.segalu.udemymod.datagen.server.*
import net.minecraft.data.DataGenerator
import net.minecraft.data.DataProvider
import net.minecraft.data.HashCache
import net.minecraftforge.common.data.ExistingFileHelper
import net.minecraftforge.common.data.LanguageProvider
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent
import org.apache.commons.lang3.text.translate.JavaUnicodeEscaper
import java.beans.Encoder
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.pathString


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
            generator.addProvider(ModEsMxProvider(generator))
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

//        File(generator.outputFolder.resolve("assets/" + UdemyMod.ID + "/lang/" + "es_es" + ".json").pathString).copyTo(File(generator.outputFolder.resolve("assets/" + UdemyMod.ID + "/lang/" + "es_mx" + ".json").pathString))
    }

}