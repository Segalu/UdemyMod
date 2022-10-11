package me.segalu.udemymod.loot

import me.segalu.udemymod.UdemyMod
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.common.loot.GlobalLootModifierSerializer
import net.minecraftforge.event.RegistryEvent
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
}

