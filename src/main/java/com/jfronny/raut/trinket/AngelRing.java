package com.jfronny.raut.trinket;

import com.jfronny.raut.RaUt;
import com.jfronny.raut.api.RingRenderer;
import dev.emi.trinkets.api.ITrinket;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import io.github.ladysnake.pal.AbilitySource;
import io.github.ladysnake.pal.Pal;
import io.github.ladysnake.pal.VanillaAbilities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
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

//I actually did this myself!
public class AngelRing extends Item implements ITrinket {
    private final AbilitySource abilitySource = Pal.getAbilitySource(RaUt.MOD_ID, "angel_ring");

    public AngelRing() {
        super(new Item.Settings().group(ItemGroup.TOOLS).maxCount(1));
        DispenserBlock.registerBehavior(this, TRINKET_DISPENSER_BEHAVIOR);
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> list, TooltipContext context) {
        list.add(new LiteralText("Flight").formatted(Formatting.GOLD));
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group.equals(SlotGroups.HAND) && slot.equals(Slots.RING);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        return ITrinket.equipTrinket(player, hand);
    }

    @Override
    public void onEquip(PlayerEntity player, ItemStack stack) {
        if (!player.world.isClient && !abilitySource.grants(player, VanillaAbilities.ALLOW_FLYING)) {
            abilitySource.grantTo(player, VanillaAbilities.ALLOW_FLYING);
        }
    }

    @Override
    public void onUnequip(PlayerEntity player, ItemStack stack) {
        if (!player.world.isClient && abilitySource.grants(player, VanillaAbilities.ALLOW_FLYING)) {
            abilitySource.revokeFrom(player, VanillaAbilities.ALLOW_FLYING);
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void render(String slot, MatrixStack matrixStack, VertexConsumerProvider vertexConsumer, int light, PlayerEntityModel<AbstractClientPlayerEntity> model, AbstractClientPlayerEntity player, float headYaw, float headPitch) {
        RingRenderer.Render(this, matrixStack, vertexConsumer, light, model, player, headYaw, headPitch);
    }
}
