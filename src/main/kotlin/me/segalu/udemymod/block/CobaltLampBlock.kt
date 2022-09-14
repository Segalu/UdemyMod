package me.segalu.udemymod.block

import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.phys.BlockHitResult

class CobaltLampBlock(pProperties: Properties) : Block(pProperties) {
    init {
        registerDefaultState(defaultBlockState().setValue(CobaltLampBlock.CLICKED, false))
    }

    override fun use(
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pPlayer: Player,
        pHand: InteractionHand,
        pHit: BlockHitResult
    ): InteractionResult {
        if (!pLevel.isClientSide && pHand == InteractionHand.MAIN_HAND) {
            pLevel.setBlock(pPos, pState.setValue(Companion.CLICKED, !pState.getValue(Companion.CLICKED)), 3)
        }
        return InteractionResult.SUCCESS
    }

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
        pBuilder.add(Companion.CLICKED)
    }

    companion object {
        val CLICKED = BooleanProperty.create("clicked")
    }
}