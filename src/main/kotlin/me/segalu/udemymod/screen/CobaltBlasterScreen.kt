package me.segalu.udemymod.screen

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import me.segalu.udemymod.UdemyMod
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Inventory

class CobaltBlasterScreen(
    pMenu: CobaltBlasterMenu,
    pPlayerInventory: Inventory, pTitle: Component
) : AbstractContainerScreen<CobaltBlasterMenu>(pMenu, pPlayerInventory, pTitle) {
    val TEXTURE = ResourceLocation(UdemyMod.ID, "textures/gui/cobalt_blaster_gui.png")

    override fun renderBg(pPoseStack: PoseStack, pPartialTick: Float, pMouseX: Int, pMouseY: Int) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader)
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F)
        RenderSystem.setShaderTexture(0, TEXTURE)
        val x = (width - imageWidth) / 2
        val y = (height - imageHeight) / 2

        blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight)

        if (menu.isCrafting()) {
            blit(pPoseStack, x + 84, y + 22, 176, 14, menu.getScaledProgress(), 36)
        }

        if (menu.hasFuel()) {
            blit(pPoseStack, x + 18, y + 33 + 14 - menu.getScaledFuelProgress(), 176,
                14 - menu.getScaledFuelProgress(), 14, menu.getScaledFuelProgress())
        }
    }

    override fun render(pPoseStack: PoseStack, pMouseX: Int, pMouseY: Int, pPartialTick: Float) {
        renderBackground(pPoseStack)
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick)
        renderTooltip(pPoseStack, pMouseX, pMouseY)
    }
}