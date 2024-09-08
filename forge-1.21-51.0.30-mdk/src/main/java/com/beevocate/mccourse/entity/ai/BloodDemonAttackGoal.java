package com.beevocate.mccourse.entity.ai;


import com.beevocate.mccourse.entity.custom.BloodDemonEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Zombie;


public class BloodDemonAttackGoal extends MeleeAttackGoal {
    private final BloodDemonEntity bloodDemon;
    private int raiseArmTicks;

    public BloodDemonAttackGoal(BloodDemonEntity bloodDemon, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(bloodDemon, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.bloodDemon = bloodDemon;
    }

    public BloodDemonAttackGoal(PathfinderMob pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen, BloodDemonEntity bloodDemon) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.bloodDemon = bloodDemon;
    }

    @Override
    public void start() {
        super.start();
        this.raiseArmTicks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.bloodDemon.setAggressive(false);
    }

    @Override
    public void tick() {
        super.tick();
        this.raiseArmTicks++;
        if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
            this.bloodDemon.setAggressive(true);
        } else {
            this.bloodDemon.setAggressive(false);
        }
    }
}