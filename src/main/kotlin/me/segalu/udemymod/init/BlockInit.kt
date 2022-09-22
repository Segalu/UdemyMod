package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.UdemyMod.UDEMYMOD_TAB
import me.segalu.udemymod.block.CobaltLampBlock
import me.segalu.udemymod.block.ImpostorBlock
import me.segalu.udemymod.block.SpeedyBlock
import me.segalu.udemymod.block.TurnipCropBlock
import me.segalu.udemymod.item.ShiftTooltipItem
import me.segalu.udemymod.item.TooltipItem
import net.minecraft.world.item.*
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.ButtonBlock
import net.minecraft.world.level.block.DoorBlock
import net.minecraft.world.level.block.FenceBlock
import net.minecraft.world.level.block.FenceGateBlock
import net.minecraft.world.level.block.PressurePlateBlock
import net.minecraft.world.level.block.SlabBlock
import net.minecraft.world.level.block.StairBlock
import net.minecraft.world.level.block.StoneButtonBlock
import net.minecraft.world.level.block.TrapDoorBlock
import net.minecraft.world.level.block.WallBlock
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

    val COBALT_STAIRS = registerItemBlock("cobalt_stairs") {
        StairBlock(
            { COBALT_BLOCK.get().defaultBlockState() },
            BlockBehaviour.Properties.of(Material.METAL).strength(2F).requiresCorrectToolForDrops()
        )
    }

    val COBALT_SLAB = registerItemBlock("cobalt_slab") {
        SlabBlock(BlockBehaviour.Properties.of(Material.METAL).strength(4F).requiresCorrectToolForDrops())
    }

    val COBALT_BUTTON = registerItemBlock("cobalt_button") {
        StoneButtonBlock(
            BlockBehaviour.Properties.of(Material.METAL).strength(4F).requiresCorrectToolForDrops().noCollission()
        )
    }

    val COBALT_PRESSURE_PLATE = registerItemBlock("cobalt_pressure_plate") {
        PressurePlateBlock(
            PressurePlateBlock.Sensitivity.EVERYTHING,
            BlockBehaviour.Properties.of(Material.METAL).strength(4F).requiresCorrectToolForDrops().noCollission()
        )
    }

    val COBALT_FENCE = registerItemBlock("cobalt_fence") {
        FenceBlock(BlockBehaviour.Properties.of(Material.METAL).strength(4F).requiresCorrectToolForDrops())
    }

    val COBALT_FENCE_GATE = registerItemBlock("cobalt_fence_gate") {
        FenceGateBlock(BlockBehaviour.Properties.of(Material.METAL).strength(4F).requiresCorrectToolForDrops())
    }

    val COBALT_WALL = registerItemBlock("cobalt_wall") {
        WallBlock(BlockBehaviour.Properties.of(Material.METAL).strength(4F).requiresCorrectToolForDrops())
    }

    val CHERRY_BLOSSOM_DOOR = registerItemBlock("cherry_blossom_door") {
        DoorBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(4F).requiresCorrectToolForDrops().noOcclusion())
    }

    val CHERRY_BLOSSOM_TRAPDOOR = registerItemBlock("cherry_blossom_trapdoor") {
        TrapDoorBlock(
            BlockBehaviour.Properties.of(Material.WOOD).strength(4F).requiresCorrectToolForDrops().noOcclusion()
        )
    }

    val SPEEDY_BLOCK = registerItemBlock(
        "speedy_block",
        "tooltip.udemymod.speedy_block",
        "tooltip.udemymod.speedy_block.shift"
    ) {
        SpeedyBlock(BlockBehaviour.Properties.of(Material.METAL).strength(4F).requiresCorrectToolForDrops())
    }

    val COBALT_LAMP = registerItemBlock("cobalt_lamp") {
        CobaltLampBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2F).lightLevel {
            if (it.getValue(CobaltLampBlock.CLICKED)) 15 else 0
        })
    }

    val IMPOSTOR_BLOCK = registerItemBlock("impostor_block") {
        ImpostorBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2F))
    }

    val TURNIP_CROP = BLOCKS.register("turnip_crop") {
        TurnipCropBlock(BlockBehaviour.Properties.copy(Blocks.BEETROOTS).noCollission().noOcclusion())
    }

    private fun <T : Block> registerItemBlock(
        name: String,
        block: () -> T
    ) = BLOCKS.register(name, block).also {
        ItemInit.ITEMS.registerObject(name) {
            BlockItem(
                it.get(),
                Item.Properties().tab(UDEMYMOD_TAB)
            )
        }
    }

    private fun <T : Block> registerItemBlock(
        name: String,
        tooltip: String,
        block: () -> T
    ) = BLOCKS.register(name, block).also {
        ItemInit.ITEMS.registerObject(name) {
            TooltipItem(
                it.get(),
                Item.Properties().tab(UDEMYMOD_TAB),
                tooltip
            )
        }
    }

    private fun <T : Block> registerItemBlock(
        name: String,
        tooltip: String,
        shiftTooltip: String,
        block: () -> T
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