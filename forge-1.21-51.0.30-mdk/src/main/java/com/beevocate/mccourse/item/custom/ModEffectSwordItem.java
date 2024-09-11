package com.beevocate.mccourse.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

import static com.beevocate.mccourse.event.ModEvents.*;

public class ModEffectSwordItem extends SwordItem {
    private final Holder<MobEffect> effect;

    public ModEffectSwordItem(Tier pTier, Properties pProperties, Holder<MobEffect> effect) {
        super(pTier, pProperties);
        this.effect = effect;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        if (entity instanceof LivingEntity livingEntity){
            livingEntity.addEffect(new MobEffectInstance(effect,400,0), player);
        }

        if (player.level() instanceof ServerLevel serverLevel) {
            BlockPos pos = entity.blockPosition(); // Get the position of the entity

            spawnsArrowEntityOnHit(serverLevel,pos);
            spawnsZombieEntityOnHit(serverLevel,pos);
            spawnsLightningBoltEntityOnHit(serverLevel,pos);
        }
        return super.onLeftClickEntity(stack, player, entity);
    }
}

