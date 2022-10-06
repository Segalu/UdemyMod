package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.command.ReturnHomeCommand
import me.segalu.udemymod.command.SetHomeCommand
import net.minecraftforge.event.RegisterCommandsEvent
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
}