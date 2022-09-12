package me.segalu.udemymod.init

import net.minecraft.tags.BlockTags
import net.minecraft.world.item.crafting.Ingredient
import net.minecraftforge.common.ForgeTier

object TierInit {
    val COBALT = ForgeTier(2, 1500, 10f, 3f, 10, BlockTags.NEEDS_IRON_TOOL) {
        Ingredient.of(ItemInit.COBALT_INGOT)
    }
}