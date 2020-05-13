package com.jfronny.raut.items;

import com.jfronny.raut.modules.AquiloriteModule;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class AquiloriteGun extends RangedWeaponItem {
    public AquiloriteGun() {
        super(new Item.Settings().group(ItemGroup.COMBAT).maxDamage(2000));
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return stack -> stack.getItem().equals(AquiloriteModule.EXPLOSIVE_SHARD);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        ItemStack arrows = user.getArrowType(stack);
        boolean inf = user.abilities.creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
        if (!arrows.isEmpty() || inf) {
            if (arrows.isEmpty())
                arrows = new ItemStack(AquiloriteModule.EXPLOSIVE_SHARD);

            if (!world.isClient) {
                ArrowItem arrowItem = (ArrowItem) (arrows.getItem() instanceof ArrowItem ? arrows.getItem() : AquiloriteModule.EXPLOSIVE_SHARD);
                ProjectileEntity projectileEntity = arrowItem.createArrow(world, arrows, user);
                projectileEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 3.0F, 1.0F);

                //int j = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                //if (j > 0) {
                //    projectileEntity.setDamage(projectileEntity.getDamage() + (double)j * 0.5D + 0.5D);
                //}
                projectileEntity.setDamage(projectileEntity.getDamage() * 2);

                //if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                //    projectileEntity.setOnFireFor(100);
                //}

                stack.setDamage(stack.getDamage() - 1);
                projectileEntity.pickupType = ProjectileEntity.PickupPermission.CREATIVE_ONLY;
                world.spawnEntity(projectileEntity);
            }

            //world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (RANDOM.nextFloat() * 0.4F + 1.2F) + 0.5F);
            world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.PLAYERS, 1.0F, 1.0F / (RANDOM.nextFloat() * 0.4F + 1.2F) + 0.5F);

            if (!inf && !user.abilities.creativeMode) {
                arrows.decrement(1);
                if (arrows.isEmpty()) {
                    user.inventory.removeOne(arrows);
                }
            }
        }
        return TypedActionResult.pass(stack);
    }
}
