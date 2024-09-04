package com.beevocate.mccourse.entity.custom;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class DemonEntity extends Monster {

    private float allowedHeightOffset = 0.5F;
    private int nextHeightOffsetChangeTick;

    public DemonEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

}
