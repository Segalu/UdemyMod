package me.segalu.udemymod.command

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.exceptions.CommandSyntaxException
import me.segalu.udemymod.UdemyMod
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.TextComponent


class SetHomeCommand(dispatcher: CommandDispatcher<CommandSourceStack>) {
    init {
        dispatcher.register(Commands.literal("home").then(Commands.literal("set").executes {
            return@executes setHome(it.source)
        }))
    }

    @Throws(CommandSyntaxException::class)
    private fun setHome(source: CommandSourceStack): Int {
        val player = source.playerOrException
        val playerPos = player.blockPosition()
        val pos = "(${playerPos.x}, ${playerPos.y}, ${playerPos.z})"

        player.persistentData.putIntArray(UdemyMod.ID + "homepos", intArrayOf(playerPos.x, playerPos.y, playerPos.z))
        source.sendSuccess(TextComponent("Set home at $pos"), true)
        return 1
    }
}