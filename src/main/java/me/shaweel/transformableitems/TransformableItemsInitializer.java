package me.shaweel.transformableitems;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "transformableitems", guiFactory = "me.shaweel.transformableitems.ConfigScreenFactory")
public class TransformableItemsInitializer {
	public TransformableItemsInitializer() {
		System.out.println(net.minecraft.client.resources.I18n.format("key.categories.transformableitems"));
		ConfigFile.load();
		MinecraftForge.EVENT_BUS.register(ModKeybinds.class);
		MinecraftForge.EVENT_BUS.register(ItemHeightAnimations.class);
		MinecraftForge.EVENT_BUS.register(ModelBake.class);
		ModKeybinds.initialize();
	}
}