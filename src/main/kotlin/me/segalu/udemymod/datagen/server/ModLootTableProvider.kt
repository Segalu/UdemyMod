package me.segalu.udemymod.datagen.server

import com.google.common.collect.ImmutableList
import com.mojang.datafixers.util.Pair
import me.segalu.udemymod.datagen.server.loot.ModBlockLootTables
import net.minecraft.data.DataGenerator
import net.minecraft.data.loot.LootTableProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.level.storage.loot.LootTables
import net.minecraft.world.level.storage.loot.ValidationContext
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.function.Supplier

class ModLootTableProvider(pGenerator: DataGenerator?) : LootTableProvider(pGenerator) {
    private val loot_tables: List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> =
        ImmutableList.of(
            Pair.of(
                Supplier { ModBlockLootTables() }, LootContextParamSets.BLOCK
            )
        )


    override fun getTables(): List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> {
        return loot_tables
    }

    override fun validate(map: MutableMap<ResourceLocation, LootTable>, validationtracker: ValidationContext) {
        map.forEach { (id, table) -> LootTables.validate(validationtracker, id, table) }
    }
}