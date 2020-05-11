package com.jfronny.raut.trinket;

import com.jfronny.raut.RaUt;
import com.jfronny.raut.gui.BackpackInventory;
import dev.emi.trinkets.api.ITrinket;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class BackpackTrinket extends Item implements ITrinket {
    public BackpackTrinket() {
        super(new Item.Settings().group(ItemGroup.MISC).maxCount(1));
    }

    public static void openGUI(ItemStack stack, PlayerEntity playerEntity) {
        if (!stack.isEmpty() && stack.getItem() instanceof BackpackTrinket) {
            BackpackInventory.Size size = null;
            if (!stack.hasTag()) {
                stack.setTag(new CompoundTag());
            }
            if (!stack.getTag().contains("Size")) {
                stack.getTag().putInt("Size", 0);
            } else {
                switch (stack.getTag().getInt("Size")) {
                    case 0:
                        size = BackpackInventory.Size.SMALL;
                        break;
                    case 1:
                        size = BackpackInventory.Size.MEDIUM;
                        break;
                    case 2:
                        size = BackpackInventory.Size.LARGE;
                        break;
                    default:
                        size = BackpackInventory.Size.SMALL;
                        break;
                }
                stack.getTag().putInt("Size", size.ordinal());
            }
            final int sizeInt = size != null ? size.ordinal() : 0;
            ContainerProviderRegistry.INSTANCE.openContainer(new Identifier(RaUt.MOD_ID, "backpack"), playerEntity, (buf) -> {
                buf.writeItemStack(stack);
                buf.writeInt(sizeInt);
                buf.writeInt(playerEntity.inventory.getSlotWithStack(stack));
            });
        }
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group.equals(SlotGroups.CHEST) && slot.equals(Slots.BACKPACK);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (world.isClient) return new TypedActionResult<>(ActionResult.PASS, playerEntity.getStackInHand(hand));

        ItemStack stack = playerEntity.getStackInHand(hand);
        openGUI(stack, playerEntity);

        return new TypedActionResult<>(ActionResult.SUCCESS, stack);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip,
                              TooltipContext context) {
        int size = stack.hasTag() && stack.getTag().contains("Size") ? stack.getTag().getInt("Size") : 0;
        switch (size) {
            case 1:
                tooltip.add(new TranslatableText("item.raut.backpack.tooltip.medium"));
                break;
            case 2:
                tooltip.add(new TranslatableText("item.raut.backpack.tooltip.large"));
                break;
            default:
                tooltip.add(new TranslatableText("item.raut.backpack.tooltip.small"));
                break;
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void render(String slot, MatrixStack matrixStack, VertexConsumerProvider vertexConsumer, int light, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw, float headPitch) {
        ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
        ItemStack stack = new ItemStack(this);
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
            matrixStack.translate(0, 0, 0.05);
        }
        matrixStack.translate(0, 0.2, 0.3);
        itemRenderer.renderItem(stack, ModelTransformation.Mode.FIXED, light, 0, matrixStack, vertexConsumer);
        matrixStack.translate(0, -0.2, -0.3);
        if (hasChestplate) {
            matrixStack.translate(0, 0, -0.05);
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
