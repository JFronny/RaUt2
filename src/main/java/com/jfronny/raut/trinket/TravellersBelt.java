package com.jfronny.raut.trinket;

/*import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.ITrinket;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.DispenserBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
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

public class TravellersBelt extends Item implements ITrinket {
    public TravellersBelt() {
        super(new Item.Settings().group(ItemGroup.TOOLS));
        DispenserBlock.registerBehavior(this, TRINKET_DISPENSER_BEHAVIOR);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        return ITrinket.equipTrinket(player, hand);
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> list, TooltipContext context) {
        list.add(new LiteralText("Swifter movement").formatted(Formatting.GOLD));
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group.equals(SlotGroups.HAND) && slot.equals(Slots.RING);
    }

    @Override
    public Multimap<String, EntityAttributeModifier> getTrinketModifiers(String group, String slot, UUID uuid, ItemStack stack) {
        Multimap<String, EntityAttributeModifier> map = HashMultimap.create();
        //map.put(StepHeightEntityAttributeMain.STEP_HEIGHT.getId(), new EntityAttributeModifier(uuid, "Step Height", 0.5F, EntityAttributeModifier.Operation.ADDITION));
        map.put(EntityAttributes.MOVEMENT_SPEED.getId(), new EntityAttributeModifier(uuid, "Movement Speed", 0.05F, EntityAttributeModifier.Operation.ADDITION));
        return map;
    }
}
*/