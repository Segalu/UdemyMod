package me.segalu.udemymod.item

import me.segalu.udemymod.init.TagInit
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.DiggerItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Tier
import net.minecraft.world.item.Vanishable
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.block.CampfireBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.ToolAction
import net.minecraftforge.common.ToolActions.*
import java.util.*

class PaxelItem(pTier: Tier?, pAttackDamageModifier: Float, pAttackSpeedModifier: Float, pProperties: Properties?) :
    DiggerItem(pAttackDamageModifier, pAttackSpeedModifier, pTier, TagInit.Blocks.PAXEL_MINEABLE, pProperties),
    Vanishable {



    override fun useOn(pContext: UseOnContext): InteractionResult? {
        val level = pContext.level
        val blockpos = pContext.clickedPos
        val blockstate = level.getBlockState(blockpos)
        val player = pContext.player
        val optional = Optional.ofNullable(blockstate.getToolModifiedState(pContext, AXE_STRIP, false))
        val optional1 = if (optional.isPresent) Optional.empty() else Optional.ofNullable(
            blockstate.getToolModifiedState(
                pContext,
                AXE_SCRAPE,
                false
            )
        )
        val optional2 = if (optional.isPresent || optional1.isPresent) Optional.empty() else Optional.ofNullable(
            blockstate.getToolModifiedState(
                pContext,
                AXE_WAX_OFF,
                false
            )
        )
        val itemstack = pContext.itemInHand
        var optional3 = Optional.empty<BlockState>()

        return if (pContext.clickedFace == Direction.DOWN) {
            InteractionResult.PASS
        } else {
            val blockstate1 = blockstate.getToolModifiedState(pContext, SHOVEL_FLATTEN, false)
            var blockstate2: BlockState? = null
            if (blockstate1 != null && level.isEmptyBlock(blockpos.above())) {
                level.playSound(player, blockpos, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0f, 1.0f)
                blockstate2 = blockstate1
            } else if (blockstate.block is CampfireBlock && blockstate.getValue(CampfireBlock.LIT)) {
                if (!level.isClientSide()) {
                    level.levelEvent(null as Player?, 1009, blockpos, 0)
                }
                CampfireBlock.dowse(pContext.player, level, blockpos, blockstate)
                blockstate2 = blockstate.setValue(CampfireBlock.LIT, java.lang.Boolean.valueOf(false))
            }
            if (blockstate2 != null) {
                if (!level.isClientSide) {
                    level.setBlock(blockpos, blockstate2, 11)
                    if (player != null) {
                        pContext.itemInHand.hurtAndBreak(
                            1, player
                        ) { p_43122_: Player -> p_43122_.broadcastBreakEvent(pContext.hand) }
                    }
                }
                InteractionResult.sidedSuccess(level.isClientSide)
            } else {
                if (optional.isPresent) {
                    level.playSound(player, blockpos, SoundEvents.AXE_STRIP, SoundSource.BLOCKS, 1.0f, 1.0f)
                    optional3 = optional
                } else if (optional1.isPresent) {
                    level.playSound(player, blockpos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0f, 1.0f)
                    level.levelEvent(player, 3005, blockpos, 0)
                    optional3 = optional1
                } else if (optional2.isPresent) {
                    level.playSound(player, blockpos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0f, 1.0f)
                    level.levelEvent(player, 3004, blockpos, 0)
                    optional3 = optional2
                }

                return if (optional3.isPresent) {
                    if (player is ServerPlayer) {
                        CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(player as ServerPlayer?, blockpos, itemstack)
                    }
                    level.setBlock(blockpos, optional3.get(), 11)
                    if (player != null) {
                        itemstack.hurtAndBreak(1, player) { p_150686_: Player ->
                            p_150686_.broadcastBreakEvent(
                                pContext.hand
                            )
                        }
                    }
                    InteractionResult.sidedSuccess(level.isClientSide)
                } else {
                    InteractionResult.PASS
                }
            }
        }

    }

    private val TOOL_ACTIONS = (DEFAULT_AXE_ACTIONS + DEFAULT_PICKAXE_ACTIONS + DEFAULT_SHOVEL_ACTIONS)
    override fun canPerformAction(stack: ItemStack?, toolAction: ToolAction?): Boolean {
        return TOOL_ACTIONS.contains(toolAction)
    }
}