package com.beevocate.mccourse.entity;

import com.beevocate.mccourse.MCCourseMod;
import com.beevocate.mccourse.entity.custom.BloodDemonEntity;
import com.beevocate.mccourse.entity.custom.CapybaraEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MCCourseMod.MOD_ID);

    public static final RegistryObject<EntityType<CapybaraEntity>> CAPYBARA =
            ENTITY_TYPES.register("capybara", () -> EntityType.Builder.of(CapybaraEntity::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.4f).build("capybara"));

    public static final RegistryObject<EntityType<BloodDemonEntity>> BLOOD_DEMON =
            ENTITY_TYPES.register("blood_demon",() -> EntityType.Builder.of(BloodDemonEntity::new, MobCategory.MONSTER)
                    .sized(1f,1f).build("blood_demon"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
