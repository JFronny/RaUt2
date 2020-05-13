package com.jfronny.raut;

import com.jfronny.raut.api.BaseModule;
import com.jfronny.raut.client_modules.TrinketsClient;
import net.fabricmc.api.ClientModInitializer;

import java.util.ArrayList;

public class RaUtClient implements ClientModInitializer {
    public static final ArrayList<BaseModule> modules = new ArrayList<>();

    @Override
    public void onInitializeClient() {
        modules.addAll(RaUt.modules);
        modules.add(new TrinketsClient());
        for (BaseModule module : modules) {
            module.InitClient();
        }
    }
}
