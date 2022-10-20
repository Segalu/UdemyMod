package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.UdemyMod.UDEMYMOD_TAB
import me.segalu.udemymod.block.*
import me.segalu.udemymod.item.ShiftTooltipItem
import me.segalu.udemymod.item.TooltipItem
import me.segalu.udemymod.world.feature.tree.CherryBlossomTreeGrower
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.*
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.grower.AbstractTreeGrower
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
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
        CobaltLampBlock(
            BlockBehaviour.Properties.of(Material.METAL).sound(SoundInit.COBALT_LAMP_SOUNDS).strength(2F).lightLevel {
                if (it.getValue(CobaltLampBlock.CLICKED)) 15 else 0
            })
    }

    val IMPOSTOR_BLOCK = registerItemBlock("impostor_block") {
        ImpostorBlock(BlockBehaviour.Properties.of(Material.METAL).strength(2F))
    }

    val TURNIP_CROP = BLOCKS.register("turnip_crop") {
        TurnipCropBlock(BlockBehaviour.Properties.copy(Blocks.BEETROOTS).noCollission().noOcclusion())
    }

    val PINK_ROSE = registerItemBlock("pink_rose") {
        FlowerBlock(MobEffects.BLINDNESS, 4, BlockBehaviour.Properties.copy(Blocks.DANDELION).noOcclusion())
    }

    val POTTED_PINK_ROSE = BLOCKS.register("potted_pink_rose") {
        FlowerPotBlock(
            { Blocks.FLOWER_POT as FlowerPotBlock },
            PINK_ROSE,
            BlockBehaviour.Properties.copy(Blocks.POTTED_DANDELION).noOcclusion()
        )
    }

    val COBALT_BLASTER = registerItemBlock("cobalt_blaster", "tooltip.udemymod.cobalt_blaster") {
        CobaltBlasterBlock(BlockBehaviour.Properties.of(Material.METAL).noOcclusion())
    }

    val CHERRY_BLOSSOM_WOOD = registerItemBlock("cherry_blossom_wood") {
        ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD))
    }

    val CHERRY_BLOSSOM_LOG = registerItemBlock("cherry_blossom_log") {
        ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG))
    }

    val STRIPPED_CHERRY_BLOSSOM_LOG = registerItemBlock("stripped_cherry_blossom_log") {
        ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG))
    }

    val STRIPPED_CHERRY_BLOSSOM_WOOD = registerItemBlock("stripped_cherry_blossom_wood") {
        ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD))
    }

    val CHERRY_BLOSSOM_PLANKS: RegistryObject<Block> = registerItemBlock("cherry_blossom_planks") {
        object : Block(Properties.copy(Blocks.OAK_PLANKS)) {
            override fun isFlammable(
                state: BlockState?,
                level: BlockGetter?,
                pos: BlockPos?,
                direction: Direction?
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState?,
                level: BlockGetter?,
                pos: BlockPos?,
                direction: Direction?
            ): Int {
                return 20
            }

            override fun getFireSpreadSpeed(
                state: BlockState?,
                level: BlockGetter?,
                pos: BlockPos?,
                direction: Direction?
            ): Int {
                return 5
            }
        }
    }

    val CHERRY_BLOSSOM_SIGN = BLOCKS.register("cherry_blossom_sign") {
        ModStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD), ModWoodTypes.CHERRY_BLOSSOM)
    }

    val CHERRY_BLOSSOM_WALL_SIGN = BLOCKS.register("cherry_blossom_wall_sign") {
        ModWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD), ModWoodTypes.CHERRY_BLOSSOM)
    }

    val CHERRY_BLOSSOM_LEAVES: RegistryObject<Block> = registerItemBlock("cherry_blossom_leaves") {
        object : LeavesBlock(Properties.copy(Blocks.OAK_LEAVES)) {
            override fun isFlammable(
                state: BlockState?,
                level: BlockGetter?,
                pos: BlockPos?,
                direction: Direction?
            ): Boolean {
                return true
            }

            override fun getFlammability(
                state: BlockState?,
                level: BlockGetter?,
                pos: BlockPos?,
                direction: Direction?
            ): Int {
                return 60
            }

            override fun getFireSpreadSpeed(
                state: BlockState?,
                level: BlockGetter?,
                pos: BlockPos?,
                direction: Direction?
            ): Int {
                return 30
            }
        }
    }

    val CHERRY_BLOSSOM_SAPLING = registerItemBlock("cherry_blossom_sapling") {
        SaplingBlock(CherryBlossomTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING))
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