package me.segalu.udemymod.entity

import me.segalu.udemymod.entity.variant.RaccoonVariant
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.block.state.BlockState
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory
import software.bernie.geckolib3.util.GeckoLibUtil.createFactory


class RaccoonEntity(pEntityType: EntityType<out Animal>, pLevel: Level) : Animal(pEntityType, pLevel), IAnimatable {
    private val factory = createFactory(this)

    companion object {
        fun setAttributes(): AttributeSupplier {
            return createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_SPEED, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3).build()
        }

        val DATA_ID_TYPE_VARIANT = SynchedEntityData.defineId(RaccoonEntity::class.java, EntityDataSerializers.INT)
    }

    override fun registerGoals() {
        goalSelector.addGoal(1, FloatGoal(this))
//        goalSelector.addGoal(1, SitWhenOrderedToGoal(this))
        goalSelector.addGoal(2, PanicGoal(this, 1.250))
//        goalSelector.addGoal(3, FollowOwnerGoal(this, 1.0, 10F, 2F, false))
        goalSelector.addGoal(3, LookAtPlayerGoal(this, Player::class.java, 8.0F))
        goalSelector.addGoal(4, WaterAvoidingRandomStrollGoal(this, 1.0))
        goalSelector.addGoal(5, RandomLookAroundGoal(this))
        goalSelector.addGoal(6, HurtByTargetGoal(this).setAlertOthers())
    }

    override fun getBreedOffspring(pLevel: ServerLevel, pOtherParent: AgeableMob): AgeableMob? {
        return null
    }

    override fun playStepSound(pPos: BlockPos, pState: BlockState) {
        playSound(SoundEvents.SWEET_BERRY_BUSH_PICK_BERRIES, 0.15F, 1.0F)
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.CAT_STRAY_AMBIENT
    }

    override fun getHurtSound(pDamageSource: DamageSource): SoundEvent {
        return SoundEvents.DOLPHIN_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.DOLPHIN_DEATH
    }

    override fun getSoundVolume(): Float {
        return 0.2F
    }

    //    ANIMATIONS
    private fun <E : IAnimatable> predicate(event: AnimationEvent<E>): PlayState {
        if (event.isMoving) {
            event.controller.setAnimation(
                AnimationBuilder().addAnimation(
                    "animation.raccoon.walk",
                    EDefaultLoopTypes.LOOP
                )
            )
            return PlayState.CONTINUE
        }
        event.controller.setAnimation(AnimationBuilder().addAnimation("animation.raccoon.idle", EDefaultLoopTypes.LOOP))
        return PlayState.CONTINUE
    }

    override fun registerControllers(data: AnimationData) {
        data.addAnimationController(AnimationController(this, "controller", 0F, this::predicate))
    }

    override fun getFactory(): AnimationFactory {
        return this.factory
    }

    //    VARIANTS
    override fun finalizeSpawn(
        level: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnType: MobSpawnType,
        spawnData: SpawnGroupData?,
        pCompound: CompoundTag?
    ): SpawnGroupData {
        val variant = RaccoonVariant.values().random()
        setVariant(variant)
        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, pCompound)!!
    }

    private fun setVariant(variant: RaccoonVariant) {
        entityData.set(DATA_ID_TYPE_VARIANT, variant.id and 255)
    }

    fun getVariant() = RaccoonVariant.byId(getTypeVariant() and 255)

    private fun getTypeVariant() = entityData.get(DATA_ID_TYPE_VARIANT)

    override fun readAdditionalSaveData(pCompound: CompoundTag) {
        super.readAdditionalSaveData(pCompound)
        entityData.set(DATA_ID_TYPE_VARIANT, pCompound.getInt("Variant"))
    }

    override fun addAdditionalSaveData(pCompound: CompoundTag) {
        super.addAdditionalSaveData(pCompound)
        pCompound.putInt("Variant", getTypeVariant())
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(DATA_ID_TYPE_VARIANT, 0)
    }

}
