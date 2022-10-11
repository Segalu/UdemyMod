package me.segalu.udemymod.datagen.server

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.ItemInit
import me.segalu.udemymod.loot.DowsingRodIglooAdditionModifier
import me.segalu.udemymod.loot.TurnipSeedsFromGrassAdditionModifier
import net.minecraft.data.DataGenerator
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition
import net.minecraftforge.common.data.GlobalLootModifierProvider
import net.minecraftforge.common.loot.LootTableIdCondition

class ModGlobalLootModifierProvider(gen: DataGenerator) : GlobalLootModifierProvider(gen, UdemyMod.ID) {
    override fun start() {
        add(
            "turnip_seeds_from_grass",
            TurnipSeedsFromGrassAdditionModifier.Serializer,
            TurnipSeedsFromGrassAdditionModifier(
                arrayOf(
                    LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS).build()
                ),
                ItemInit.TURNIP_SEEDS
            )
        )
        add(
            "dowsing_rod_in_igloo",
            DowsingRodIglooAdditionModifier.Serializer,
            DowsingRodIglooAdditionModifier(
                arrayOf(
                    LootTableIdCondition.Builder(ResourceLocation("minecraft","chests/igloo_chest")).build()
                ),
                ItemInit.DOWSING_ROD
            )
        )
    }


}