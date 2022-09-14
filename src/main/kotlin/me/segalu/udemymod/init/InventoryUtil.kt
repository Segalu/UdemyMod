package me.segalu.udemymod.init

import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack

object InventoryUtil {
    fun hasPlayerStackInInventory(player: Player, item: Item?): Boolean {
        for (i in 0 until player.getInventory().getContainerSize()) {
            val currentStack: ItemStack = player.getInventory().getItem(i)
            if (!currentStack.isEmpty() && currentStack.sameItem(ItemStack(item))) {
                return true
            }
        }
        return false
    }

    fun getFirstInventoryIndex(player: Player, item: Item?): Int {
        for (i in 0 until player.getInventory().getContainerSize()) {
            val currentStack: ItemStack = player.getInventory().getItem(i)
            if (!currentStack.isEmpty() && currentStack.sameItem(ItemStack(item))) {
                return i
            }
        }
        return -1
    }
}