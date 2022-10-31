package me.segalu.udemymod.datagen.client

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.BlockInit
import me.segalu.udemymod.init.ItemInit
import net.minecraft.client.renderer.block.model.ItemTransforms
import net.minecraft.core.Direction
import net.minecraft.data.DataGenerator
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import net.minecraft.world.item.BowItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.FlowerPotBlock
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.client.model.generators.ModelBuilder.Perspective
import net.minecraftforge.common.data.ExistingFileHelper

class ModItemModelProvider(generator: DataGenerator?, helper: ExistingFileHelper?) :
    ItemModelProvider(generator, UdemyMod.ID, helper) {

    private fun simpleBlockItem(item: Item) {
        getBuilder(item.registryName.toString())
            .parent(getExistingFile(modLoc("$BLOCK_FOLDER/${item.registryName!!.path}")))
    }

    private fun oneLayerItem(item: Item, texture: ResourceLocation) {
        if (existingFileHelper.exists(texture, PackType.CLIENT_RESOURCES, ".png", "textures")) {
            getBuilder(item.registryName!!.path).parent(getExistingFile(mcLoc("$ITEM_FOLDER/generated")))
                .texture("layer0", texture)
        } else {
            println("Texture for ${item.registryName} not present at $texture")
        }
    }

    private fun oneLayerItem(item: Item) {
        oneLayerItem(item, ResourceLocation(UdemyMod.ID, "$ITEM_FOLDER/${item.registryName!!.path}"))
    }

    private fun handHeldItem(item: Item, texture: ResourceLocation) {
        val itemTexture = ResourceLocation(texture.namespace, "$ITEM_FOLDER/${texture.path}")
        if (existingFileHelper.exists(itemTexture, PackType.CLIENT_RESOURCES, ".png", "textures")) {
            getBuilder(item.registryName!!.path).parent(getExistingFile(mcLoc("$ITEM_FOLDER/handheld")))
                .texture("layer0", itemTexture)
        } else {
            println("Texture for ${item.registryName} not present at $itemTexture")
        }
    }

    private fun handHeldItem(item: Item) {
        handHeldItem(item, item.registryName!!)
    }

    override fun registerModels() {
        val cobaltResource = ResourceLocation(
            UdemyMod.ID,
            "$BLOCK_FOLDER/${BlockInit.COBALT_BLOCK.get().registryName!!.path}"
        )
        //Simple items
        oneLayerItem(ItemInit.COBALT_INGOT)
        oneLayerItem(ItemInit.COBALT_NUGGET)
        oneLayerItem(ItemInit.RAW_COBALT)
        oneLayerItem(ItemInit.DOWSING_ROD)
        oneLayerItem(ItemInit.COAL_SLIVER)
        oneLayerItem(ItemInit.TURNIP)
        oneLayerItem(BlockInit.CHERRY_BLOSSOM_DOOR.get().asItem())
        oneLayerItem(ItemInit.COBALT_HELMET)
        oneLayerItem(ItemInit.COBALT_CHESTPLATE)
        oneLayerItem(ItemInit.COBALT_LEGGINGS)
        oneLayerItem(ItemInit.COBALT_BOOTS)
        oneLayerItem(ItemInit.COBALT_HORSE_ARMOR)
        getBuilder("${ItemInit.DATA_TABLET.registryName!!.path}_on").parent(getExistingFile(mcLoc("$ITEM_FOLDER/generated")))
            .texture(
                "layer0", ResourceLocation(
                    UdemyMod.ID,
                    "$ITEM_FOLDER/${ItemInit.DATA_TABLET.registryName!!.path}_on"
                )
            )
        getBuilder(ItemInit.DATA_TABLET.registryName!!.path).parent(getExistingFile(mcLoc("$ITEM_FOLDER/generated")))
            .texture(
                "layer0", ResourceLocation(
                    UdemyMod.ID,
                    "$ITEM_FOLDER/${ItemInit.DATA_TABLET.registryName!!.path}_off"
                )
            ).override().predicate(ResourceLocation(UdemyMod.ID, "on"), 1F)
            .model(getExistingFile(modLoc("$ITEM_FOLDER/${ItemInit.DATA_TABLET.registryName!!.path}_on"))).end()
        oneLayerItem(ItemInit.TURNIP_SEEDS)
        oneLayerItem(ItemInit.BAR_BRAWL_RECORD)
        oneLayerItem(ItemInit.CHERRY_BLOSSOM_SIGN_ITEM)
        oneLayerItem(ItemInit.MAGIC_DUST)

        //Simple blocks
        simpleBlockItem(BlockInit.COBALT_BLOCK.get().asItem())
        simpleBlockItem(BlockInit.COBALT_ORE.get().asItem())
        simpleBlockItem(BlockInit.DEEPSLATE_COBALT_ORE.get().asItem())
        simpleBlockItem(BlockInit.RAW_COBALT_BLOCK.get().asItem())
        simpleBlockItem(BlockInit.SPEEDY_BLOCK.get().asItem())
        simpleBlockItem(BlockInit.COBALT_STAIRS.get().asItem())
        simpleBlockItem(BlockInit.COBALT_SLAB.get().asItem())
        simpleBlockItem(BlockInit.COBALT_PRESSURE_PLATE.get().asItem())
        simpleBlockItem(BlockInit.COBALT_FENCE_GATE.get().asItem())
        fenceInventory(BlockInit.COBALT_FENCE.get().asItem().registryName.toString(), cobaltResource)
        wallInventory(BlockInit.COBALT_WALL.get().asItem().registryName.toString(), cobaltResource)
        buttonInventory(BlockInit.COBALT_BUTTON.get().asItem().registryName.toString(), cobaltResource)
        trapdoorBottom(
            BlockInit.CHERRY_BLOSSOM_TRAPDOOR.get().asItem().registryName.toString(),
            ResourceLocation(
                UdemyMod.ID,
                "$BLOCK_FOLDER/${BlockInit.CHERRY_BLOSSOM_TRAPDOOR.get().registryName!!.path}"
            )
        )
        withExistingParent(
            BlockInit.COBALT_LAMP.get().asItem().registryName.toString(),
            ResourceLocation(UdemyMod.ID, "$BLOCK_FOLDER/${BlockInit.COBALT_LAMP.get().registryName!!.path}_off")
        )
        withExistingParent(
            BlockInit.IMPOSTOR_BLOCK.get().asItem().registryName.toString(),
            ResourceLocation("minecraft", "$BLOCK_FOLDER/${Blocks.DIAMOND_BLOCK.registryName!!.path}")
        )
        getBuilder(BlockInit.PINK_ROSE.get().registryName!!.path).parent(getExistingFile(mcLoc("$ITEM_FOLDER/generated")))
            .texture(
                "layer0",
                ResourceLocation(UdemyMod.ID, "$BLOCK_FOLDER/${BlockInit.PINK_ROSE.get().registryName!!.path}")
            )
        simpleBlockItem(BlockInit.COBALT_BLASTER.get().asItem())
        simpleBlockItem(BlockInit.CHERRY_BLOSSOM_LOG.get().asItem())
        simpleBlockItem(BlockInit.CHERRY_BLOSSOM_WOOD.get().asItem())
        simpleBlockItem(BlockInit.STRIPPED_CHERRY_BLOSSOM_LOG.get().asItem())
        simpleBlockItem(BlockInit.STRIPPED_CHERRY_BLOSSOM_WOOD.get().asItem())
        simpleBlockItem(BlockInit.CHERRY_BLOSSOM_PLANKS.get().asItem())
        simpleBlockItem(BlockInit.WINTER_WINDOW.get().asItem())

        //Hand held
        handHeldItem(ItemInit.COBALT_SWORD)
        handHeldItem(ItemInit.COBALT_PICKAXE)
        handHeldItem(ItemInit.COBALT_AXE)
        handHeldItem(ItemInit.COBALT_HOE)
        handHeldItem(ItemInit.COBALT_SHOVEL)
        handHeldItem(ItemInit.COBALT_PAXEL)
        getBuilder(ItemInit.COBALT_BOW.registryName!!.path).parent(getExistingFile(mcLoc("$ITEM_FOLDER/generated")))
            .texture(
                "layer0", ResourceLocation(UdemyMod.ID, "$ITEM_FOLDER/${ItemInit.COBALT_BOW.registryName!!.path}")
            ).transforms()
            .transform(ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND).rotation(-80F, 260F, -40F)
            .translation(-1F, -2F, 2.5F).scale(0.9F, 0.9F, 0.9F).end()
            .transform(ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND).rotation(-80F, -280F, 40F)
            .translation(-1F, -2F, 2.5F).scale(0.9F, 0.9F, 0.9F).end()
            .transform(ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND).rotation(0F, -90F, 25F)
            .translation(1.13F, 3.2F, 1.13F).scale(0.68F, 0.68F, 0.68F).end()
            .transform(ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND).rotation(0F, 90F, -25F)
            .translation(1.13F, 3.2F, 1.13F).scale(0.68F, 0.68F, 0.68F).end()
            .end()
            .override().predicate(ResourceLocation("pulling"), 1F)
            .model(
                getBuilder("${ItemInit.COBALT_BOW.registryName!!.path}_pulling_0").parent(getExistingFile(modLoc("$ITEM_FOLDER/cobalt_bow")))
                    .texture(
                        "layer0", ResourceLocation(
                            UdemyMod.ID,
                            "$ITEM_FOLDER/${ItemInit.COBALT_BOW.registryName!!.path}_pulling_0"
                        )
                    )
            )
            .end().override().predicate(ResourceLocation("pulling"), 1F).predicate(ResourceLocation("pull"), 0.65F)
            .model(
                getBuilder("${ItemInit.COBALT_BOW.registryName!!.path}_pulling_1").parent(getExistingFile(modLoc("$ITEM_FOLDER/cobalt_bow")))
                    .texture(
                        "layer0", ResourceLocation(
                            UdemyMod.ID,
                            "$ITEM_FOLDER/${ItemInit.COBALT_BOW.registryName!!.path}_pulling_1"
                        )
                    )
            ).end().override().predicate(ResourceLocation("pulling"), 1F).predicate(ResourceLocation("pull"), 0.9F)
            .model(
                getBuilder("${ItemInit.COBALT_BOW.registryName!!.path}_pulling_2").parent(getExistingFile(modLoc("$ITEM_FOLDER/cobalt_bow")))
                    .texture(
                        "layer0", ResourceLocation(
                            UdemyMod.ID,
                            "$ITEM_FOLDER/${ItemInit.COBALT_BOW.registryName!!.path}_pulling_2"
                        )
                    )
            ).end()
        oneLayerItem(ItemInit.HONEY_BUCKET)
        simpleBlockItem(BlockInit.CHERRY_BLOSSOM_LEAVES.get().asItem())
        oneLayerItem(
            BlockInit.CHERRY_BLOSSOM_SAPLING.get().asItem(),
            ResourceLocation(UdemyMod.ID, "$BLOCK_FOLDER/${BlockInit.CHERRY_BLOSSOM_SAPLING.get().asItem().registryName!!.path}")
        )

    }
}