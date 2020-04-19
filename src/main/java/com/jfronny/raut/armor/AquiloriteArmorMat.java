package com.jfronny.raut.armor;

import com.jfronny.raut.RaUt;
import com.jfronny.raut.api.AttributeArmorMat;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import org.apache.commons.lang3.tuple.Triple;

import java.util.HashMap;

public class AquiloriteArmorMat implements AttributeArmorMat {
    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
    private static final int[] PROTECTION_AMOUNTS = new int[]{3, 6, 8, 3};
    @Override
    public int getDurability(EquipmentSlot slot) {
        return BASE_DURABILITY[slot.getEntitySlotId()] * 40;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return PROTECTION_AMOUNTS[slot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 30;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(RaUt.AQUILORITE_GEM);
    }

    @Override
    public String getName() {
        return "aquilorite";
    }

    @Override
    public float getToughness() {
        return 2.5f;
    }

    @Override
    public HashMap<EquipmentSlot, HashMap<EntityAttribute, Triple<String, Double, EntityAttributeModifier.Operation>>> getAttributes() {
        HashMap<EquipmentSlot, HashMap<EntityAttribute, Triple<String, Double, EntityAttributeModifier.Operation>>> map = new HashMap<>();
        HashMap<EntityAttribute, Triple<String, Double, EntityAttributeModifier.Operation>> legs = new HashMap<>();
        legs.put(EntityAttributes.MOVEMENT_SPEED, new Triple<String, Double, EntityAttributeModifier.Operation>() {
            @Override public String getLeft() { return "Movement Speed"; }
            @Override public Double getMiddle() { return 0.05; }
            @Override public EntityAttributeModifier.Operation getRight() { return EntityAttributeModifier.Operation.ADDITION; }
        });
        legs.put(EntityAttributes.FLYING_SPEED, new Triple<String, Double, EntityAttributeModifier.Operation>() {
            @Override public String getLeft() { return "Flying Speed"; }
            @Override public Double getMiddle() { return 0.01; }
            @Override public EntityAttributeModifier.Operation getRight() { return EntityAttributeModifier.Operation.ADDITION; }
        });
        map.put(EquipmentSlot.LEGS, legs);
        HashMap<EntityAttribute, Triple<String, Double, EntityAttributeModifier.Operation>> chest = new HashMap<>();
        chest.put(EntityAttributes.ARMOR_TOUGHNESS, new Triple<String, Double, EntityAttributeModifier.Operation>() {
            @Override public String getLeft() { return "Resistance"; }
            @Override public Double getMiddle() { return 5d; }
            @Override public EntityAttributeModifier.Operation getRight() { return EntityAttributeModifier.Operation.ADDITION; }
        });
        map.put(EquipmentSlot.CHEST, chest);
        HashMap<EntityAttribute, Triple<String, Double, EntityAttributeModifier.Operation>> feet = new HashMap<>();
        feet.put(EntityAttributes.KNOCKBACK_RESISTANCE, new Triple<String, Double, EntityAttributeModifier.Operation>() {
            @Override public String getLeft() { return "Knockback Resistance"; }
            @Override public Double getMiddle() { return 5d; }
            @Override public EntityAttributeModifier.Operation getRight() { return EntityAttributeModifier.Operation.ADDITION; }
        });
        map.put(EquipmentSlot.FEET, feet);
        HashMap<EntityAttribute, Triple<String, Double, EntityAttributeModifier.Operation>> head = new HashMap<>();
        head.put(EntityAttributes.LUCK, new Triple<String, Double, EntityAttributeModifier.Operation>() {
            @Override public String getLeft() { return "Luck"; }
            @Override public Double getMiddle() { return 2d; }
            @Override public EntityAttributeModifier.Operation getRight() { return EntityAttributeModifier.Operation.ADDITION; }
        });
        head.put(EntityAttributes.ATTACK_SPEED, new Triple<String, Double, EntityAttributeModifier.Operation>() {
            @Override public String getLeft() { return "Attack Speed"; }
            @Override public Double getMiddle() { return 0.1; }
            @Override public EntityAttributeModifier.Operation getRight() { return EntityAttributeModifier.Operation.ADDITION; }
        });
        map.put(EquipmentSlot.HEAD, head);
        return map;
    }
}
