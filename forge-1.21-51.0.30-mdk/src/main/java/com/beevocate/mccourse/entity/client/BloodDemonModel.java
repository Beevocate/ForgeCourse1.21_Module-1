package com.beevocate.mccourse.entity.client;

import com.beevocate.mccourse.entity.client.animation.BloodDemonAnimations;
import com.beevocate.mccourse.entity.custom.BloodDemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class BloodDemonModel extends HierarchicalModel<BloodDemonEntity> {
    private final ModelPart body;
    private final ModelPart head;

    public BloodDemonModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = body.getChild("body").getChild("head");
    }

    public BloodDemonModel(ModelPart body, ModelPart head) {
        this.body = body;
        this.head = head;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -18.0F, 0.0F));

        PartDefinition leftarm = arms.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(24, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.0F, -6.0F, 0.0F));

        PartDefinition rightarm = arms.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(0, 32).addBox(-2.0F, 0.3192F, -1.7761F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -7.0F, 0.0F));

        PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition left_leg = legs.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 32).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, -12.0F, 0.0F));

        PartDefinition right_leg = legs.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 0).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, -12.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -28.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 48, 48);
    }

    @Override
    public ModelPart root() {
        return body;
    }

    @Override
    public void setupAnim(BloodDemonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw,headPitch);

        this.animateWalk(BloodDemonAnimations.walk,limbSwing,limbSwingAmount,2f,2.5f);
        this.animate(entity.idleAnimationState,BloodDemonAnimations.idle, ageInTicks,1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45f);

        this.head.xRot = headYaw * ((float)Math.PI / 180F);
        this.head.yRot = headPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

}
