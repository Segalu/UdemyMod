package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.UdemyMod.UDEMYMOD_TAB
import me.segalu.udemymod.block.SpeedyBlock
import net.minecraft.client.gui.screens.Screen
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.TranslatableComponent
import net.minecraft.world.item.*
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.Material
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject


object BlockInit {
    val BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, UdemyMod.ID)

    // the returned ObjectHolderDelegate can be used as a property delegate
    // this is automatically registered by the deferred registry at the correct times
    val COBALT_BLOCK by BLOCKS.registerObject("cobalt_block") {
        Block(BlockBehaviour.Properties.of(Material.METAL).strength(5F).requiresCorrectToolForDrops()).also {
            ItemInit.ITEMS.registerObject("cobalt_block") {
                BlockItem(
                    it,
                    Item.Properties().tab(UDEMYMOD_TAB)
                )
            }
        }
    }

    val COBALT_ORE by BLOCKS.registerObject("cobalt_ore") {
        Block(BlockBehaviour.Properties.of(Material.METAL).strength(4F).requiresCorrectToolForDrops()).also {
            ItemInit.ITEMS.registerObject("cobalt_ore") {
                BlockItem(
                    it,
                    Item.Properties().tab(UDEMYMOD_TAB)
                )
            }
        }
    }

    val RAW_COBALT_BLOCK by BLOCKS.registerObject("raw_cobalt_block") {
        Block(BlockBehaviour.Properties.of(Material.METAL).strength(5F).requiresCorrectToolForDrops()).also {
            ItemInit.ITEMS.registerObject("raw_cobalt_block") {
                BlockItem(
                    it,
                    Item.Properties().tab(UDEMYMOD_TAB)
                )
            }
        }
    }

    val DEEPSLATE_COBALT_ORE by BLOCKS.registerObject("deepslate_cobalt_ore") {
        Block(BlockBehaviour.Properties.of(Material.METAL).strength(4F).requiresCorrectToolForDrops()).also {
            ItemInit.ITEMS.registerObject("deepslate_cobalt_ore") {
                BlockItem(
                    it,
                    Item.Properties().tab(UDEMYMOD_TAB)
                )
            }
        }
    }

    val SPEEDY_BLOCK by BLOCKS.registerObject("speedy_block") {
        SpeedyBlock(BlockBehaviour.Properties.of(Material.METAL).strength(4F).requiresCorrectToolForDrops()).also {
            ItemInit.ITEMS.registerObject("speedy_block") {
                object : BlockItem(
                    it,
                    Item.Properties().tab(UDEMYMOD_TAB)
                ) {
                    override fun appendHoverText(
                        pStack: ItemStack,
                        pLevel: Level?,
                        pTooltip: MutableList<Component>,
                        pFlag: TooltipFlag
                    ) {
                        if (Screen.hasShiftDown()) {
                            pTooltip.add(TranslatableComponent("tooltip.udemymod.speedy_block.shift"))
                        } else {
                            pTooltip.add((TranslatableComponent("tooltip.udemymod.speedy_block")))
                        }
                    }
                }
            }
        }
    }
}