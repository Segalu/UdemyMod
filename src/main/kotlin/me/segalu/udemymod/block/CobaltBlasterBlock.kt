package me.segalu.udemymod.block

import me.segalu.udemymod.init.BlockEntitiesInit
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.Containers
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.entity.BlockEntityTicker
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.AttachFace
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.DirectionProperty
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.shapes.BooleanOp
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.Shapes
import net.minecraft.world.phys.shapes.VoxelShape
import net.minecraftforge.network.NetworkHooks
import java.util.stream.Stream


class CobaltBlasterBlock(pProperties: Properties) : BaseEntityBlock(pProperties) {

    init {
        registerDefaultState(getStateDefinition().any().setValue(FACING, Direction.NORTH))
        registerDefaultState(getStateDefinition().any().setValue(FACE, AttachFace.FLOOR))
    }


    override fun getStateForPlacement(pContext: BlockPlaceContext): BlockState? {
        return defaultBlockState().setValue(FACING, pContext.horizontalDirection.opposite)
    }

    override fun rotate(pState: BlockState, pRotation: Rotation): BlockState {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)))
    }

    override fun mirror(pState: BlockState, pMirror: Mirror): BlockState {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)))
    }

    override fun getRenderShape(pState: BlockState): RenderShape {
        return RenderShape.MODEL
    }

    override fun onRemove(
        pState: BlockState,
        pLevel: Level,
        pPos: BlockPos,
        pNewState: BlockState,
        pIsMoving: Boolean
    ) {
        if (pState.block != pNewState.block) {
            val blockEntity = pLevel.getBlockEntity(pPos)
            if (blockEntity is CobaltBlasterBlockEntity) {
                blockEntity.drops()
            }
        }
    }

    override fun use(
        pState: BlockState?, pLevel: Level, pPos: BlockPos?,
        pPlayer: Player?, pHand: InteractionHand?, pHit: BlockHitResult?
    ): InteractionResult? {
        if (!pLevel.isClientSide()) {
            val entity = pLevel.getBlockEntity(pPos)
            if (entity is CobaltBlasterBlockEntity) {
                println(entity)
                NetworkHooks.openGui(pPlayer as ServerPlayer?, entity as CobaltBlasterBlockEntity?, pPos)
            } else {
                throw IllegalStateException("Our Container provider is missing!")
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide())
    }

    override fun <T : BlockEntity> getTicker(
        pLevel: Level?,
        pState: BlockState?,
        pBlockEntityType: BlockEntityType<T>
    ): BlockEntityTicker<T>? {
        return createTickerHelper(pBlockEntityType, BlockEntitiesInit.COBALT_BLASTER, CobaltBlasterBlockEntity::tick)
    }

    override fun createBlockStateDefinition(pBuilder: StateDefinition.Builder<Block, BlockState>) {
        pBuilder.add(FACING)
        pBuilder.add(FACE)
    }

    override fun newBlockEntity(pPos: BlockPos, pState: BlockState): BlockEntity? {
        return CobaltBlasterBlockEntity(pPos, pState)
    }

    override fun getShape(
        pState: BlockState,
        pLevel: BlockGetter,
        pPos: BlockPos,
        pContext: CollisionContext
    ): VoxelShape {
        return when (pState.getValue(FACING)) {
            Direction.NORTH -> SHAPE_N
            Direction.EAST -> SHAPE_E
            Direction.WEST -> SHAPE_W
            else -> SHAPE_S
        }
    }

    companion object {
        val FACING: DirectionProperty = BlockStateProperties.HORIZONTAL_FACING
        val FACE = BlockStateProperties.ATTACH_FACE

        private val SHAPE_N = Stream.of(
            box(13.0, 0.0, 0.0, 16.0, 3.0, 3.0),
            box(13.0, 0.0, 13.0, 16.0, 3.0, 16.0),
            box(0.0, 0.0, 13.0, 3.0, 3.0, 16.0),
            box(0.0, 0.0, 0.0, 3.0, 3.0, 3.0),
            box(3.0, 2.0, 4.0, 13.0, 6.0, 5.0),
            box(2.0, 0.0, 1.0, 14.0, 2.0, 14.0),
            box(3.0, 0.0, 15.0, 13.0, 2.0, 16.0),
            box(3.0, 2.0, 5.0, 13.0, 14.0, 14.0),
            box(3.0, 0.0, 14.0, 13.0, 7.0, 15.0),
            box(4.0, 13.0, 7.0, 12.0, 15.0, 13.0),
            box(11.0, 6.0, 3.0, 13.0, 14.0, 5.0),
            box(3.0, 6.0, 3.0, 5.0, 14.0, 5.0)
        ).reduce { v1: VoxelShape?, v2: VoxelShape? ->
            Shapes.join(v1, v2, BooleanOp.OR)
        }.get()
        private val SHAPE_E = Stream.of(
            box(13.0, 0.0, 13.0, 16.0, 3.0, 16.0),
            box(0.0, 0.0, 13.0, 3.0, 3.0, 16.0),
            box(0.0, 0.0, 0.0, 3.0, 3.0, 3.0),
            box(13.0, 0.0, 0.0, 16.0, 3.0, 3.0),
            box(11.0, 2.0, 3.0, 12.0, 6.0, 13.0),
            box(2.0, 0.0, 2.0, 15.0, 2.0, 14.0),
            box(0.0, 0.0, 3.0, 1.0, 2.0, 13.0),
            box(2.0, 2.0, 3.0, 11.0, 14.0, 13.0),
            box(1.0, 0.0, 3.0, 2.0, 7.0, 13.0),
            box(3.0, 13.0, 4.0, 9.0, 15.0, 12.0),
            box(11.0, 6.0, 11.0, 13.0, 14.0, 13.0),
            box(11.0, 6.0, 3.0, 13.0, 14.0, 5.0)
        ).reduce { v1: VoxelShape?, v2: VoxelShape? ->
            Shapes.join(
                v1, v2, BooleanOp.OR
            )
        }.get()
        private val SHAPE_W = Stream.of(
            box(0.0, 0.0, 0.0, 3.0, 3.0, 3.0),
            box(13.0, 0.0, 0.0, 16.0, 3.0, 3.0),
            box(13.0, 0.0, 13.0, 16.0, 3.0, 16.0),
            box(0.0, 0.0, 13.0, 3.0, 3.0, 16.0),
            box(4.0, 2.0, 3.0, 5.0, 6.0, 13.0),
            box(1.0, 0.0, 2.0, 14.0, 2.0, 14.0),
            box(15.0, 0.0, 3.0, 16.0, 2.0, 13.0),
            box(5.0, 2.0, 3.0, 14.0, 14.0, 13.0),
            box(14.0, 0.0, 3.0, 15.0, 7.0, 13.0),
            box(7.0, 13.0, 4.0, 13.0, 15.0, 12.0),
            box(3.0, 6.0, 3.0, 5.0, 14.0, 5.0),
            box(3.0, 6.0, 11.0, 5.0, 14.0, 13.0)
        ).reduce { v1: VoxelShape?, v2: VoxelShape? ->
            Shapes.join(v1, v2, BooleanOp.OR)
        }.get()
        private val SHAPE_S = Stream.of(
            box(0.0, 0.0, 13.0, 3.0, 3.0, 16.0),
            box(0.0, 0.0, 0.0, 3.0, 3.0, 3.0),
            box(13.0, 0.0, 0.0, 16.0, 3.0, 3.0),
            box(13.0, 0.0, 13.0, 16.0, 3.0, 16.0),
            box(3.0, 2.0, 11.0, 13.0, 6.0, 12.0),
            box(2.0, 0.0, 2.0, 14.0, 2.0, 15.0),
            box(3.0, 0.0, 0.0, 13.0, 2.0, 1.0),
            box(3.0, 2.0, 2.0, 13.0, 14.0, 11.0),
            box(3.0, 0.0, 1.0, 13.0, 7.0, 2.0),
            box(4.0, 13.0, 3.0, 12.0, 15.0, 9.0),
            box(3.0, 6.0, 11.0, 5.0, 14.0, 13.0),
            box(11.0, 6.0, 11.0, 13.0, 14.0, 13.0)
        ).reduce { v1: VoxelShape?, v2: VoxelShape? ->
            Shapes.join(v1, v2, BooleanOp.OR)
        }.get()
    }


}