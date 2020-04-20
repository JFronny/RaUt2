package com.jfronny.raut;

import com.jfronny.raut.RaUt;
import com.jfronny.raut.api.BaseModule;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class RaUtClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        for (BaseModule module : RaUt.modules) {
            module.InitClient();
        }
    }
}
