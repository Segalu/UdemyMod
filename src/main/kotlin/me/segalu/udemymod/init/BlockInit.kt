package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.UdemyMod.UDEMYMOD_TAB
import me.segalu.udemymod.block.SpeedyBlock
import me.segalu.udemymod.item.ShiftTooltipItem
import me.segalu.udemymod.item.TooltipItem
import net.minecraft.world.item.*
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.Material
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import thedarkcolour.kotlinforforge.forge.registerObject

object BlockInit {
    val BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, UdemyMod.ID)

    // the returned ObjectHolderDelegate can be used as a property delegate
    // this is automatically registered by the deferred registry at the correct times
    val COBALT_BLOCK = registerItemBlock("cobalt_block") {
        Block(BlockBehaviour.Properties.of(Material.METAL).strength(5F).requiresCorrectToolForDrops())
    }

    val COBALT_ORE = registerItemBlock("cobalt_ore") {
        Block(BlockBehaviour.Properties.of(Material.METAL).strength(4F).requiresCorrectToolForDrops())
    }

    val RAW_COBALT_BLOCK = registerItemBlock("raw_cobalt_block") {
        Block(BlockBehaviour.Properties.of(Material.METAL).strength(5F).requiresCorrectToolForDrops())
    }

    val DEEPSLATE_COBALT_ORE = registerItemBlock("deepslate_cobalt_ore") {
        Block(BlockBehaviour.Properties.of(Material.METAL).strength(4F).requiresCorrectToolForDrops())
    }

    val SPEEDY_BLOCK = registerItemBlock(
        "speedy_block",
        "tooltip.udemymod.speedy_block",
        "tooltip.udemymod.speedy_block.shift"
    ) {
        SpeedyBlock(BlockBehaviour.Properties.of(Material.METAL).strength(4F).requiresCorrectToolForDrops())
    }

    private fun registerItemBlock(
        name: String,
        block: () -> Block
    ) = BLOCKS.register(name, block).also {
        ItemInit.ITEMS.registerObject(name) {
            BlockItem(
                it.get(),
                Item.Properties().tab(UDEMYMOD_TAB)
            )
        }
    }

    private fun registerItemBlock(
        name: String,
        tooltip: String,
        block: () -> Block
    ) = BLOCKS.register(name, block).also {
        ItemInit.ITEMS.registerObject(name) {
            TooltipItem(
                it.get(),
                Item.Properties().tab(UDEMYMOD_TAB),
                tooltip
            )
        }
    }

    private fun registerItemBlock(
        name: String,
        tooltip: String,
        shiftTooltip: String,
        block: () -> Block
    ) = BLOCKS.register(name, block).also {
        ItemInit.ITEMS.registerObject(name) {
            ShiftTooltipItem(
                it.get(),
                Item.Properties().tab(UDEMYMOD_TAB),
                tooltip,
                shiftTooltip
            )
        }
    }
}