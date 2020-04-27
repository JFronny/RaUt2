package com.jfronny.raut.api;

import dev.emi.trinkets.api.ITrinket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RingRenderer {
    public static void Render(Item ring, MatrixStack matrixStack, VertexConsumerProvider vertexConsumer, int light, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw, float headPitch) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = new ItemStack(ring);
        //Translate to chest
        if (player.isInSneakingPose() && !model.riding && !player.isSwimming()) {
            matrixStack.translate(0.0F, 0.2F, 0.0F);
            matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(model.torso.pitch * 57.5F));
        }
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(model.torso.yaw * 57.5F));
        matrixStack.translate(0.0F, 0.4F, -0.16F);
        //
        boolean hasChestplate = false;
        for (ItemStack item : player.getArmorItems()) {
            Item tmpItem = item.getItem();
            if ((tmpItem instanceof ArmorItem) && ((ArmorItem) tmpItem).getSlotType() == EquipmentSlot.CHEST) {
                hasChestplate = true;
            }
        }
        if (hasChestplate) {
            matrixStack.translate(0, 0, -0.05);
        }
        matrixStack.scale(0.5f, -0.5f, 0.5f);
        matrixStack.translate(0, 0.2, 0);
        itemRenderer.renderItem(stack, ModelTransformation.Mode.FIXED, light, 0, matrixStack, vertexConsumer);
        matrixStack.translate(0, -0.2, 0);
        matrixStack.scale(2f, -2f, 2f);
        if (hasChestplate) {
            matrixStack.translate(0, 0, 0.05);
        }
        //Undo translation to chest
        matrixStack.translate(0.0F, -0.4F, 0.16F);
        matrixStack.multiply(Vector3f.NEGATIVE_Y.getDegreesQuaternion(model.torso.yaw * 57.5F));
        if (player.isInSneakingPose() && !model.riding && !player.isSwimming()) {
            matrixStack.multiply(Vector3f.NEGATIVE_X.getDegreesQuaternion(model.torso.pitch * 57.5F));
            matrixStack.translate(0.0F, -0.2F, 0.0F);
        }
    }
}
