package com.jfronny.raut.integration;

import com.jfronny.raut.RaUt;
import com.jfronny.raut.api.DepRegistry;
import me.shedaniel.rei.api.EntryRegistry;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import net.fabricmc.loader.api.SemanticVersion;
import net.fabricmc.loader.util.version.VersionParsingException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.List;

import static com.jfronny.raut.RaUt.MOD_ID;
import static com.jfronny.raut.RaUt.cfg;

public class REICompat implements REIPluginV0 {
    @Override
    public Identifier getPluginIdentifier() {
        return new Identifier(MOD_ID, "rei_compat");
    }

    @Override
    public void registerEntries(EntryRegistry entryRegistry) {
        if (cfg.reiCompat.enabled) {
            RaUt.logger.info("Configuring resource visibility");
            recheckItemHiding(entryRegistry.getStacksList());
        }
    }

    public void recheckItemHiding(List<EntryStack> list) {
        for (Item disabledItem : DepRegistry.disabledItems.values()) {
            for (int i = 0; i < list.size(); i++) {
                EntryStack entry = list.get(i);

                if (entry.getType() == EntryStack.Type.ITEM) {
                    ItemStack listItem = entry.getItemStack();
                    if (listItem.getItem() == disabledItem) {
                        list.remove(i);
                    }
                }
            }
        }
    }

    @Override
    public SemanticVersion getMinimumVersion() throws VersionParsingException {
        return SemanticVersion.parse("3.0-pre");
    }
}
