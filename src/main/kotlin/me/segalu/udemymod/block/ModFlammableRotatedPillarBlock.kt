package me.segalu.udemymod.block

import me.segalu.udemymod.init.BlockInit
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.ToolAction
import net.minecraftforge.common.ToolActions

class ModFlammableRotatedPillarBlock(pProperties: Properties) : RotatedPillarBlock(pProperties) {

    override fun isFlammable(state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?): Boolean {
        return true
    }

    override fun getFlammability(state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?): Int {
        return 5
    }

    override fun getFireSpreadSpeed(
        state: BlockState?,
        level: BlockGetter?,
        pos: BlockPos?,
        direction: Direction?
    ): Int {
        return 5
    }

    override fun getToolModifiedState(
        state: BlockState?,
        context: UseOnContext?,
        toolAction: ToolAction?,
        simulate: Boolean
    ): BlockState? {
        if (ToolActions.AXE_STRIP.name() == toolAction?.name()) {
            if (state?.`is`(BlockInit.CHERRY_BLOSSOM_LOG.get()) == true) {
                return BlockInit.STRIPPED_CHERRY_BLOSSOM_LOG.get().defaultBlockState()
                    .setValue(AXIS, state.getValue(AXIS))
            }
            if (state?.`is`(BlockInit.CHERRY_BLOSSOM_WOOD.get()) == true) {
                return BlockInit.STRIPPED_CHERRY_BLOSSOM_WOOD.get().defaultBlockState()
                    .setValue(AXIS, state.getValue(AXIS))
            }

        }
        return super.getToolModifiedState(state, context, toolAction, simulate)
    }

}