package me.segalu.udemymod.entity.client

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.entity.ModBoatEntity
import net.minecraft.client.model.BoatModel
import net.minecraft.client.model.geom.ModelLayerLocation
import net.minecraft.client.renderer.entity.BoatRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.vehicle.Boat
import com.mojang.datafixers.util.Pair


class ModBoatRenderer(pContext: EntityRendererProvider.Context) : BoatRenderer(pContext) {
    private val boatResources: Map<ModBoatEntity.Type, Pair<ResourceLocation, BoatModel>>

    init {
        shadowRadius = 0.8F
        boatResources = buildMap(5) {
            ModBoatEntity.Type.values().forEach {
                put(
                    it, Pair.of(
                        ResourceLocation(UdemyMod.ID, "textures/entity/boat/${it.name.lowercase()}.png"),
                        BoatModel(
                            pContext.bakeLayer(
                                ModelLayerLocation(ResourceLocation("minecraft", "boat/oak"), "main")
                            )
                        )
                    )
                )
            }
        }
    }

    override fun getModelWithLocation(boat: Boat): Pair<ResourceLocation, BoatModel> {
        boat as ModBoatEntity
        return boatResources[boat.getModBoatType()]!!
    }

}