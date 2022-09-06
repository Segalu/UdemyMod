package me.segalu.udemymod.datagen.client

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.init.BlockInit
import me.segalu.udemymod.init.ItemInit
import net.minecraft.data.DataGenerator
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import net.minecraft.world.item.Item
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
        //Simple items
        oneLayerItem(ItemInit.COBALT_INGOT)
        oneLayerItem(ItemInit.COBALT_NUGGET)
        oneLayerItem(ItemInit.RAW_COBALT)
        oneLayerItem(ItemInit.DOWSING_ROD)
        oneLayerItem(ItemInit.COAL_SLIVER)
        oneLayerItem(ItemInit.TURNIP)

        //Simple blocks
        simpleBlockItem(BlockInit.COBALT_BLOCK.get().asItem())
        simpleBlockItem(BlockInit.COBALT_ORE.get().asItem())
        simpleBlockItem(BlockInit.DEEPSLATE_COBALT_ORE.get().asItem())
        simpleBlockItem(BlockInit.RAW_COBALT_BLOCK.get().asItem())
        simpleBlockItem(BlockInit.SPEEDY_BLOCK.get().asItem())
    }

}