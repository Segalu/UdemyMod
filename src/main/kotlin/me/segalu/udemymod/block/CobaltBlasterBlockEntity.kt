package me.segalu.udemymod.block

import me.segalu.udemymod.init.BlockEntitiesInit
import me.segalu.udemymod.init.ItemInit
import me.segalu.udemymod.recipe.CobaltBlasterRecipe
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
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.crafting.RecipeType
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.ForgeHooks
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.ItemStackHandler


class CobaltBlasterBlockEntity(pPos: BlockPos, pBlockState: BlockState) :
    BlockEntity(BlockEntitiesInit.COBALT_BLASTER, pPos, pBlockState), MenuProvider {
    private val data: ContainerData
    private var progress = 0
    private var maxProgress = 72
    private var fuelTime = 0
    private var maxFuelTime = 0

    init {
        data = object : ContainerData {
            override fun get(index: Int): Int {
                return when (index) {
                    0 -> progress
                    1 -> maxProgress
                    2 -> fuelTime
                    3 -> maxFuelTime
                    else -> 0
                }
            }

            override fun set(pIndex: Int, pValue: Int) {
                when (pIndex) {
                    0 -> progress = pValue
                    1 -> maxProgress = pValue
                    2 -> fuelTime = pValue
                    3 -> maxFuelTime = pValue
                }
            }

            override fun getCount() = 4
        }
    }

    companion object {
        fun tick(pLevel: Level?, pPos: BlockPos?, pState: BlockState?, pBlockEntity: CobaltBlasterBlockEntity) {
            if (isConsumingFuel(pBlockEntity)) {
                pBlockEntity.fuelTime--
            }

            if (hasRecipe(pBlockEntity)) {
                if (hasFuelInFuelSlot(pBlockEntity) && !isConsumingFuel(pBlockEntity)) {
                    pBlockEntity.consumeFuel()
                    setChanged(pLevel, pPos, pState)
                }
                if (isConsumingFuel(pBlockEntity)) {
                    pBlockEntity.progress++
                    setChanged(pLevel, pPos, pState)
                    if (pBlockEntity.progress > pBlockEntity.maxProgress) {
                        craftItem(pBlockEntity)
                    }
                }
            } else {
                pBlockEntity.resetProgress()
                setChanged(pLevel, pPos, pState)
            }
        }

        private fun hasFuelInFuelSlot(entity: CobaltBlasterBlockEntity): Boolean {
            return !entity.itemHandler.getStackInSlot(0).isEmpty
        }

        private fun isConsumingFuel(entity: CobaltBlasterBlockEntity) = entity.fuelTime > 0

        private fun hasRecipe(entity: CobaltBlasterBlockEntity): Boolean {
            val level = entity.level
            val inventory = SimpleContainer(entity.itemHandler.slots)
            for (slot in 0 until entity.itemHandler.slots) {
                inventory.setItem(slot, entity.itemHandler.getStackInSlot(slot))
            }

            val match =
                level!!.recipeManager.getRecipeFor(CobaltBlasterRecipe.Companion.Type.INSTANCE, inventory, level)

            return match.isPresent && canInsertAmountIntoOutputSlot(inventory) && canInsertItemIntoOutputSlot(
                inventory,
                match.get().resultItem
            )
        }

        private fun craftItem(entity: CobaltBlasterBlockEntity) {
            val level = entity.level
            val inventory = SimpleContainer(entity.itemHandler.slots)
            for (slot in 0 until entity.itemHandler.slots) {
                inventory.setItem(slot, entity.itemHandler.getStackInSlot(slot))
            }

            val match =
                level!!.recipeManager.getRecipeFor(CobaltBlasterRecipe.Companion.Type.INSTANCE, inventory, level)

            if (match.isPresent) {
                entity.itemHandler.extractItem(1, 1, false)
                entity.itemHandler.extractItem(2, 1, false)

                entity.itemHandler.setStackInSlot(
                    3,
                    ItemStack(
                        match.get().resultItem.item,
                        entity.itemHandler.getStackInSlot(3).count + 1
                    )
                )

                entity.resetProgress()
            }
        }

        private fun hasNotReachedStackLimit(entity: CobaltBlasterBlockEntity): Boolean {
            return entity.itemHandler.getStackInSlot(3).count < entity.itemHandler.getStackInSlot(3).maxStackSize
        }

        private fun canInsertItemIntoOutputSlot(inventory: SimpleContainer, output: ItemStack): Boolean {
            return inventory.getItem(3).item == output.item || inventory.getItem(3).isEmpty
        }

        private fun canInsertAmountIntoOutputSlot(inventory: SimpleContainer): Boolean {
            return inventory.getItem(3).maxStackSize > inventory.getItem(3).count
        }
    }

    private fun resetProgress() {
        progress = 0
    }

    private val itemHandler: ItemStackHandler = object : ItemStackHandler(4) {
        override fun onContentsChanged(slot: Int) {
            setChanged()
        }
    }

    private var lazyItemHandler: LazyOptional<IItemHandler> = LazyOptional.empty()

    override fun createMenu(pContainerId: Int, pPlayerInventory: Inventory, pPlayer: Player): AbstractContainerMenu? {
        return CobaltBlasterMenu(pContainerId, pPlayerInventory, this, data)
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
        pTag.putInt("blaster.progress", progress)
        pTag.putInt("blaster.fuelTime", fuelTime)
        pTag.putInt("blaster.maxFuelTime", maxFuelTime)
        super.saveAdditional(pTag)
    }

    override fun load(pTag: CompoundTag) {
        super.load(pTag)
        itemHandler.deserializeNBT(pTag.getCompound("inventory"))
        progress = pTag.getInt("blaster.progress")
        fuelTime = pTag.getInt("blaster.fuelTime")
        maxFuelTime = pTag.getInt("blaster.maxFuelTime")
    }

    fun drops() {
        val inventory = SimpleContainer(itemHandler.slots)
        for (slot in 0 until itemHandler.slots) {
            val item = itemHandler.getStackInSlot(slot)
            inventory.setItem(slot, item)
        }

        Containers.dropContents(level, worldPosition, inventory)
    }

    private fun consumeFuel() {
        if (!itemHandler.getStackInSlot(0).isEmpty) {
            fuelTime = ForgeHooks.getBurnTime(itemHandler.extractItem(0, 1, false), RecipeType.SMELTING)
            maxFuelTime = fuelTime
        }
    }

}

