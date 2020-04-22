package com.jfronny.raut;

import com.jfronny.raut.api.BaseModule;
import net.fabricmc.api.ClientModInitializer;

public class RaUtClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (BaseModule module : RaUt.modules) {
            module.InitClient();
        }
    }
}
