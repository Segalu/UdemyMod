package me.segalu.udemymod.block

import me.segalu.udemymod.init.ItemInit
import net.minecraft.world.level.ItemLike
import net.minecraft.world.level.block.BeetrootBlock

class TurnipCropBlock(pProperties: Properties) : BeetrootBlock(pProperties) {

    override fun getBaseSeedId(): ItemLike {
        return ItemInit.TURNIP_SEEDS
    }
}