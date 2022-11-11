package me.segalu.udemymod.init

import me.segalu.udemymod.UdemyMod
import me.segalu.udemymod.command.ReturnHomeCommand
import me.segalu.udemymod.command.SetHomeCommand
import me.segalu.udemymod.config.UdemyModClientConfigs
import me.segalu.udemymod.util.SegaluTitleScreen
import net.minecraft.client.gui.screens.TitleScreen
import net.minecraft.world.entity.npc.VillagerProfession
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.trading.MerchantOffer
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn
import net.minecraftforge.client.event.ScreenOpenEvent
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.event.entity.living.LivingDamageEvent
import net.minecraftforge.event.entity.player.PlayerEvent.Clone
import net.minecraftforge.event.village.VillagerTradesEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.server.command.ConfigCommand

@EventBusSubscriber(modid = UdemyMod.ID)
object EventInit {
    @SubscribeEvent
    fun onCommandsRegister(event: RegisterCommandsEvent) {
        SetHomeCommand(event.dispatcher)
        ReturnHomeCommand(event.dispatcher)
        ConfigCommand.register(event.dispatcher)
    }

    @SubscribeEvent
    fun onPlayerCloneEvent(event: Clone) {
        if (!event.original.level.isClientSide) {
            event.player.persistentData.putIntArray(
                UdemyMod.ID + "homepos",
                event.original.persistentData.getIntArray(UdemyMod.ID + "homepos")
            )
        }
    }

    @SubscribeEvent
    fun setEntityOnFireWhenHit(event: LivingDamageEvent) {
        if (!event.entity.level.isClientSide) {
            if (event.source.directEntity is Player) {
                val player = event.source.directEntity as Player
                if (player.mainHandItem.item == Items.NETHER_BRICK) {
                    player.mainHandItem.shrink(1)
                    event.entityLiving.setSecondsOnFire(2)
                }
            }
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    fun openGui(event: ScreenOpenEvent) {
        if (UdemyModClientConfigs.CUSTOM_TITLE_SCREEN.get() && event.screen is TitleScreen && event.screen !is SegaluTitleScreen) {
            event.screen = SegaluTitleScreen
        }
    }

    @SubscribeEvent
    fun addCustomTrades(event: VillagerTradesEvent) {
        if (event.type == VillagerProfession.FARMER) {
            val trades = event.trades
            val stack = ItemStack(ItemInit.TURNIP, 12)
            val villagerLevel = 1

            trades[villagerLevel].add { trader, rand ->
                MerchantOffer(ItemStack(Items.EMERALD, 2), stack, 10, 50, 0.02F)
            }
        }

        if (event.type == VillagerProfession.TOOLSMITH) {
            val trades = event.trades
            val stack = ItemStack(ItemInit.COBALT_PAXEL, 1)
            val villagerLevel = 3

            trades[villagerLevel].add { trader, rand ->
                MerchantOffer(ItemStack(Items.EMERALD, 12), stack, 4, 120, 0.09F)
            }
        }
    }

}