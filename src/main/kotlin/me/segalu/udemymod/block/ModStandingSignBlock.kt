package me.segalu.udemymod.block

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.StandingSignBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.WoodType

class ModStandingSignBlock(pProperties: Properties, pType: WoodType) : StandingSignBlock(pProperties, pType) {

    override fun newBlockEntity(pPos: BlockPos, pState: BlockState): BlockEntity? {
        return ModSignBlockEntity(pPos, pState)
    }
}