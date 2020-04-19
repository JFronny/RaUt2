package com.jfronny.raut.api;

import com.google.common.collect.Multimap;
import com.jfronny.raut.api.AttributeArmorMat;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import org.apache.commons.lang3.tuple.Triple;

import java.util.HashMap;
import java.util.UUID;

public class BaseArmor extends ArmorItem {
    private static final UUID[] MODIFIERS = new UUID[] { UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"),
            UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"),
            UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"),
            UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150") };
    HashMap<EquipmentSlot, HashMap<EntityAttribute, Triple<String, Double, EntityAttributeModifier.Operation>>> attributes;
    public BaseArmor(AttributeArmorMat material, EquipmentSlot slot) {
        super(material, slot, new Item.Settings().group(ItemGroup.COMBAT));
        this.attributes = material.getAttributes();
    }

    @Override
    public Multimap<String, EntityAttributeModifier> getModifiers(EquipmentSlot slot) {
        Multimap<String, EntityAttributeModifier> map = super.getModifiers(slot);
        if (this.slot == slot && attributes.containsKey(this.slot)){
            HashMap<EntityAttribute, Triple<String, Double, EntityAttributeModifier.Operation>> tmp = attributes.get(slot);
            for (EntityAttribute attribute : tmp.keySet()) {
                Triple<String, Double, EntityAttributeModifier.Operation> mod = tmp.get(attribute);
                map.put(attribute.getId(), new EntityAttributeModifier(MODIFIERS[slot.getEntitySlotId()], mod.getLeft(), mod.getMiddle(), mod.getRight()));
            }
        }
        return map;
    }
}
