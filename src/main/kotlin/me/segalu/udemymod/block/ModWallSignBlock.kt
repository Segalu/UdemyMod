package me.segalu.udemymod.block

import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.WallSignBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.WoodType

class ModWallSignBlock(pProperties: Properties, pType: WoodType) : WallSignBlock(pProperties, pType ) {

    override fun newBlockEntity(pPos: BlockPos, pState: BlockState): BlockEntity? {
        return ModSignBlockEntity(pPos, pState)
    }
}