package com.jfronny.raut.armor;

import com.jfronny.raut.RaUt;
import com.jfronny.raut.api.AttributeArmorMat;
import com.jfronny.raut.modules.SteelModule;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.apache.commons.lang3.tuple.Triple;

import java.util.HashMap;

public class SteelArmorMat implements AttributeArmorMat {
    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private static final int[] PROTECTION_AMOUNTS = new int[]{2, 6, 7, 2};

    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * 60;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_AMOUNTS[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(SteelModule.STEEL_INGOT);
    }

    @Override
    public String getName() {
        return "steel";
    }

    @Override
    public float getToughness() {
        return 1;
    }

    @Override
    public HashMap<EquipmentSlot, HashMap<EntityAttribute, Triple<String, Double, EntityAttributeModifier.Operation>>> getAttributes() {
        HashMap<EquipmentSlot, HashMap<EntityAttribute, Triple<String, Double, EntityAttributeModifier.Operation>>> map = new HashMap<>();
        return map;
    }
}
