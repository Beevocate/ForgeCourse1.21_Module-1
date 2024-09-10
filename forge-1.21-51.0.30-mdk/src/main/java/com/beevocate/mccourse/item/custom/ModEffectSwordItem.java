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

    // Adds lightning bolt when I hit with the Azurite Sword
    public static void spawnsLightningBoltEntityOnHit(ServerLevel serverLevel, BlockPos pos) {
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, serverLevel);
        lightningBolt.moveTo(pos.getX(), pos.getY(), pos.getZ());
        serverLevel.addFreshEntity(lightningBolt);
    }
    // Adds ArrowEntity when I hit with the Azurite Sword
    private static void spawnsArrowEntityOnHit(ServerLevel serverLevel, BlockPos pos) {
        AbstractArrow abstractArrow = new AbstractArrow(EntityType.ARROW, serverLevel) {
            @Override
            protected ItemStack getDefaultPickupItem() {
                return null;
            }
        };
        abstractArrow.moveTo(pos.getX(), pos.getY()+5, pos.getZ());
        serverLevel.addFreshEntity(abstractArrow);
    }

    //  Adds zombie to when I hit it with the Azurite Sword
    private static void spawnsZombieEntityOnHit(ServerLevel serverLevel, BlockPos pos) {
        Zombie zombie1 = new Zombie(EntityType.ZOMBIE, serverLevel);
        Zombie zombie2 = new Zombie(EntityType.ZOMBIE, serverLevel);
        Zombie zombie3 = new Zombie(EntityType.ZOMBIE, serverLevel);
        Zombie zombie4 = new Zombie(EntityType.ZOMBIE, serverLevel);
        zombie1.moveTo(pos.getX()+4, pos.getY()+30, pos.getZ()+4);
        zombie2.moveTo(pos.getX()+4, pos.getY()+30, pos.getZ()-4);
        zombie3.moveTo(pos.getX()-4, pos.getY()+30, pos.getZ()+4);
        zombie4.moveTo(pos.getX()-4, pos.getY()+30, pos.getZ()-4);
        serverLevel.addFreshEntity(zombie1);
        serverLevel.addFreshEntity(zombie2);
        serverLevel.addFreshEntity(zombie3);
        serverLevel.addFreshEntity(zombie4);
    }
}

