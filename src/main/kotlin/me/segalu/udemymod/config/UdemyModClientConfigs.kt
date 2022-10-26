package me.segalu.udemymod.config

import net.minecraftforge.common.ForgeConfigSpec

object UdemyModClientConfigs {
    val BUILDER = ForgeConfigSpec.Builder()
    lateinit var SPEC: ForgeConfigSpec

    lateinit var CUSTOM_TITLE_SCREEN: ForgeConfigSpec.ConfigValue<Boolean>

    init {
        BUILDER.push("Configs for UdemyMod")

        CUSTOM_TITLE_SCREEN = BUILDER.comment("Whether the default Title Screen will be replaced!")
            .define("replace", true)

        BUILDER.pop()
        SPEC = BUILDER.build()
    }
}