package me.segalu.udemymod.datagen.server

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.TagInit
import net.minecraft.core.Registry
import net.minecraft.data.DataGenerator
import net.minecraft.data.tags.BiomeTagsProvider
import net.minecraft.data.tags.ConfiguredStructureTagsProvider
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BiomeTags
import net.minecraft.tags.ConfiguredStructureTags
import net.minecraft.tags.Tag.TagEntry
import net.minecraft.world.level.biome.Biomes
import net.minecraftforge.common.data.ExistingFileHelper

class ModStructureBiomesTagProvider(pGenerator: DataGenerator, existingFileHelper: ExistingFileHelper?) :
    BiomeTagsProvider(pGenerator, UdemyMod.ID, existingFileHelper) {
    override fun addTags() {
        tag(TagInit.StructureBiomes.CODE_STRUCTURE_SKY_FAN_BIOMES).add(
            TagEntry(BiomeTags.IS_MOUNTAIN.location)
        )
        tag(TagInit.StructureBiomes.CODE_STRUCTURE_SKY_FAN_BIOMES).add(
            TagEntry(BiomeTags.IS_OCEAN.location)
        )
        tag(TagInit.StructureBiomes.CODE_STRUCTURE_SKY_FAN_BIOMES).add(
            TagEntry(BiomeTags.IS_DEEP_OCEAN.location)
        )
        tag(TagInit.StructureBiomes.CODE_STRUCTURE_SKY_FAN_BIOMES).add(
            Biomes.SWAMP
        )
    }

}

class ModStructureConfiguredTagProvider(
    pGenerator: DataGenerator,
    existingFileHelper: ExistingFileHelper?
) : ConfiguredStructureTagsProvider(pGenerator, "minecraft", existingFileHelper) {
    override fun addTags() {
        tag(ConfiguredStructureTags.ON_OCEAN_EXPLORER_MAPS).add(
            ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, ResourceLocation(UdemyMod.ID, "code_structure_sky_fan"))
        )
    }

}
