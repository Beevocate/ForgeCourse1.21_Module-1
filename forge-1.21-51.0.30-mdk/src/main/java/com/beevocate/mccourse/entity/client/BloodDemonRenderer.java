package com.beevocate.mccourse.entity.client;

import com.beevocate.mccourse.MCCourseMod;
import com.beevocate.mccourse.entity.custom.BloodDemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BloodDemonRenderer extends MobRenderer<BloodDemonEntity, BloodDemonModel> {

    public BloodDemonRenderer(EntityRendererProvider.Context pContext) {
        super(pContext,new BloodDemonModel(pContext.bakeLayer(ModModelLayers.BLOOD_DEMON)),0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(BloodDemonEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(MCCourseMod.MOD_ID,"textures/entity/blood_demon/blood_demon.png");
    }

    @Override
    public void render(BloodDemonEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
