package me.segalu.udemymod.entity.client

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.entity.TigerEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer

class TigerRenderer(renderManager: EntityRendererProvider.Context) :
    GeoEntityRenderer<TigerEntity>(renderManager, TigerModel()) {
    init {
        shadowRadius = 0.3f
    }

    override fun getTextureLocation(animatable: TigerEntity): ResourceLocation {
        return ResourceLocation(UdemyMod.ID, "textures/entity/tiger/tiger.png")
    }

    override fun getRenderType(
        animatable: TigerEntity,
        partialTick: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource?,
        buffer: VertexConsumer?,
        packedLight: Int,
        texture: ResourceLocation
    ): RenderType {
        poseStack.scale(1.5F, 1.5F, 1.5F)
        return RenderType.entityCutout(texture)
    }

}