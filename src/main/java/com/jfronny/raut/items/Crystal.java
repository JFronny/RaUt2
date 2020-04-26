package com.jfronny.raut.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Crystal extends Item {
    private static final List<StatusEffect> positiveEffects;
    private static final List<StatusEffect> negativeEffects;
    private static final Random rnd;

    static {
        positiveEffects = new ArrayList<>();
        negativeEffects = new ArrayList<>();
        rnd = new Random();
        for (Field field : StatusEffects.class.getFields()) {
            if (StatusEffect.class.equals(field.getType())) {
                Object effect = null;
                try {
                    effect = field.get(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (effect instanceof StatusEffect) {
                    StatusEffect tmp = (StatusEffect) effect;
                    if (tmp.isBeneficial())
                        positiveEffects.add((StatusEffect) effect);
                    else
                        negativeEffects.add((StatusEffect) effect);
                }
            }
        }
    }

    public Crystal() {
        super(new Item.Settings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(1).saturationModifier(0.1f).alwaysEdible().build()));
    }

    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        if (!world.isClient) {
            if (!(user.getActiveStatusEffects().keySet().containsAll(positiveEffects)
                    && user.getActiveStatusEffects().keySet().containsAll(positiveEffects))) {
                List<StatusEffect> effects = !user.getActiveStatusEffects().keySet().containsAll(negativeEffects)
                        && (user.getActiveStatusEffects().keySet().containsAll(positiveEffects)
                        || rnd.nextInt(5) == 0) ? negativeEffects : positiveEffects;
                if (!user.getActiveStatusEffects().keySet().containsAll(effects)) {
                    List<StatusEffect> eff = new ArrayList<>(effects);
                    eff.removeAll(user.getActiveStatusEffects().keySet());
                    StatusEffect effect = eff.get(rnd.nextInt(eff.size()));
                    if (effect.isInstant())
                        effect.applyInstantEffect(user, user, user, 1, 1);
                    else
                        user.addStatusEffect(new StatusEffectInstance(effect, 1200, 1));
                }
            }
            if (user instanceof PlayerEntity) {
                ((PlayerEntity) user).getItemCooldownManager().set(this, 10);
            }
        }
        return itemStack;
    }
}
