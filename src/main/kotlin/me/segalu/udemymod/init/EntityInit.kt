package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.entity.RaccoonEntity
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.Level
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.ForgeRegistries.Keys.ENTITY_TYPES
import net.minecraftforge.registries.RegistryObject
import thedarkcolour.kotlinforforge.forge.registerObject


object EntityInit {
    val ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, UdemyMod.ID)

    val RACCOON by ENTITIES.registerObject("raccoon") {
        EntityType.Builder.of(::RaccoonEntity, MobCategory.CREATURE)
            .sized(0.8F, 0.6F)
            .build(ResourceLocation(UdemyMod.ID, "raccoon").toString())
    }

}