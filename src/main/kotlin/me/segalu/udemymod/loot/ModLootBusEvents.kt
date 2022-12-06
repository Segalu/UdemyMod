package me.segalu.udemymod.loot

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.entity.RaccoonEntity
import me.segalu.udemymod.entity.TigerEntity
import me.segalu.udemymod.init.EntityInit
import me.segalu.udemymod.recipe.CobaltBlasterRecipe
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraftforge.common.loot.GlobalLootModifierSerializer
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.entity.EntityAttributeCreationEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber


@EventBusSubscriber(modid = UdemyMod.ID, bus = EventBusSubscriber.Bus.MOD)
object ModLootBusEvents {
    @SubscribeEvent
    fun registerModifierSerializers(event: RegistryEvent.Register<GlobalLootModifierSerializer<*>>) {
        event.registry.registerAll(
            TurnipSeedsFromGrassAdditionModifier.Serializer.setRegistryName(
                ResourceLocation(
                    UdemyMod.ID,
                    "turnip_seeds_from_grass"
                )
            ),
            DowsingRodIglooAdditionModifier.Serializer.setRegistryName(
                ResourceLocation(
                    UdemyMod.ID,
                    "dowsing_rod_in_igloo"
                )
            ),
        )
    }

    @SubscribeEvent
    fun entityAttributeEvent(event: EntityAttributeCreationEvent) {
        event.put(EntityInit.RACCOON, RaccoonEntity.setAttributes())
        event.put(EntityInit.TIGER, TigerEntity.setAttributes())
    }

    @SubscribeEvent
    fun registerRecipeTypes(event: RegistryEvent.Register<RecipeSerializer<*>>) {
        Registry.register(Registry.RECIPE_TYPE, CobaltBlasterRecipe.Companion.Type.ID, CobaltBlasterRecipe.Companion.Type.INSTANCE)
    }

}

