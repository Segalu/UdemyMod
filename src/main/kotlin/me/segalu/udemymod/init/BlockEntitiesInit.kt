package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.block.CobaltBlasterBlockEntity
import me.segalu.udemymod.block.ModSignBlockEntity
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object BlockEntitiesInit {
    val BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, UdemyMod.ID)

    val COBALT_BLASTER by BLOCK_ENTITIES.registerObject("cobalt_blaster") {
        BlockEntityType.Builder.of(::CobaltBlasterBlockEntity, BlockInit.COBALT_BLASTER.get()).build(null)
    }

    val SIGN_BLOCK_ENTITIES by BLOCK_ENTITIES.registerObject("sign_block_entities") {
        BlockEntityType.Builder.of(
            ::ModSignBlockEntity,
            BlockInit.CHERRY_BLOSSOM_SIGN.get(),
            BlockInit.CHERRY_BLOSSOM_WALL_SIGN.get()
        ).build(null)
    }

}