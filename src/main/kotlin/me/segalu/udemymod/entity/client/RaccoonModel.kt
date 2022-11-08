package me.segalu.udemymod.entity.client

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.entity.RaccoonEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

class RaccoonModel : AnimatedGeoModel<RaccoonEntity>() {
    override fun getModelLocation(`object`: RaccoonEntity): ResourceLocation {
        return ResourceLocation(UdemyMod.ID, "geo/raccoon.geo.json")
    }

    override fun getTextureLocation(`object`: RaccoonEntity): ResourceLocation {
        return ResourceLocation(UdemyMod.ID, "textures/entity/raccoon/raccoon.png")
    }

    override fun getAnimationFileLocation(animatable: RaccoonEntity): ResourceLocation {
        return ResourceLocation(UdemyMod.ID, "animations/raccoon.animation.json")
    }

}