package me.shaweel.transformableitems;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fmlclient.ConfigGuiHandler.ConfigGuiFactory;

@Mod("transformableitems")
public class TransformableItems {
	public TransformableItems() {
		ConfigFile.load();
		ModLoadingContext.get().registerExtensionPoint(ConfigGuiFactory.class, () -> new ConfigGuiFactory((client, parent) -> new ConfigScreen()));
	}
}