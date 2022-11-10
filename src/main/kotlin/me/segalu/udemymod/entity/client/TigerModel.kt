package me.segalu.udemymod.entity.client

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.entity.TigerEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class TigerModel : AnimatedGeoModel<TigerEntity>() {
    override fun getModelLocation(`object`: TigerEntity?): ResourceLocation {
        return ResourceLocation(UdemyMod.ID, "geo/tiger.geo.json")
    }

    override fun getTextureLocation(`object`: TigerEntity?): ResourceLocation {
        return ResourceLocation(UdemyMod.ID, "textures/entity/tiger.png")
    }

    override fun getAnimationFileLocation(animatable: TigerEntity?): ResourceLocation {
        return ResourceLocation(UdemyMod.ID, "animations/tiger.animation.json")
    }

}