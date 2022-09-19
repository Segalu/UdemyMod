package me.segalu.udemymod.block

import net.minecraft.core.BlockPos
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.phys.BlockHitResult

class ImpostorBlock(pProperties: Properties): Block(pProperties) {
    companion object {
        val SKIN = IntegerProperty.create("skin", 1, 4)
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
            pLevel.setBlock(pPos, pState.setValue(SKIN, pState.getValue(SKIN).inc().run{ if (this > 4) 1 else this }), 3)
        }
        return InteractionResult.SUCCESS
    }

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
        pBuilder.add(SKIN)
    }

    init {
        registerDefaultState(defaultBlockState().setValue(SKIN, 1))
    }


}