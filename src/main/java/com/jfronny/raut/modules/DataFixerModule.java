package com.jfronny.raut.modules;

import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.api.DepRegistry;
import io.github.cottonmc.cotton.datapack.recipe.RecipeUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import static com.jfronny.raut.RaUt.logger;

public class DataFixerModule extends BaseModule {
    @Override
    public void Init() {
        for (Item item : DepRegistry.disabledItems.values()) {
            logger.devInfo("Block: " + item.toString());
            ItemStack stack = new ItemStack(item);
            RecipeUtil.removeRecipeFor(stack);
        }
    }
}
