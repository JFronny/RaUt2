package com.jfronny.raut.api;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

import static com.jfronny.raut.RaUt.MOD_ID;

public class DepRegistry {
    public static HashMap<String, Item> disabledItems = new HashMap<>();

    public static void registerItem(String ID, Boolean dep, Item item) {
        if (!dep) {
            Item nullIt = new DisabledItem();
            disabledItems.put(ID, nullIt);
            Registry.register(Registry.ITEM, new Identifier(MOD_ID, ID), nullIt);
        } else {
            try {
                Registry.register(Registry.ITEM, new Identifier(MOD_ID, ID), item);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void registerBlock(String ID, Boolean dep, Block block) {
        registerBlock(ID, dep, block, ItemGroup.BUILDING_BLOCKS);
    }

    public static void registerBlock(String ID, Boolean dep, Block block, ItemGroup group) {
        registerBlock(ID, dep, block, new BlockItem(block, new Item.Settings().group(group)));
    }

    public static void registerBlock(String ID, Boolean dep, Block block, BlockItem item) {
        try {
            Registry.register(Registry.BLOCK, new Identifier(MOD_ID, ID), dep ? block : new Block(FabricBlockSettings.of(Material.AIR).build()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        registerItem(ID, dep, item);
    }

    public static void registerTools(String IDPrefix, Boolean dep, FactorToolMat mat) {
        registerItem(IDPrefix + "_axe", dep, new BaseAxe(mat));
        registerItem(IDPrefix + "_hoe", dep, new BaseHoe(mat));
        registerItem(IDPrefix + "_pickaxe", dep, new BasePickaxe(mat));
        registerItem(IDPrefix + "_shovel", dep, new BaseShovel(mat));
        registerItem(IDPrefix + "_sword", dep, new BaseSword(mat));
        registerItem(IDPrefix + "_paxel", dep, new BasePaxel(mat));
    }

    public static void registerArmor(String IDPrefix, Boolean dep, AttributeArmorMat mat) {
        registerItem(IDPrefix + "_helmet", dep, new BaseArmor(mat, EquipmentSlot.HEAD));
        registerItem(IDPrefix + "_chestplate", dep, new BaseArmor(mat, EquipmentSlot.CHEST));
        registerItem(IDPrefix + "_leggings", dep, new BaseArmor(mat, EquipmentSlot.LEGS));
        registerItem(IDPrefix + "_boots", dep, new BaseArmor(mat, EquipmentSlot.FEET));
    }

    public static class DisabledItem extends Item {
        public DisabledItem() {
            super(new Item.Settings());
        }

        @Override
        public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
            tooltip.add(new TranslatableText("status.raut.disabled"));
        }
    }
}
