package me.segalu.udemymod.block

import me.segalu.udemymod.init.BlockEntitiesInit
import me.segalu.udemymod.init.ItemInit
import me.segalu.udemymod.screen.CobaltBlasterMenu
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.TextComponent
import net.minecraft.world.Containers
import net.minecraft.world.MenuProvider
import net.minecraft.world.SimpleContainer
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.ItemStackHandler


class CobaltBlasterBlockEntity(pPos: BlockPos, pBlockState: BlockState) :
    BlockEntity(BlockEntitiesInit.COBALT_BLASTER, pPos, pBlockState), MenuProvider {

    companion object {
        fun tick(pLevel: Level?, pPos: BlockPos?, pState: BlockState?, pBlockEntity: CobaltBlasterBlockEntity) {
            if (hasRecipe(pBlockEntity) && hasNotReachedStackLimit(pBlockEntity)) {
                craftItem(pBlockEntity)
            }
        }

        private fun hasRecipe(entity: CobaltBlasterBlockEntity): Boolean {
            val hasItemInSecondSlot = entity.itemHandler.getStackInSlot(0).item == ItemInit.COAL_SLIVER
            val hasItemInFirstSlot = entity.itemHandler.getStackInSlot(1).item == ItemInit.RAW_COBALT

            return hasItemInSecondSlot && hasItemInFirstSlot
        }

        private fun craftItem(entity: CobaltBlasterBlockEntity) {
            entity.itemHandler.extractItem(0, 1, false)
            entity.itemHandler.extractItem(1, 1, false)

            entity.itemHandler.setStackInSlot(
                3,
                ItemStack(ItemInit.COBALT_INGOT, entity.itemHandler.getStackInSlot(3).count + 1)
            )
        }

        private fun hasNotReachedStackLimit(entity: CobaltBlasterBlockEntity): Boolean {
            return entity.itemHandler.getStackInSlot(3).count < entity.itemHandler.getStackInSlot(3).maxStackSize
        }
    }

    private val itemHandler: ItemStackHandler = object : ItemStackHandler(4) {
        override fun onContentsChanged(slot: Int) {
            setChanged()
        }
    }

    private var lazyItemHandler: LazyOptional<IItemHandler> = LazyOptional.empty()

    override fun createMenu(pContainerId: Int, pPlayerInventory: Inventory, pPlayer: Player): AbstractContainerMenu? {
        return CobaltBlasterMenu(pContainerId, pPlayerInventory, this)
    }

    override fun getDisplayName(): Component {
        return TextComponent("Cobalt Blaster")
    }


    override fun <T> getCapability(cap: Capability<T>, side: Direction?): LazyOptional<T> {
        return if (cap === CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            lazyItemHandler.cast()
        } else super.getCapability(cap, side)
    }

    override fun onLoad() {
        super.onLoad()
        lazyItemHandler = LazyOptional.of { itemHandler }
    }

    override fun invalidateCaps() {
        super.invalidateCaps()
        lazyItemHandler.invalidate()
    }

    override fun saveAdditional(pTag: CompoundTag) {
        pTag.put("inventory", itemHandler.serializeNBT())
        super.saveAdditional(pTag)
    }

    override fun load(pTag: CompoundTag) {
        super.load(pTag)
        itemHandler.deserializeNBT(pTag.getCompound("inventory"))
    }

    fun drops() {
        val inventory = SimpleContainer(itemHandler.slots)
        for (slot in 0 until itemHandler.slots) {
            val item = itemHandler.getStackInSlot(slot)
            inventory.setItem(slot, item)
        }

        Containers.dropContents(level, worldPosition, inventory)
    }

}

