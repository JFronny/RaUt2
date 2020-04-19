package com.jfronny.raut.client;

import com.jfronny.raut.RaUt;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class RaUtClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(RaUt.COTTON_CROP, RenderLayer.getCutout());
    }
}
