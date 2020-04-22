package com.jfronny.raut.modules;

import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.api.DepRegistry;
import com.jfronny.raut.trinket.AngelRing;
import com.jfronny.raut.trinket.BuilderRing;
import com.jfronny.raut.trinket.TravellersRing;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketSlots;
import io.github.cottonmc.cotton.datapack.recipe.RecipeUtil;
import net.minecraft.util.Identifier;

import static com.jfronny.raut.RaUt.config;
import static com.jfronny.raut.RaUt.logger;

public class TrinketsModule extends BaseModule {
    @Override
    public void Init() {
        TrinketSlots.addSlot(SlotGroups.HAND, Slots.RING, new Identifier("trinkets", "textures/item/empty_trinket_slot_ring.png"));
        DepRegistry.registerItem("traveller_ring", config.trinkets, TravellersRing::new);
        DepRegistry.registerItem("angel_ring", config.trinkets, AngelRing::new);
        DepRegistry.registerItem("builders_ring", config.trinkets, BuilderRing::new);
        if (config.trinkets) {
            if (config.aquilorite) {
                logger.devInfo("unreg vanilla trinkets");
                RecipeUtil.removeRecipe("raut:angel_ring_vn");
                RecipeUtil.removeRecipe("raut:traveller_ring_vn");
                RecipeUtil.removeRecipe("raut:builders_ring_vn");
            } else {
                logger.devInfo("unreg mod trinkets");
                RecipeUtil.removeRecipe("raut:angel_ring_aq");
                RecipeUtil.removeRecipe("raut:traveller_ring_aq");
                RecipeUtil.removeRecipe("raut:builders_ring_aq");
            }
        } else {
            logger.devInfo("unreg all trinkets");
            RecipeUtil.removeRecipe("raut:angel_ring_vn");
            RecipeUtil.removeRecipe("raut:angel_ring_aq");
            RecipeUtil.removeRecipe("raut:traveller_ring_vn");
            RecipeUtil.removeRecipe("raut:traveller_ring_aq");
            RecipeUtil.removeRecipe("raut:builders_ring_vn");
            RecipeUtil.removeRecipe("raut:builders_ring_aq");
        }
    }
}
