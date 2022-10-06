package me.segalu.udemymod.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.exceptions.CommandSyntaxException
import me.segalu.udemymod.UdemyMod
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.TextComponent
import kotlin.jvm.Throws

class ReturnHomeCommand(dispatcher: CommandDispatcher<CommandSourceStack>) {
    init {
        dispatcher.register(Commands.literal("home").then(Commands.literal("return").executes {
            return@executes returnHome(it.source)
        }))
    }

    @Throws(CommandSyntaxException::class)
    private fun returnHome(source: CommandSourceStack): Int {
        val player = source.playerOrException
        val hasSetHome = player.persistentData.getIntArray(UdemyMod.ID + "homepos").isNotEmpty()

        return if (hasSetHome) {
            val homePosition = player.persistentData.getIntArray(UdemyMod.ID + "homepos").map(Int::toDouble)
            player.teleportTo(homePosition[0], homePosition[1], homePosition[2])

            source.sendSuccess(TextComponent("Player returned home!!!"), true)
            1
        } else {
            source.sendFailure(TextComponent("No home set"))
            -1
        }
    }
}