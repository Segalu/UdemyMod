package me.segalu.udemymod

import me.segalu.udemymod.init.*
import net.minecraft.client.renderer.ItemBlockRenderTypes
import net.minecraft.client.renderer.RenderType
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.ComposterBlock
import net.minecraft.world.level.block.FlowerPotBlock
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS

/**
 * Main mod class. Should be an `object` declaration annotated with `@Mod`.
 * The modid should be declared in this object and should match the modId entry
 * in mods.toml.
 *
 * An example for blocks is in the `blocks` package of this mod.
 */
@Mod(UdemyMod.ID)
object UdemyMod {
    const val ID = "udemymod"

    // the logger for our mod
    val LOGGER: Logger = LogManager.getLogger(ID)

    init {
        LOGGER.log(Level.INFO, "Hello world!")

        BlockInit.BLOCKS.register(MOD_BUS)
        ItemInit.ITEMS.register(MOD_BUS)
        SoundInit.SOUND_EVENTS.register(MOD_BUS)
        ModEnchantments.ENCHANTMENTS.register(MOD_BUS)
        PaintingInit.PAINTING_MOTIVES.register(MOD_BUS)
        FluidInit.FLUIDS.register(MOD_BUS)

        MOD_BUS.addListener(UdemyMod::onClientSetup)
        MOD_BUS.addListener(UdemyMod::onServerSetup)
    }

    @OnlyIn(Dist.CLIENT)
    val UDEMYMOD_TAB: CreativeModeTab = object : CreativeModeTab("udemymod") {
        override fun makeIcon(): ItemStack {
            return ItemStack(ItemInit.COBALT_INGOT)
        }
    }

    /**
     * This is used for initializing client specific
     * things such as renderers and keymaps
     * Fired on the mod specific event bus.
     */
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOGGER.log(Level.INFO, "Initializing client...")
        ItemBlockRenderTypes.setRenderLayer(BlockInit.CHERRY_BLOSSOM_DOOR.get(), RenderType.cutout())
        ItemBlockRenderTypes.setRenderLayer(BlockInit.CHERRY_BLOSSOM_TRAPDOOR.get(), RenderType.cutout())
        ItemBlockRenderTypes.setRenderLayer(BlockInit.TURNIP_CROP.get(), RenderType.cutout())
        ItemBlockRenderTypes.setRenderLayer(BlockInit.PINK_ROSE.get(), RenderType.cutout())
        ItemBlockRenderTypes.setRenderLayer(BlockInit.POTTED_PINK_ROSE.get(), RenderType.cutout())
        ItemBlockRenderTypes.setRenderLayer(BlockInit.COBALT_BLASTER.get(), RenderType.cutout())
        ItemBlockRenderTypes.setRenderLayer(FluidInit.HONEY_FLUID, RenderType.translucent())
        ItemBlockRenderTypes.setRenderLayer(FluidInit.HONEY_FLOWING, RenderType.translucent())
        ItemBlockRenderTypes.setRenderLayer(FluidInit.HONEY_BLOCK, RenderType.translucent())

        ItemPropertiesInit.addCustomItemProperties()
    }

    /**
     * Fired on the global Forge bus.
     */
    private fun onServerSetup(event: FMLCommonSetupEvent) {
        LOGGER.log(Level.INFO, "Server starting...")
        event.enqueueWork {
            (Blocks.FLOWER_POT as FlowerPotBlock).addPlant(BlockInit.PINK_ROSE.id, BlockInit.POTTED_PINK_ROSE)
            ComposterBlock.COMPOSTABLES.put(ItemInit.TURNIP_SEEDS, 0.3F)
            ComposterBlock.COMPOSTABLES.put(ItemInit.TURNIP, 0.65F)
        }
    }
}