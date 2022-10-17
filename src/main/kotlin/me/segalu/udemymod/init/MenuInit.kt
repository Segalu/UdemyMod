package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.screen.CobaltBlasterMenu
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.MenuType
import net.minecraftforge.common.extensions.IForgeMenuType
import net.minecraftforge.network.IContainerFactory
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject

object MenuInit {
    val MENUS = DeferredRegister.create(ForgeRegistries.CONTAINERS, UdemyMod.ID)

    val COBALT_BLASTER_MENU = registerMenuType("cobalt_blaster_menu", ::CobaltBlasterMenu)

    private fun <T: AbstractContainerMenu> registerMenuType(name: String, factory: IContainerFactory<T>): RegistryObject<MenuType<T>> {
        return MENUS.register(name) { IForgeMenuType.create(factory) }
    }
}