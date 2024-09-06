package com.beevocate.mccourse.entity.client;

import com.beevocate.mccourse.MCCourseMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation CAPYBARA = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MCCourseMod.MOD_ID,"capybara"), "main");

    public static final ModelLayerLocation BLOOD_DEMON = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(MCCourseMod.MOD_ID,"blood_demon"), "main");


}
