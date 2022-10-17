package me.segalu.udemymod.screen.slot

import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.SlotItemHandler

class ModFuelSlot(itemHandler: IItemHandler?, index: Int, xPosition: Int, yPosition: Int) : SlotItemHandler(
    itemHandler, index,
    xPosition,
    yPosition
) {

    override fun mayPlace(stack: ItemStack): Boolean {
        return AbstractFurnaceBlockEntity.isFuel(stack) || isBucket(stack)
    }

    override fun getMaxStackSize(stack: ItemStack): Int {
        return if (isBucket(stack)) 1 else super.getMaxStackSize(stack)
    }

    companion object {
        fun isBucket(stack: ItemStack): Boolean {
            return stack.`is`(Items.BUCKET)
        }
    }
}