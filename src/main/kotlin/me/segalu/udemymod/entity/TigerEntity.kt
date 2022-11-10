package me.segalu.udemymod.entity

import me.segalu.udemymod.init.EntityInit
import me.segalu.udemymod.init.ItemInit
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.Mth
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.*
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.vehicle.DismountHelper
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3
import net.minecraftforge.common.ForgeHooks
import net.minecraftforge.event.ForgeEventFactory
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.util.GeckoLibUtil.createFactory
import java.util.function.Predicate
import kotlin.random.Random

class TigerEntity(entityType: EntityType<out TamableAnimal>, level: Level) : TamableAnimal(entityType, level),
    PlayerRideableJumping, IAnimatable {
    private val factory = createFactory(this)
    private var playerJumpPendingScale = 0F

    companion object {
        val PREY_SELECTOR = Predicate { entity: LivingEntity ->
            val entitytype = entity.type
            entitytype === EntityType.SHEEP ||
                    entitytype === EntityType.RABBIT ||
                    entitytype === EntityType.COW ||
                    entitytype === EntityType.CHICKEN ||
                    entitytype === EntityType.PIG ||
                    entitytype === EntityInit.RACCOON
        }

        fun setAttributes() = createMobAttributes()
            .add(Attributes.MAX_HEALTH, 20.0)
            .add(Attributes.ATTACK_DAMAGE, 8.0)
            .add(Attributes.ATTACK_SPEED, 2.0)
            .add(Attributes.MOVEMENT_SPEED, 0.2)
            .add(Attributes.JUMP_STRENGTH, 1.0).build()

    }

    init {
        isTame = false
    }

    override fun getBreedOffspring(pLevel: ServerLevel, pOtherParent: AgeableMob): AgeableMob? {
        return null
    }

    override fun registerControllers(data: AnimationData) {
        data.addAnimationController(AnimationController(this, "controller", 0F, this::predicate))
    }

    private fun <E : IAnimatable> predicate(event: AnimationEvent<E>): PlayState {
        if (event.isMoving) {
            event.controller.setAnimation(
                AnimationBuilder().addAnimation(
                    "animation.tiger.walk",
                    EDefaultLoopTypes.LOOP
                )
            )
            return PlayState.CONTINUE
        }

        if (isInSittingPose) {
            event.controller.setAnimation(
                AnimationBuilder().addAnimation(
                    "animation.tiger.sitting",
                    EDefaultLoopTypes.HOLD_ON_LAST_FRAME
                )
            )
            return PlayState.CONTINUE
        }

        event.controller.setAnimation(AnimationBuilder().addAnimation("animation.tiger.idle", EDefaultLoopTypes.LOOP))
        return PlayState.CONTINUE
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, FloatGoal(this))
        goalSelector.addGoal(2, SitWhenOrderedToGoal(this))
        goalSelector.addGoal(3, RandomStrollGoal(this, 1.0))
        targetSelector.addGoal(3, HurtByTargetGoal(this))
        goalSelector.addGoal(3, RandomSwimmingGoal(this, 0.0, 1))
        goalSelector.addGoal(4, MeleeAttackGoal(this, 1.2, false))
        goalSelector.addGoal(4, RandomLookAroundGoal(this))
        goalSelector.addGoal(4, TemptGoal(this, 1.2, Ingredient.of(ItemInit.TURNIP), false))
        targetSelector.addGoal(7, NonTameRandomTargetGoal(this, Animal::class.java, false, PREY_SELECTOR))
        goalSelector.addGoal(6, FollowOwnerGoal(this, 1.0, 10.0f, 2.0f, false))
    }

    override fun mobInteract(pPlayer: Player, pHand: InteractionHand): InteractionResult {
        val itemStack = pPlayer.getItemInHand(pHand)
        val item = itemStack.item
        val tameableItem = ItemInit.TURNIP

        if (level.isClientSide) {
            val flag = isOwnedBy(pPlayer) || isTame || item == tameableItem && !isTame
            return if (flag) InteractionResult.CONSUME else InteractionResult.PASS
        } else {
            if (isTame) {
                if (pPlayer.isCrouching && pHand == InteractionHand.MAIN_HAND) {
                    isOrderedToSit = !isOrderedToSit
                }

                if (isFood(itemStack) && health < maxHealth) {
                    if (!pPlayer.isCreative) {
                        itemStack.shrink(1)
                    }

                    heal(item.getFoodProperties(itemStack, this)!!.nutrition.toFloat())
                    return InteractionResult.SUCCESS
                }
                pPlayer.startRiding(this)
            } else if (item == tameableItem && !isOnFire) {
                if (!pPlayer.isCreative) {
                    itemStack.shrink(1)
                }

                if (Random.nextInt(3) == 0 && !ForgeEventFactory.onAnimalTame(this, pPlayer)) {
                    tame(pPlayer)
                    navigation.stop()

                    target = null
                    isOrderedToSit = true
                    level.broadcastEntityEvent(this, 7.toByte())
                } else {
                    level.broadcastEntityEvent(this, 6.toByte())
                }
                return InteractionResult.SUCCESS
            }

            return super.mobInteract(pPlayer, pHand)
        }
    }

    override fun canBeControlledByRider(): Boolean {
        return controllingPassenger is LivingEntity
    }

    override fun getControllingPassenger() = firstPassenger

    override fun travel(pTravelVector: Vec3) {
        if (isAlive) {
            if (isVehicle && canBeControlledByRider()) {
                val livingEntity = controllingPassenger as LivingEntity
                yRot = livingEntity.yRot
                yRotO = yRot
                xRot = livingEntity.xRot * 0.5F
                setRot(yRot, xRot)
                yBodyRot = yRot
                yHeadRot = yBodyRot
                val f = livingEntity.xxa * 0.5F
                var f1 = livingEntity.zza
                if (f1 <= 0.0F) {
                    f1 *= 0.25F
                }

                if (playerJumpPendingScale > 0.0f && !super.jumping && onGround) {
                    val d0: Double =
                        getCustomJump() * playerJumpPendingScale.toDouble() * blockJumpFactor.toDouble()
                    val d1 = d0 + jumpBoostPower
                    val vec3 = deltaMovement
                    setDeltaMovement(vec3.x, d1, vec3.z)
                    setJumping(true)
                    hasImpulse = true
                    ForgeHooks.onLivingJump(this)
                    if (f1 > 0.0f) {
                        val f2 = Mth.sin(yRot * (Math.PI.toFloat() / 180f))
                        val f3 = Mth.cos(yRot * (Math.PI.toFloat() / 180f))
                        deltaMovement = deltaMovement.add(
                            (-0.4f * f2 * this.playerJumpPendingScale).toDouble(),
                            0.0,
                            (0.4f * f3 * this.playerJumpPendingScale).toDouble()
                        )
                    }
                    this.playerJumpPendingScale = 0.0f
                }

                flyingSpeed = speed * 0.1f
                if (isControlledByLocalInstance) {
                    speed = getAttributeValue(Attributes.MOVEMENT_SPEED).toFloat()
                    super.travel(Vec3(f.toDouble(), pTravelVector.y, f1.toDouble()))
                } else if (livingEntity is Player) {
                    deltaMovement = Vec3.ZERO
                }

                if (onGround) {
                    this.playerJumpPendingScale = 0.0f
                    setJumping(false)
                }

                calculateEntityAnimation(this, false)
                tryCheckInsideBlocks()
            } else {
                flyingSpeed = 0.02f
                super.travel(pTravelVector)
            }
        }
    }

    override fun getDismountLocationForPassenger(pPassenger: LivingEntity): Vec3 {
        val direction = motionDirection
        if (direction.axis == Direction.Axis.Y) {
            return super.getDismountLocationForPassenger(pPassenger)
        } else {
            val aint = DismountHelper.offsetsForDirection(direction)
            val blockPos = blockPosition()
            val blockPosMutable = BlockPos.MutableBlockPos()

            for (pose in pPassenger.dismountPoses) {
                val axisalignedbb = pPassenger.getLocalBoundsForPose(pose)

                for (aint1 in aint) {
                    blockPosMutable.set(blockPos.x + aint1[0], blockPos.y, blockPos.z + aint1[1])
                    val d0 = level.getBlockFloorHeight(blockPosMutable)
                    if (DismountHelper.isBlockFloorValid(d0)) {
                        val vec3 = Vec3.upFromBottomCenterOf(blockPosMutable, d0)
                        if (DismountHelper.canDismountTo(level, pPassenger, axisalignedbb.move(vec3))) {
                            pPassenger.pose = pose
                            return vec3
                        }
                    }
                }
            }
            return super.getDismountLocationForPassenger(pPassenger)
        }
    }

    override fun getFactory() = factory

    //    Jumping
    override fun causeFallDamage(pFallDistance: Float, pMultiplier: Float, pSource: DamageSource): Boolean {
        if (pFallDistance > 1.0f) {
            playSound(SoundEvents.HORSE_LAND, 0.4f, 1.0f)
        }
        val i = calculateFallDamage(pFallDistance, pMultiplier)
        return if (i <= 0) {
            false
        } else {
            hurt(pSource, i.toFloat())
            if (isVehicle) {
                for (entity in indirectPassengers) {
                    entity.hurt(pSource, i.toFloat())
                }
            }
            playBlockFallSound()
            true
        }
    }

    override fun calculateFallDamage(pDistance: Float, pDamageMultiplier: Float): Int {
        return Mth.ceil((pDistance * 0.5f - 6.0f) * pDamageMultiplier)
    }

    private fun playJumpSound() {
        playSound(SoundEvents.HORSE_JUMP, 0.4f, 1.0f)
    }

    private fun getCustomJump(): Double {
        return getAttributeValue(Attributes.JUMP_STRENGTH)
    }

    override fun onPlayerJump(jumpPower: Int) {
        var pJumpPower = jumpPower
        if (pJumpPower < 0) {
            pJumpPower = 0
        }
        if (pJumpPower >= 90) {
            playerJumpPendingScale = 1.0f
        } else {
            playerJumpPendingScale = 0.4f + 0.4f * pJumpPower.toFloat() / 90.0f
        }
    }

    override fun canJump() = true

    override fun handleStartJump(pJumpPower: Int) {
        playJumpSound()
    }

    override fun handleStopJump() {}

}