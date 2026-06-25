package me.shaweel.transformableitems;

import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.ConfigScreenHandler.ConfigScreenFactory;

@Mod(value = "transformableitems")
public class TransformableItems {
	public TransformableItems(ModContainer container) {
		ConfigFile.load();

		container.registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((client, parent) -> new ConfigScreen()));
	}
}
