package me.segalu.udemymod.datagen.client

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.BlockInit
import me.segalu.udemymod.init.ItemInit
import net.minecraft.data.DataGenerator
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.Blocks
import net.minecraftforge.client.model.generators.BlockStateProvider
import net.minecraftforge.client.model.generators.ItemModelBuilder.OverrideBuilder
import net.minecraftforge.client.model.generators.ItemModelProvider
import net.minecraftforge.common.data.ExistingFileHelper

class ModItemModelProvider(generator: DataGenerator?, helper: ExistingFileHelper?) :
    ItemModelProvider(generator, UdemyMod.ID, helper) {

    private fun simpleBlockItem(item: Item) {
        getBuilder(item.registryName.toString())
            .parent(getExistingFile(modLoc("block/${item.registryName!!.path}")))
    }

    private fun oneLayerItem(item: Item, texture: ResourceLocation) {
        val itemTexture = ResourceLocation(texture.namespace, "item/${texture.path}")
        if (existingFileHelper.exists(itemTexture, PackType.CLIENT_RESOURCES, ".png", "textures")) {
            getBuilder(item.registryName!!.path).parent(getExistingFile(mcLoc("item/generated")))
                .texture("layer0", itemTexture)
        } else {
            println("Texture for ${item.registryName} not present at $itemTexture")
        }
    }

    private fun oneLayerItem(item: Item) {
        oneLayerItem(item, item.registryName!!)
    }

    private fun handHeldItem(item: Item, texture: ResourceLocation) {
        val itemTexture = ResourceLocation(texture.namespace, "item/${texture.path}")
        if (existingFileHelper.exists(itemTexture, PackType.CLIENT_RESOURCES, ".png", "textures")) {
            getBuilder(item.registryName!!.path).parent(getExistingFile(mcLoc("item/handheld")))
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
            "block/${BlockInit.COBALT_BLOCK.get().registryName!!.path}"
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
        getBuilder("${ItemInit.DATA_TABLET.registryName!!.path}_on").parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", ResourceLocation(
            UdemyMod.ID,
            "item/${ItemInit.DATA_TABLET.registryName!!.path}_on"
        ))
        getBuilder(ItemInit.DATA_TABLET.registryName!!.path).parent(getExistingFile(mcLoc("item/generated")))
            .texture("layer0", ResourceLocation(
                UdemyMod.ID,
                "item/${ItemInit.DATA_TABLET.registryName!!.path}_off"
            )).override().predicate(ResourceLocation(UdemyMod.ID, "on"), 1F).model(getExistingFile(modLoc("item/${ItemInit.DATA_TABLET.registryName!!.path}_on"))).end()

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
            ResourceLocation(UdemyMod.ID, "block/${BlockInit.CHERRY_BLOSSOM_TRAPDOOR.get().registryName!!.path}")
        )
        withExistingParent(
            BlockInit.COBALT_LAMP.get().asItem().registryName.toString(),
            ResourceLocation(UdemyMod.ID, "block/${BlockInit.COBALT_LAMP.get().registryName!!.path}_off")
        )
        withExistingParent(
            BlockInit.IMPOSTOR_BLOCK.get().asItem().registryName.toString(),
            ResourceLocation("minecraft", "block/${Blocks.DIAMOND_BLOCK.registryName!!.path}")
        )

        //Hand held
        handHeldItem(ItemInit.COBALT_SWORD)
        handHeldItem(ItemInit.COBALT_PICKAXE)
        handHeldItem(ItemInit.COBALT_AXE)
        handHeldItem(ItemInit.COBALT_HOE)
        handHeldItem(ItemInit.COBALT_SHOVEL)
        handHeldItem(ItemInit.COBALT_PAXEL)

    }
}