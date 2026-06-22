package me.shaweel.transformableitems;

import net.fabricmc.api.ClientModInitializer;

public class TransformableItemsInitializer implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ConfigFile.load();
	}
}
