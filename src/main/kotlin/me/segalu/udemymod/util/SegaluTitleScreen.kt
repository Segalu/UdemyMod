package me.segalu.udemymod.util

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.PoseStack
import me.segalu.udemymod.UdemyMod
import net.minecraft.client.gui.screens.TitleScreen
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.client.ForgeHooksClient

object SegaluTitleScreen : TitleScreen() {
    private val SPLASH = ResourceLocation(UdemyMod.ID, "textures/gui/background/segalumenu.jpg")
    private val MINECRAFT_LOGO = ResourceLocation("textures/gui/title/minecraft.png")

    override fun render(pPoseStack: PoseStack, pMouseX: Int, pMouseY: Int, pPartialTick: Float) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick)

        drawCustomTitleScreen(pPoseStack, width, height)
        drawMinecraftLogo(pPoseStack)

        ForgeHooksClient.renderMainMenu(this, pPoseStack, minecraft!!.font, width, height, -1)
/*        drawString(
            pPoseStack, minecraft!!.font, "Copyright Mojang AB. Do not distribute!",
            width - font.width("Copyright Mojang AB. Do not distribute!") - 2,
            height - 10, 16777215
        )*/

        for (widget in renderables) {
            widget.render(pPoseStack, pMouseX, pMouseY, pPartialTick)
        }
    }

    private fun drawMinecraftLogo(pPoseStack: PoseStack) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader)
        RenderSystem.setShaderTexture(0, MINECRAFT_LOGO)
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F)

        blitOutlineBlack(width / 2 - 137, 30) { int1, int2 ->
            blit(pPoseStack, int1 + 0, int2, 0, 0, 99, 44)
            blit(pPoseStack, int1 + 99, int2, 129, 0, 27, 44)
            blit(pPoseStack, int1 + 99 + 26, int2, 126, 0, 3, 44)
            blit(pPoseStack, int1 + 99 + 26 + 3, int2, 99, 0, 26, 44)
            blit(pPoseStack, int1 + 155, int2, 0, 45, 155, 44)
        }
    }

    private fun drawCustomTitleScreen(pPoseStack: PoseStack, width: Int, height: Int) {
        RenderSystem.enableTexture()
        RenderSystem.setShader(GameRenderer::getPositionTexShader)
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F)
        RenderSystem.setShaderTexture(0, SPLASH)
        blit(pPoseStack, 0, 0, 0F, 0F, this.width, this.height, width, height)
    }
}