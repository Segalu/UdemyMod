package me.segalu.udemymod.entity

import me.segalu.udemymod.init.BlockInit
import me.segalu.udemymod.init.EntityInit
import me.segalu.udemymod.init.ItemInit
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.vehicle.Boat
import net.minecraft.world.item.Item
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block

class ModBoatEntity(pEntityType: EntityType<out Boat>, pLevel: Level) : Boat(pEntityType, pLevel) {
    init {
        blocksBuilding = true
    }

    constructor(level: Level, x: Double, y: Double, z: Double) : this(EntityInit.MOD_BOAT, level) {
        setPos(x, y, z)
        xo = x
        yo = y
        zo = z
    }

    companion object {
        val DATA_ID_TYPE = SynchedEntityData.defineId(ModBoatEntity::class.java, EntityDataSerializers.INT)
    }

    override fun addAdditionalSaveData(compoundTag: CompoundTag) {
        compoundTag.putString("Type", getModBoatType().name)
    }

    override fun readAdditionalSaveData(pCompound: CompoundTag) {
        if (pCompound.contains("Type", 8)) {
            setBoatType(ModBoatEntity.Type.valueOf(pCompound.getString("Type")))
        }
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        this.entityData.define(DATA_ID_TYPE, Type.CHERRY_BLOSSOM.ordinal)
    }

    override fun getDropItem(): Item {
        return when (getModBoatType()) {
            Type.CHERRY_BLOSSOM -> ItemInit.CHERRY_BLOSSOM_BOAT
        }
    }

    fun setBoatType(boatType: Type) {
        entityData.set(DATA_ID_TYPE, boatType.ordinal)
    }

    fun getModBoatType(): ModBoatEntity.Type {
        return ModBoatEntity.Type.values()[entityData.get(DATA_ID_TYPE)]
    }

    enum class Type(pPlanks: Block, name: String) {
        CHERRY_BLOSSOM(BlockInit.CHERRY_BLOSSOM_PLANKS.get(), "cherry_blossom")
    }

}