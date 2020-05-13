package com.jfronny.raut.modules;

import com.jfronny.raut.RaUt;
import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.api.DepRegistry;
import com.jfronny.raut.gui.BackpackController;
import com.jfronny.raut.trinket.AngelRing;
import com.jfronny.raut.trinket.BackpackTrinket;
import com.jfronny.raut.trinket.BuilderRing;
import com.jfronny.raut.trinket.TravellersRing;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketSlots;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

import static com.jfronny.raut.RaUt.cfg;

public class TrinketsModule extends BaseModule {
    public static final Identifier OPEN_BACKPACK_PACKET_ID = new Identifier(RaUt.MOD_ID, "open_backpack");
    public static BackpackTrinket backpack = new BackpackTrinket();

    @Override
    public void Init() {
        TrinketSlots.addSlot(SlotGroups.HAND, Slots.RING, new Identifier("trinkets", "textures/item/empty_trinket_slot_ring.png"));
        TrinketSlots.addSlot(SlotGroups.CHEST, Slots.BACKPACK, new Identifier("trinkets", "textures/item/empty_trinket_slot_backpack.png"));
        DepRegistry.registerItem("traveller_ring", cfg.trinkets.enabled && cfg.trinkets.traveller_ring, new TravellersRing());
        DepRegistry.registerItem("angel_ring", cfg.trinkets.enabled && cfg.trinkets.angel_ring, new AngelRing());
        DepRegistry.registerItem("builders_ring", cfg.trinkets.enabled && cfg.trinkets.builders_ring, new BuilderRing());
        DepRegistry.registerItem("backpack", cfg.trinkets.enabled && cfg.trinkets.backpack, backpack);
        if (cfg.trinkets.backpack) {
            ContainerProviderRegistry.INSTANCE.registerFactory(new Identifier(RaUt.MOD_ID, "backpack"), (syncId, id, player, buf) -> new BackpackController(syncId, player.inventory, buf.readItemStack(), buf.readInt(), buf.readInt()));
            ServerSidePacketRegistry.INSTANCE.register(OPEN_BACKPACK_PACKET_ID, (packetContext, attachedData) -> {
                PlayerEntity player = packetContext.getPlayer();
                packetContext.getTaskQueue().execute(() -> {
                    Inventory component = TrinketsApi.getTrinketsInventory(player);
                    Set<Item> backpackSet = new HashSet<>();
                    backpackSet.add(backpack);
                    if (component.containsAnyInInv(backpackSet)) {
                        int size = component.getInvSize();
                        for (int i = 0; i < size; i++) {
                            if (component.getInvStack(i).getItem() == backpack) {
                                BackpackTrinket.openGUI(component.getInvStack(i), player);
                                return;
                            }
                        }
                    }
                });
            });
        }
    }
}
