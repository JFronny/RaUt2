package com.jfronny.raut.api;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ArmorMaterial;
import org.apache.commons.lang3.tuple.Triple;

import java.util.HashMap;

public interface AttributeArmorMat extends ArmorMaterial {
    public HashMap<EquipmentSlot, HashMap<EntityAttribute, Triple<String, Double, EntityAttributeModifier.Operation>>> getAttributes();
}
