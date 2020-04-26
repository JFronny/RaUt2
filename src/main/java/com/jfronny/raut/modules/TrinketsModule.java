package com.jfronny.raut.modules;

import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.api.DepRegistry;
import com.jfronny.raut.trinket.AngelRing;
import com.jfronny.raut.trinket.BuilderRing;
import com.jfronny.raut.trinket.TravellersRing;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketSlots;
import net.minecraft.util.Identifier;

import static com.jfronny.raut.RaUt.cfg;

public class TrinketsModule extends BaseModule {
    @Override
    public void Init() {
        TrinketSlots.addSlot(SlotGroups.HAND, Slots.RING, new Identifier("trinkets", "textures/item/empty_trinket_slot_ring.png"));
        DepRegistry.registerItem("traveller_ring", cfg.trinkets, new TravellersRing());
        DepRegistry.registerItem("angel_ring", cfg.trinkets, new AngelRing());
        DepRegistry.registerItem("builders_ring", cfg.trinkets, new BuilderRing());
    }
}
