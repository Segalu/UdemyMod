package me.segalu.udemymod.config

import net.minecraftforge.common.ForgeConfigSpec

object UdemyModCommonConfigs {
    val BUILDER = ForgeConfigSpec.Builder()
    lateinit var SPEC: ForgeConfigSpec

    lateinit var COBALT_ORE_VEINS_PER_CHUNK: ForgeConfigSpec.ConfigValue<Int>
    lateinit var COBALT_ORE_VEIN_SIZE: ForgeConfigSpec.ConfigValue<Int>

    init {
        BUILDER.push("Configs for UdemyMod")

        COBALT_ORE_VEINS_PER_CHUNK = BUILDER.comment("How many Cobalt Ore Veins spawn per chunk!")
            .defineInRange("Veins per Chunk", 7, 1, 10)
        COBALT_ORE_VEIN_SIZE = BUILDER.comment("How many Cobalt Ore BLocks spawn in one Vein!")
            .define("Vein Size", 9)

        BUILDER.pop()
        SPEC = BUILDER.build()
    }
}