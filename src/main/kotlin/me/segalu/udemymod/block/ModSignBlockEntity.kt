package me.segalu.udemymod.block

import me.segalu.udemymod.init.BlockEntitiesInit
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.entity.SignBlockEntity
import net.minecraft.world.level.block.state.BlockState

class ModSignBlockEntity(pPos: BlockPos, pBlockState: BlockState) : SignBlockEntity(pPos, pBlockState) {

    override fun getType(): BlockEntityType<*> {
        return BlockEntitiesInit.SIGN_BLOCK_ENTITIES
    }
}