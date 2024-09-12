package com.beevocate.mccourse.event;

import com.beevocate.mccourse.MCCourseMod;
import com.beevocate.mccourse.entity.custom.BloodDemonEntity;
import com.beevocate.mccourse.item.ModItems;
import com.beevocate.mccourse.item.custom.HammerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MCCourseMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    // Don't be a jerk License

    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }

            for(BlockPos pos : HammerItem.getBlocksToBeDestroyed(1, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                    continue;
                }

                HARVESTED_BLOCKS.add(pos);
                serverPlayer.gameMode.destroyBlock(pos);
                HARVESTED_BLOCKS.remove(pos);
            }
        }
    }


    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent event) {
        if(event.getEntity() instanceof Sheep sheep) {
            if(event.getSource().getDirectEntity() instanceof Player player) {
                if(player.getMainHandItem().getItem() == ModItems.METAL_DETECTOR.get()) {
                    player.sendSystemMessage(Component.literal(player.getName().getString() + " just hit a Sheep with a metal detector"));
                }

                if(player.getMainHandItem().getItem() == ModItems.ONION.get()) {
                    player.sendSystemMessage(Component.literal(player.getName().getString() + " just hit a Sheep with a onion"));
                    sheep.addEffect(new MobEffectInstance(MobEffects.GLOWING, 400));
                    player.getMainHandItem().shrink(1);
                }

                if(player.getMainHandItem().getItem() == Items.END_ROD) {
                    player.sendSystemMessage(Component.literal(player.getName().getString() + " just hit a Sheep with AN END ROD?"));
                    sheep.addEffect(new MobEffectInstance(MobEffects.POISON, 400));
                    player.getMainHandItem().shrink(1);
                }
            }
        }
        // Adds NAUSIA when for 4 seconds when hitting the Blood Demon
        if (event.getEntity() instanceof BloodDemonEntity bloodDemonEntity){
            if (event.getSource().getDirectEntity() instanceof  Player player){
                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION,80));
            }
        }

        // Hits you with lighting when you hit a Blood Demon
        if (event.getEntity() instanceof BloodDemonEntity bloodDemonEntity) {
            if (event.getSource().getDirectEntity() instanceof Player player) {
                if (player.level() instanceof ServerLevel serverLevel) {
                    BlockPos pos = player.blockPosition(); // Get the position of the entity
                    spawnsLightningBoltEntityOnHit(serverLevel, pos);
                }
            }
        }

        // Hits enemy with a lightning bolt when hit by the Sentient Bows Arrows - DOES NOT WORK
        if (event.getEntity() instanceof LivingEntity livingEntity){
            if (event.getSource().getDirectEntity() instanceof Player player) {
                if (livingEntity.getProjectile(ItemStack.EMPTY).getItem() == ModItems.SENTIENT_BOW.get()){
                    if (livingEntity.level() instanceof ServerLevel serverLevel) {
                        BlockPos pos = livingEntity.blockPosition(); // Get the position of the entity
                    }
                }
            }
        }
    }

    // Adds lightning bolt when I hit with the Azurite Sword
    public static void spawnsLightningBoltEntityOnHit(ServerLevel serverLevel, BlockPos pos) {
        LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, serverLevel);
        lightningBolt.moveTo(pos.getX(), pos.getY(), pos.getZ());
        serverLevel.addFreshEntity(lightningBolt);
    }

    // Adds ArrowEntity when I hit with the Azurite Sword
    public static void spawnsArrowEntityOnHit(ServerLevel serverLevel, BlockPos pos) {
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
    public static void spawnsZombieEntityOnHit(ServerLevel serverLevel, BlockPos pos) {
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