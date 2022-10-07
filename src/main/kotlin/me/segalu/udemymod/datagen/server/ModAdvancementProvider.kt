package me.segalu.udemymod.datagen.server

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.BlockInit
import me.segalu.udemymod.init.ItemInit
import net.minecraft.advancements.Advancement
import net.minecraft.advancements.FrameType
import net.minecraft.advancements.critereon.InventoryChangeTrigger
import net.minecraft.data.DataGenerator
import net.minecraft.data.advancements.AdvancementProvider
import net.minecraft.network.chat.TranslatableComponent
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.common.data.ExistingFileHelper
import java.util.function.Consumer

class ModAdvancementProvider(generator: DataGenerator, helper: ExistingFileHelper) :
    AdvancementProvider(generator, helper) {

    override fun registerAdvancements(consumer: Consumer<Advancement>, fileHelper: ExistingFileHelper) {
        val UDEMYMOD = Advancement.Builder.advancement().display(
            ItemInit.COBALT_INGOT,
            TranslatableComponent("udemymod.advancements.root.title"),
            TranslatableComponent("udemymod.advancements.root.description"),
            ResourceLocation(UdemyMod.ID, "textures/block/${BlockInit.COBALT_BLOCK.get().registryName!!.path}.png"),
            FrameType.TASK,
            true,
            true,
            false
        ).addCriterion("cobalt_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ItemInit.COBALT_INGOT))
            .save(consumer, ResourceLocation(UdemyMod.ID, "udemymod"), fileHelper)

        val DOWSING_ROD = Advancement.Builder.advancement().parent(UDEMYMOD).display(
            ItemInit.DOWSING_ROD,
            TranslatableComponent("udemymod.advancements.dowsing_rod.title"),
            TranslatableComponent("udemymod.advancements.dowsing_rod.description"),
            null,
            FrameType.TASK,
            true,
            true,
            false
        ).addCriterion("dowsing_rod", InventoryChangeTrigger.TriggerInstance.hasItems(ItemInit.DOWSING_ROD))
            .save(consumer, ResourceLocation(UdemyMod.ID, "dowsing_rod"), fileHelper)

    }
}