package me.shaweel.transformableitems;

import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod("transformableitems")
public class TransformableItems {
	public TransformableItems() {
		ConfigFile.load();
		ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((client, parent) -> new ConfigScreen()));
	}
}