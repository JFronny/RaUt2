package com.jfronny.raut.modules;

import com.jfronny.raut.RaUt;
import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.api.DepRegistry;
import com.jfronny.raut.gui.BackpackController;
import com.jfronny.raut.gui.BackpackScreen;
import com.jfronny.raut.mixin.interfacing.ItemGroupExtension;
import com.jfronny.raut.trinket.AngelRing;
import com.jfronny.raut.trinket.BackpackTrinket;
import com.jfronny.raut.trinket.BuilderRing;
import com.jfronny.raut.trinket.TravellersRing;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketSlots;
import dev.emi.trinkets.api.TrinketsApi;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;
import org.lwjgl.glfw.GLFW;

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
        DepRegistry.registerItem("traveller_ring", cfg.trinkets, new TravellersRing());
        DepRegistry.registerItem("angel_ring", cfg.trinkets, new AngelRing());
        DepRegistry.registerItem("builders_ring", cfg.trinkets, new BuilderRing());
        DepRegistry.registerItem("backpack", cfg.trinkets, backpack);
        if (cfg.trinkets) {
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

    @Override
    public void InitClient() {
        ScreenProviderRegistry.INSTANCE.registerFactory(new Identifier(RaUt.MOD_ID, "backpack"), (syncId, identifier, player, buf) -> new BackpackScreen(new BackpackController(syncId, player.inventory, buf.readItemStack(), buf.readInt(), buf.readInt()), player));
        if (cfg.trinkets) {
            FabricKeyBinding backpackKeyBinding = FabricKeyBinding.Builder.create(
                    new Identifier(RaUt.MOD_ID, "open_backpack"),
                    InputUtil.Type.KEYSYM,
                    GLFW.GLFW_KEY_B,
                    "key.categories.inventory"
            ).build();
            ClientTickCallback.EVENT.register(e ->
            {
                if (backpackKeyBinding.isPressed()) {
                    Inventory component = TrinketsApi.getTrinketsInventory(e.player);
                    Set<Item> backpackSet = new HashSet<>();
                    backpackSet.add(backpack);
                    if (component.containsAnyInInv(backpackSet)) {
                        int size = component.getInvSize();
                        for (int i = 0; i < size; i++) {
                            if (component.getInvStack(i).getItem() == backpack) {
                                PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
                                passedData.writeItemStack(component.getInvStack(i));
                                ClientSidePacketRegistry.INSTANCE.sendToServer(OPEN_BACKPACK_PACKET_ID, passedData);
                                return;
                            }
                        }
                    }
                }
            });
            KeyBindingRegistry.INSTANCE.register(backpackKeyBinding);
            ItemGroupExtension MISC = (ItemGroupExtension) ItemGroup.MISC;
            ItemStack backpack_1 = new ItemStack(backpack);
            if (!backpack_1.hasTag())
                backpack_1.setTag(new CompoundTag());
            backpack_1.getTag().putInt("Size", 1);
            MISC.addStack(backpack_1);
            ItemStack backpack_2 = new ItemStack(backpack);
            if (!backpack_2.hasTag())
                backpack_2.setTag(new CompoundTag());
            backpack_2.getTag().putInt("Size", 2);
            MISC.addStack(backpack_2);
        }
    }
}
