package me.segalu.udemymod.item

import me.segalu.udemymod.init.InventoryUtil.getFirstInventoryIndex
import me.segalu.udemymod.init.InventoryUtil.hasPlayerStackInInventory
import me.segalu.udemymod.init.ItemInit
import me.segalu.udemymod.init.SoundInit
import me.segalu.udemymod.init.TagInit
import net.minecraft.client.gui.screens.Screen
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.TextComponent
import net.minecraft.network.chat.TranslatableComponent
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraftforge.registries.ForgeRegistries


class DowsingRodItem(pProperties: Properties) : Item(pProperties) {

    private fun addNbtToDataTablet(player: Player, pos: BlockPos, blockBelow: Block) {
        val dataTablet = player.inventory.getItem(getFirstInventoryIndex(player, ItemInit.DATA_TABLET))
        val nbtData = CompoundTag()
        nbtData.putString(
            "udemymod.last_ore",
            "Found ${blockBelow.asItem().registryName.toString()} at (${pos.x}, ${pos.y}, ${pos.z})"
        )
        dataTablet.tag = nbtData
    }

    override fun useOn(pContext: UseOnContext): InteractionResult {
        if (pContext.level.isClientSide) {
            val pos: BlockPos = pContext.clickedPos
            val player: Player? = pContext.player
            var foundBlock: Boolean = false

            for (i in 0..pos.y + 64) {
                val blockBelow: Block = pContext.level.getBlockState(pos.below(i)).block

                if (isValuableBlock(blockBelow)) {
                    outputValuableCoordinates(pos.below(i), player, blockBelow)
                    foundBlock = true

                    if (hasPlayerStackInInventory(player!!, ItemInit.DATA_TABLET)) {
                        addNbtToDataTablet(player!!, pos.below(i), blockBelow);
                    }

//                    player.playSound(SoundInit.DOWSING_ROD_FOUND_ORE.get(), 1f, 1f)
                    pContext.level.playSound(player, pos, SoundInit.DOWSING_ROD_FOUND_ORE.get()
                    , SoundSource.BLOCKS, 1F, 1F)

                    break
                }
            }
            if (!foundBlock) player?.sendMessage(
                TranslatableComponent("item.udemymod.dowsing_rod.no_valuables"),
                player.uuid
            )
        }

        pContext.itemInHand.hurtAndBreak(1, pContext.player) {
            it?.broadcastBreakEvent(it.usedItemHand)
        }

        return super.useOn(pContext)
    }

    override fun appendHoverText(
        pStack: ItemStack,
        pLevel: Level?,
        pTooltipComponents: MutableList<Component>,
        pIsAdvanced: TooltipFlag
    ) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(TranslatableComponent("tooltip.udemymod.dowsing_rod.shift"))
        } else {
            pTooltipComponents.add(TranslatableComponent("tooltip.udemymod.dowsing_rod"))
        }
    }

    private fun outputValuableCoordinates(blockPos: BlockPos, player: Player?, blockBelow: Block) {
        player?.sendMessage(
            TextComponent("Found ${blockBelow.asItem().registryName} at (${blockPos.x}, ${blockPos.y}, ${blockPos.z}"),
            player.uuid
        )
    }

    private fun isValuableBlock(block: Block) =
        ForgeRegistries.BLOCKS.tags()!!.getTag(TagInit.Blocks.DOWSING_ROD_VALUABLES).contains(block)
}