package com.jfronny.raut.integration;

import com.jfronny.raut.Cfg;
import com.jfronny.raut.RaUt;
import io.github.prospector.modmenu.api.ModMenuApi;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class ModMenu implements ModMenuApi {
    @Override
    public String getModId() {
        return RaUt.MOD_ID;
    }

    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return screen -> AutoConfig.getConfigScreen(Cfg.class, screen).get();
    }
}