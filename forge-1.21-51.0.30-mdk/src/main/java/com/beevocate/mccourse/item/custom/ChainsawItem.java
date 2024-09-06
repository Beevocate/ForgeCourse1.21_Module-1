package com.beevocate.mccourse.item.custom;

import com.beevocate.mccourse.component.ModDataComponentTypes;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Objects;

public class ChainsawItem extends Item {
    public ChainsawItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();

        if(!level.isClientSide){
            if(level.getBlockState(context.getClickedPos()).is(BlockTags.LOGS)){
                level.destroyBlock(context.getClickedPos(),true, context.getPlayer());
                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level),
                        ((ServerPlayer) context.getPlayer()), item ->
                        Objects.requireNonNull(context.getPlayer()).onEquippedItemBroken(item, EquipmentSlot.MAINHAND));


                context.getItemInHand().set(ModDataComponentTypes.COORDINATES.get(), context.getClickedPos());
            }
        }

        if(level.getBlockState(context.getClickedPos()).is(BlockTags.LOGS)) {
            context.getItemInHand().set(ModDataComponentTypes.COORDINATES.get(), context.getClickedPos());
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if(!Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.mccourse.chainsaw.tooltip.shift"));
        }
        else {
            pTooltipComponents.add(Component.translatable("tooltip.mccourse.chainsaw.tooltip.1"));
            pTooltipComponents.add(Component.translatable("tooltip.mccourse.chainsaw.tooltip.2"));
        }
        if(pStack.get(ModDataComponentTypes.COORDINATES.get()) != null) {
            pTooltipComponents.add(Component.literal("Last Tree was chopped at " + pStack.get(ModDataComponentTypes.COORDINATES.get())));
        }



        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
