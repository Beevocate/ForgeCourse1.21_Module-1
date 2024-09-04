package com.beevocate.mccourse.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties ONION = new FoodProperties.Builder().nutrition(3).saturationModifier(5)
            .effect(new MobEffectInstance(MobEffects.WITHER,200),0.95f).build();
}
