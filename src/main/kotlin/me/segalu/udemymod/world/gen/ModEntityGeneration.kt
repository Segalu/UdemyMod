package me.segalu.udemymod.world.gen

import me.segalu.udemymod.entity.RaccoonEntity
import me.segalu.udemymod.init.EntityInit
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.biome.Biomes
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraftforge.event.world.BiomeLoadingEvent
import java.util.*


object ModEntityGeneration {
    fun onEntitySpawn(event: BiomeLoadingEvent) {
        addEntityToAllOverworldBiomes(event, EntityInit.RACCOON, 40, 2, 4)
        addEntityToSpecificBiomes(event, EntityInit.TIGER, 40, 2, 4, Biomes.JUNGLE, Biomes.BEACH, Biomes.BAMBOO_JUNGLE, Biomes.SAVANNA, Biomes.SPARSE_JUNGLE, Biomes.SAVANNA_PLATEAU)
    }

    private fun addEntityToAllBiomesExceptThese(event: BiomeLoadingEvent, type: EntityType<*>,weight: Int, minCount: Int, maxCount: Int, vararg biomes: ResourceKey<Biome>) {
        val isBiomeSelected: Boolean = biomes.map(ResourceKey<*>::location).map(Any::toString).any { it == event.name.toString() }
        if (!isBiomeSelected) {
            addEntityToAllBiomes(event, type, weight, minCount, maxCount)
        }
    }

    private fun addEntityToSpecificBiomes(event: BiomeLoadingEvent, type: EntityType<*>,weight: Int, minCount: Int, maxCount: Int, vararg biomes: ResourceKey<Biome>) {
        val isBiomeSelected: Boolean = biomes.map(ResourceKey<*>::location).map(Any::toString).any { it == event.name.toString() }
        if (isBiomeSelected) {
            addEntityToAllBiomes(event, type, weight, minCount, maxCount)
        }
    }

    private fun addEntityToAllOverworldBiomes(
        event: BiomeLoadingEvent,
        type: EntityType<*>,
        weight: Int,
        minCount: Int,
        maxCount: Int
    ) {
        if (!event.category.equals(Biome.BiomeCategory.THEEND) && !event.category.equals(Biome.BiomeCategory.NETHER)) {
            val base = event.spawns.getSpawner(type.category)
            base.add(MobSpawnSettings.SpawnerData(type, weight, minCount, maxCount))
        }
    }

    private fun addEntityToAllNetherBiomes(
        event: BiomeLoadingEvent,
        type: EntityType<*>,
        weight: Int,
        minCount: Int,
        maxCount: Int
    ) {
        if (event.category.equals(Biome.BiomeCategory.NETHER)) {
            val base = event.spawns.getSpawner(type.category)
            base.add(MobSpawnSettings.SpawnerData(type, weight, minCount, maxCount))
        }
    }

    private fun addEntityToAllEndBiomes(
        event: BiomeLoadingEvent,
        type: EntityType<*>,
        weight: Int,
        minCount: Int,
        maxCount: Int
    ) {
        if (event.category.equals(Biome.BiomeCategory.THEEND)) {
            val base = event.spawns.getSpawner(type.category)
            base.add(MobSpawnSettings.SpawnerData(type, weight, minCount, maxCount))
        }
    }

    private fun addEntityToAllBiomes(
        event: BiomeLoadingEvent,
        type: EntityType<*>,
        weight: Int,
        minCount: Int,
        maxCount: Int
    ) {
        val base = event.spawns.getSpawner(type.category)
        base.add(MobSpawnSettings.SpawnerData(type, weight, minCount, maxCount))
    }

}