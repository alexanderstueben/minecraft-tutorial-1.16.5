package io.github.alexanderstueben.tutorialmod.item;

import java.util.List;

import javax.annotation.Nullable;

import io.github.alexanderstueben.tutorialmod.util.TutorialTags;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class Firestone extends Item {
    public Firestone(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getLevel();

        if (!world.isClientSide) {
            PlayerEntity playerEntity = context.getPlayer();
            BlockState clickedBlock = world.getBlockState(context.getClickedPos());

            rightClockOnCertainBlockState(clickedBlock, context, playerEntity);
            stack.hurtAndBreak(1, playerEntity, player -> player.broadcastBreakEvent(context.getHand()));
        }

        return super.onItemUseFirst(stack, context);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World world, List<ITextComponent> tooltip,
            ITooltipFlag flag) {
        
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.tutorialmod.firestone.shift"));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.tutorialmod.firestone"));
        }
        
        super.appendHoverText(itemStack, world, tooltip, flag);
    }

    private void rightClockOnCertainBlockState(BlockState clickedBlock, ItemUseContext context,
            PlayerEntity playerEntity) {
        if (random.nextFloat() > 0.5f) {
            playerEntity.setSecondsOnFire(6);
        } else if (!playerEntity.isOnFire() && blockIsValidForResistance(clickedBlock)) {
            playerEntity.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200));
            context.getLevel().destroyBlock(context.getClickedPos(), false);
        } else {
            lightGroundOnFire(context);
        }
    }

    private void lightGroundOnFire(ItemUseContext context) {
        PlayerEntity playerentity = context.getPlayer();
        World world = context.getLevel();
        BlockPos blockPos = context.getClickedPos().relative(context.getClickedFace());

        if (AbstractFireBlock.canBePlacedAt(world, blockPos, context.getHorizontalDirection())) {
            world.playSound(
                    playerentity,
                    blockPos,
                    SoundEvents.FLINTANDSTEEL_USE,
                    SoundCategory.BLOCKS,
                    1.0F,
                    random.nextFloat() * 0.4F + 0.8F);
            BlockState blockstate = AbstractFireBlock.getState(world, blockPos);
            world.setBlock(blockPos, blockstate, 11);
        }
    }

    private boolean blockIsValidForResistance(BlockState clickedBlock) {
        return clickedBlock.is(TutorialTags.Blocks.FIRESTONE_CLICKABLE);
    }
}
