package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import net.minecraft.resources.ResourceLocation
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.Material
import net.minecraftforge.fluids.FluidAttributes
import net.minecraftforge.fluids.ForgeFlowingFluid
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.registerObject

object FluidInit {
    val FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, UdemyMod.ID)

    val WATER_STILL_RL = ResourceLocation("block/water_still")
    val WATER_FLOWING_RL = ResourceLocation("block/water_flow")
    val WATER_OVERLAY_RL = ResourceLocation("block/water_overlay")

    val HONEY_FLUID by FLUIDS.registerObject("honey_fluid") {
        ForgeFlowingFluid.Source(HONEY_PROPERTIES)
    }

    val HONEY_FLOWING by FLUIDS.registerObject("honey_flowing") {
        ForgeFlowingFluid.Flowing(HONEY_PROPERTIES)
    }

    val HONEY_PROPERTIES: ForgeFlowingFluid.Properties = ForgeFlowingFluid.Properties(
        { HONEY_FLUID }, { HONEY_FLOWING }, FluidAttributes.builder(WATER_STILL_RL, WATER_FLOWING_RL)
            .density(15).luminosity(2).viscosity(5).sound(SoundEvents.HONEY_DRINK).overlay(WATER_OVERLAY_RL)
            .color(0xbffcba03.toInt())
    ).slopeFindDistance(2).levelDecreasePerBlock(2)
        .block { HONEY_BLOCK }.bucket { ItemInit.HONEY_BUCKET }

    val HONEY_BLOCK by BlockInit.BLOCKS.registerObject("honey") {
        LiquidBlock(
            { HONEY_FLUID },
            BlockBehaviour.Properties.of(Material.WATER).noCollission().strength(100F).noDrops()
        )
    }
}