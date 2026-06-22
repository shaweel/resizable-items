package me.shaweel.transformableitems;

import java.util.function.Function;

import io.github.prospector.modmenu.api.ModMenuApi;

import net.minecraft.client.gui.screens.Screen;

public class ModMenuIntegration implements ModMenuApi {
	@Override
	public String getModId() { return "transformableitems"; }
	
	@Override
	public Function<Screen, ? extends Screen> getConfigScreenFactory() {
		return parent -> new ConfigScreen();
	}
}