package me.segalu.udemymod

import me.segalu.udemymod.block.ModWoodTypes
import me.segalu.udemymod.config.UdemyModClientConfigs
import me.segalu.udemymod.config.UdemyModCommonConfigs
import me.segalu.udemymod.init.*
import me.segalu.udemymod.screen.CobaltBlasterScreen
import net.minecraft.client.gui.screens.MenuScreens
import net.minecraft.client.renderer.ItemBlockRenderTypes
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.Sheets
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import net.minecraft.client.renderer.blockentity.SignRenderer
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.ComposterBlock
import net.minecraft.world.level.block.FlowerPotBlock
import net.minecraft.world.level.block.state.properties.WoodType
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.config.ModConfig
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
        EnchantmentsInit.ENCHANTMENTS.register(MOD_BUS)
        PaintingInit.PAINTING_MOTIVES.register(MOD_BUS)
        FluidInit.FLUIDS.register(MOD_BUS)
        BlockEntitiesInit.BLOCK_ENTITIES.register(MOD_BUS)
        MenuInit.MENUS.register(MOD_BUS)
        RecipeInit.SERIALIZERS.register(MOD_BUS)

        MOD_BUS.addListener(UdemyMod::onClientSetup)
        MOD_BUS.addListener(UdemyMod::onServerSetup)

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, UdemyModClientConfigs.SPEC, "$ID-client.toml")
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, UdemyModCommonConfigs.SPEC, "$ID-common.toml")
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
        ItemBlockRenderTypes.setRenderLayer(BlockInit.CHERRY_BLOSSOM_SAPLING.get(), RenderType.cutout())
        ItemBlockRenderTypes.setRenderLayer(BlockInit.CHERRY_BLOSSOM_LEAVES.get(), RenderType.cutout())
        ItemBlockRenderTypes.setRenderLayer(FluidInit.HONEY_FLUID, RenderType.translucent())
        ItemBlockRenderTypes.setRenderLayer(FluidInit.HONEY_FLOWING, RenderType.translucent())
        ItemBlockRenderTypes.setRenderLayer(FluidInit.HONEY_BLOCK, RenderType.translucent())

        ItemPropertiesInit.addCustomItemProperties()

        MenuScreens.register(MenuInit.COBALT_BLASTER_MENU.get(), ::CobaltBlasterScreen)

        WoodType.register(ModWoodTypes.CHERRY_BLOSSOM)
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

            BlockEntityRenderers.register(BlockEntitiesInit.SIGN_BLOCK_ENTITIES, ::SignRenderer)
            Sheets.addWoodType(ModWoodTypes.CHERRY_BLOSSOM)
        }
    }
}