package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey

object TagInit {
    object Blocks {
        private fun tag(name: String) = BlockTags.create(ResourceLocation(UdemyMod.ID, name))
        private fun forgeTag(name: String) = BlockTags.create(ResourceLocation("forge", name))

        val DOWSING_ROD_VALUABLES = tag("dowsing_rod_valuables")
        val PAXEL_MINEABLE = tag("mineable/paxel")
        val CHERRY_BLOSSOM_LOGS = tag("cherry_blossom_logs")

    }

    object Items {
        private fun tag(name: String) = ItemTags.create(ResourceLocation(UdemyMod.ID, name))
        private fun forgeTag(name: String) = ItemTags.create(ResourceLocation("forge", name))

        val CHERRY_BLOSSOM_LOGS = tag("cherry_blossom_logs")

        val COBALT_INGOTS = forgeTag("ingots/cobalt")
        val COBALT_NUGGETS = forgeTag("nuggets/cobalt")

    }

    object StructureBiomes {
        private fun tag(name: String) = TagKey.create(
            Registry.BIOME_REGISTRY,
            ResourceLocation(UdemyMod.ID, "has_structure/$name")
        )

        val CODE_STRUCTURE_SKY_FAN_BIOMES = tag("code_structure_sky_fan_biomes")
    }

}