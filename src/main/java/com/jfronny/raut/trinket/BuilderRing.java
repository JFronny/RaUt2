package com.jfronny.raut.trinket;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import com.jfronny.raut.api.RingRenderer;
import dev.emi.trinkets.api.ITrinket;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

//some code from github.com/emilyploszaj/bunch-o-trinkets
public class BuilderRing extends Item implements ITrinket {
    public BuilderRing() {
        super(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));
        DispenserBlock.registerBehavior(this, TRINKET_DISPENSER_BEHAVIOR);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        return ITrinket.equipTrinket(player, hand);
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> list, TooltipContext context) {
        list.add(new LiteralText("Farther reach").formatted(Formatting.GOLD));
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group.equals(SlotGroups.HAND) && slot.equals(Slots.RING);
    }

    @Override
    public Multimap<String, EntityAttributeModifier> getTrinketModifiers(String group, String slot, UUID uuid, ItemStack stack) {
        Multimap<String, EntityAttributeModifier> map = HashMultimap.create();
        if (slot.equals(Slots.RING)) {
            map.put(ReachEntityAttributes.REACH.getId(), new EntityAttributeModifier(uuid, "Reach", 1.5F, EntityAttributeModifier.Operation.ADDITION));
        }
        return map;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void render(String slot, MatrixStack matrixStack, VertexConsumerProvider vertexConsumer, int light, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw, float headPitch) {
        RingRenderer.Render(this, matrixStack, vertexConsumer, light, model, player, headYaw, headPitch);
    }
}
