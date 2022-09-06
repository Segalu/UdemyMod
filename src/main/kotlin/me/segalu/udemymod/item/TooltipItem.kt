package me.segalu.udemymod.item

import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.TranslatableComponent
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block

class TooltipItem(block: Block, pProperties: Properties, private val tooltip: String) : BlockItem(block, pProperties) {
    override fun appendHoverText(
        pStack: ItemStack,
        pLevel: Level?,
        pTooltip: MutableList<Component>,
        pFlag: TooltipFlag
    ) {
        pTooltip.add(TranslatableComponent(tooltip))
    }
}

class ShiftTooltipItem(
    block: Block,
    pProperties: Properties,
    private val tooltip: String,
    private val shiftTooltip: String
) : BlockItem(block, pProperties) {
    override fun appendHoverText(
        pStack: ItemStack,
        pLevel: Level?,
        pTooltip: MutableList<Component>,
        pFlag: TooltipFlag
    ) {
        if (Screen.hasShiftDown()) {
            pTooltip.add(TranslatableComponent(shiftTooltip))
        } else {
            pTooltip.add((TranslatableComponent(tooltip)))
        }
    }
}