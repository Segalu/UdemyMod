package me.segalu.udemymod.entity.client

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.entity.RaccoonEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class RaccoonRenderer(renderManager: EntityRendererProvider.Context) :
    GeoEntityRenderer<RaccoonEntity>(renderManager, RaccoonModel()) {
    init {
        shadowRadius = 0.3f
    }

    override fun getTextureLocation(instance: RaccoonEntity): ResourceLocation {
        return ResourceLocation(UdemyMod.ID, "textures/entity/raccoon/raccoon.png")
    }

    override fun getRenderType(
        animatable: RaccoonEntity,
        partialTicks: Float,
        stack: PoseStack,
        bufferSource: MultiBufferSource?,
        buffer: VertexConsumer?,
        packedLightIn: Int,
        texture: ResourceLocation
    ): RenderType {
        stack.scale(0.8f, 0.8f, 0.8f)
        return RenderType.entityCutout(texture)
    }
}