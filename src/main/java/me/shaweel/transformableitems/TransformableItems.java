package me.shaweel.transformableitems;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = "transformableitems", dist = Dist.CLIENT)
public class TransformableItems {
	public TransformableItems(ModContainer container) {
		ConfigFile.load();

		container.registerExtensionPoint(IConfigScreenFactory.class, (_, _) -> new ConfigScreen());
	}
}
