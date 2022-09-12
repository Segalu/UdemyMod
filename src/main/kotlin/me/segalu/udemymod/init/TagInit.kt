package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraftforge.common.Tags

object TagInit {
    object Blocks {
        private fun tag(name: String) = BlockTags.create(ResourceLocation(UdemyMod.ID, name))
        private fun forgeTag(name: String) = BlockTags.create(ResourceLocation("forge", name))

        val DOWSING_ROD_VALUABLES = tag("dowsing_rod_valuables")
        val PAXEL_MINEABLE = tag("mineable/paxel")
    }
    object Items {
        private fun tag(name: String) = ItemTags.create(ResourceLocation(UdemyMod.ID, name))
        private fun forgeTag(name: String) = ItemTags.create(ResourceLocation("forge", name))

        val COBALT_INGOTS = forgeTag("ingots/cobalt")
        val COBALT_NUGGETS = forgeTag("nuggets/cobalt")
    }
}