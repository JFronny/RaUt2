package com.jfronny.raut.modules;

import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.api.DepRegistry;
import com.jfronny.raut.items.DebugClear;
import net.minecraft.item.Item;

import static com.jfronny.raut.RaUt.cfg;

public class DebugModule extends BaseModule {
    public static final Item DEBUG_CLEAR = new DebugClear();

    @Override
    public void Init() {
        DepRegistry.registerItem("debug_clear", cfg.debug.enabled && cfg.debug.debugClear, DEBUG_CLEAR);
    }
}
