package me.segalu.udemymod.datagen.client

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.block.CobaltLampBlock
import me.segalu.udemymod.block.ImpostorBlock
import me.segalu.udemymod.init.BlockInit
import me.segalu.udemymod.init.ItemInit
import net.minecraft.data.DataGenerator
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.block.Blocks
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.common.data.ExistingFileHelper

class ModBlockStateProvider(generator: DataGenerator?, helper: ExistingFileHelper?) :
    BlockStateProvider(generator, UdemyMod.ID, helper) {
    private val cobaltResource = blockTexture(BlockInit.COBALT_BLOCK.get())
    override fun registerStatesAndModels() {
        simpleBlock(BlockInit.COBALT_BLOCK.get())
        simpleBlock(BlockInit.COBALT_ORE.get())
        simpleBlock(BlockInit.RAW_COBALT_BLOCK.get())
        simpleBlock(BlockInit.DEEPSLATE_COBALT_ORE.get())
        simpleBlock(BlockInit.SPEEDY_BLOCK.get())
        stairsBlock(BlockInit.COBALT_STAIRS.get(), cobaltResource)
        slabBlock(BlockInit.COBALT_SLAB.get(), cobaltResource, cobaltResource)
        models().buttonInventory("${BlockInit.COBALT_BUTTON.get().registryName}_inventory", cobaltResource)
        buttonBlock(BlockInit.COBALT_BUTTON.get(), cobaltResource)
        pressurePlateBlock(BlockInit.COBALT_PRESSURE_PLATE.get(), cobaltResource)
        models().fenceInventory("${BlockInit.COBALT_FENCE.get().registryName}_inventory", cobaltResource)
        fenceBlock(BlockInit.COBALT_FENCE.get(), cobaltResource)
        fenceGateBlock(BlockInit.COBALT_FENCE_GATE.get(), cobaltResource)
        models().wallInventory("${BlockInit.COBALT_WALL.get().registryName}_inventory", cobaltResource)
        wallBlock(BlockInit.COBALT_WALL.get(), cobaltResource)
        doorBlock(
            BlockInit.CHERRY_BLOSSOM_DOOR.get(),
            ResourceLocation(UdemyMod.ID, "block/${BlockInit.CHERRY_BLOSSOM_DOOR.get().registryName!!.path}_bottom"),
            ResourceLocation(UdemyMod.ID, "block/${BlockInit.CHERRY_BLOSSOM_DOOR.get().registryName!!.path}_top")
        )
        trapdoorBlock(
            BlockInit.CHERRY_BLOSSOM_TRAPDOOR.get(),
            ResourceLocation(
                UdemyMod.ID,
                "block/${BlockInit.CHERRY_BLOSSOM_TRAPDOOR.get().asItem().registryName!!.path}"
            ),
            true
        )
        getVariantBuilder(BlockInit.COBALT_LAMP.get())
            .partialState()
            .with(CobaltLampBlock.CLICKED, false)
            .modelForState()
            .modelFile(
                models().cubeAll(
                    "${BlockInit.COBALT_LAMP.get().registryName}_off", ResourceLocation(
                        UdemyMod.ID,
                        "block/${BlockInit.COBALT_LAMP.get().registryName!!.path}_off"
                    )
                )
            )
            .addModel()
            .partialState()
            .with(CobaltLampBlock.CLICKED, true)
            .modelForState()
            .modelFile(
                models().cubeAll(
                    "${BlockInit.COBALT_LAMP.get().registryName}_on", ResourceLocation(
                        UdemyMod.ID,
                        "block/${BlockInit.COBALT_LAMP.get().registryName!!.path}_on"
                    )
                )
            )
            .addModel()
        getVariantBuilder(BlockInit.IMPOSTOR_BLOCK.get())
            .partialState()
            .with(ImpostorBlock.SKIN, 1)
            .modelForState()
            .modelFile(
                models().getExistingFile(
                    ResourceLocation(
                        "minecraft",
                        "block/${Blocks.REDSTONE_ORE.registryName!!.path}"
                    )
                )
            )
            .addModel()
            .partialState()
            .with(ImpostorBlock.SKIN, 2)
            .modelForState()
            .modelFile(
                models().getExistingFile(
                    ResourceLocation(
                        "minecraft",
                        "block/${Blocks.LAPIS_ORE.registryName!!.path}"
                    )
                )
            )
            .addModel()
            .partialState()
            .with(ImpostorBlock.SKIN, 3)
            .modelForState()
            .modelFile(
                models().getExistingFile(
                    ResourceLocation(
                        "minecraft",
                        "block/${Blocks.DIAMOND_ORE.registryName!!.path}"
                    )
                )
            )
            .addModel()
            .partialState()
            .with(ImpostorBlock.SKIN, 4)
            .modelForState()
            .modelFile(
                models().getExistingFile(
                    ResourceLocation(
                        UdemyMod.ID,
                        "block/${BlockInit.COBALT_ORE.get().registryName!!.path}"
                    )
                )
            )
            .addModel()
        getVariantBuilder(BlockInit.TURNIP_CROP.get())
            .partialState()
            .with(BlockInit.TURNIP_CROP.get().ageProperty, 0)
            .modelForState()
            .modelFile(
                models().crop(
                    "${ItemInit.TURNIP.registryName}_stage0", ResourceLocation(
                        UdemyMod.ID,
                        "block/${ItemInit.TURNIP.registryName!!.path}_stage0"
                    )
                )
            )
            .addModel()
            .partialState()
            .with(BlockInit.TURNIP_CROP.get().ageProperty, 1)
            .modelForState()
            .modelFile(
                models().crop(
                    "${ItemInit.TURNIP.registryName}_stage1", ResourceLocation(
                        UdemyMod.ID,
                        "block/${ItemInit.TURNIP.registryName!!.path}_stage1"
                    )
                )
            )
            .addModel()
            .partialState()
            .with(BlockInit.TURNIP_CROP.get().ageProperty, 2)
            .modelForState()
            .modelFile(
                models().crop(
                    "${ItemInit.TURNIP.registryName}_stage2", ResourceLocation(
                        UdemyMod.ID,
                        "block/${ItemInit.TURNIP.registryName!!.path}_stage2"
                    )
                )
            )
            .addModel()
            .partialState()
            .with(BlockInit.TURNIP_CROP.get().ageProperty, 3)
            .modelForState()
            .modelFile(
                models().crop(
                    "${ItemInit.TURNIP.registryName}_stage3", ResourceLocation(
                        UdemyMod.ID,
                        "block/${ItemInit.TURNIP.registryName!!.path}_stage3"
                    )
                )
            )
            .addModel()
        simpleBlock(
            BlockInit.PINK_ROSE.get(),
            models().cross(BlockInit.PINK_ROSE.get().registryName!!.path, blockTexture(BlockInit.PINK_ROSE.get()))
        )
        simpleBlock(
            BlockInit.POTTED_PINK_ROSE.get(),
            models().withExistingParent(BlockInit.POTTED_PINK_ROSE.get().registryName!!.path, "flower_pot_cross")
                .texture(
                    "plant",
                    ResourceLocation(UdemyMod.ID, "block/${BlockInit.PINK_ROSE.get().registryName!!.path}")
                )
        )
        horizontalFaceBlock(
            BlockInit.COBALT_BLASTER.get(),
            models().getExistingFile(ResourceLocation(UdemyMod.ID, BlockInit.COBALT_BLASTER.get().registryName!!.path))
        )
        logBlock(BlockInit.CHERRY_BLOSSOM_LOG.get())
        axisBlock(
            BlockInit.CHERRY_BLOSSOM_WOOD.get(),
            blockTexture(BlockInit.CHERRY_BLOSSOM_LOG.get()),
            blockTexture(BlockInit.CHERRY_BLOSSOM_LOG.get())
        )
        logBlock(BlockInit.STRIPPED_CHERRY_BLOSSOM_LOG.get())
        axisBlock(
            BlockInit.STRIPPED_CHERRY_BLOSSOM_WOOD.get(),
            blockTexture(BlockInit.STRIPPED_CHERRY_BLOSSOM_LOG.get()),
            blockTexture(BlockInit.STRIPPED_CHERRY_BLOSSOM_LOG.get())
        )
        simpleBlock(BlockInit.CHERRY_BLOSSOM_PLANKS.get())
    }

}