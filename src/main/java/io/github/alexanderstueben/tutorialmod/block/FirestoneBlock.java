package io.github.alexanderstueben.tutorialmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class FirestoneBlock extends Block {

    public FirestoneBlock(Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, 
                                Hand hand, BlockRayTraceResult blockRayTraceResult) {
        if (!world.isClientSide()) {
            if (hand == Hand.MAIN_HAND) {
                System.out.println("I right-clicked a Firestone Block with my Main Hand");
            }
            if (hand == Hand.OFF_HAND) {
                System.out.println("I right-clicked a Firestone Block with my Off Hand");
            }
        }
        return super.use(blockState, world, blockPos, playerEntity, hand, blockRayTraceResult);
    }

    @Override
    public void attack(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity) {
        if (!world.isClientSide()) {
            System.out.println("I left-clicked a Firestone Block");
        }
    }

    @Override
    public void stepOn(World world, BlockPos pos, Entity entity) {
        entity.setSecondsOnFire(5);
        super.stepOn(world, pos, entity);
    }
}
