package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.command.ReturnHomeCommand
import me.segalu.udemymod.command.SetHomeCommand
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Items
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.event.entity.living.LivingDamageEvent
import net.minecraftforge.event.entity.player.PlayerEvent.Clone
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.server.command.ConfigCommand

@EventBusSubscriber(modid = UdemyMod.ID)
object EventInit {
    @SubscribeEvent
    fun onCommandsRegister(event: RegisterCommandsEvent) {
        SetHomeCommand(event.dispatcher)
        ReturnHomeCommand(event.dispatcher)
        ConfigCommand.register(event.dispatcher)
    }

    @SubscribeEvent
    fun onPlayerCloneEvent(event: Clone) {
        if (!event.original.level.isClientSide) {
            event.player.persistentData.putIntArray(
                UdemyMod.ID + "homepos",
                event.original.persistentData.getIntArray(UdemyMod.ID + "homepos")
            )
        }
    }

    @SubscribeEvent
    fun setEntityOnFireWhenHit(event: LivingDamageEvent) {
        if(!event.entity.level.isClientSide) {
            if(event.source.directEntity is Player) {
                val player = event.source.directEntity as Player
                if (player.mainHandItem.item == Items.NETHER_BRICK) {
                    player.mainHandItem.shrink(1)
                    event.entityLiving.setSecondsOnFire(2)
                }
            }
        }
    }
}