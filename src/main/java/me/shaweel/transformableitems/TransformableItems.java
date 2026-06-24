package me.shaweel.transformableitems;

import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod("transformableitems")
public class TransformableItems {
	public TransformableItems() {
		ConfigFile.load();
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (client, parent) -> new ConfigScreen());
	}
}