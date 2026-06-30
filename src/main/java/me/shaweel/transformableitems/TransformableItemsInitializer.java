package me.shaweel.transformableitems;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.client.Minecraft;

@Mod(modid = "transformableitems", version = "1.1", name = "Transformable Items", guiFactory = "me.shaweel.transformableitems.ConfigScreenFactory")
public class TransformableItemsInitializer {
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		Minecraft.getMinecraft().entityRenderer.itemRenderer = new CustomItemRenderer(Minecraft.getMinecraft());
	}
	
	public TransformableItemsInitializer() {
		ConfigFile.load();
		FMLCommonHandler.instance().bus().register(new ModKeybinds());
		FMLCommonHandler.instance().bus().register(new ItemHeightAnimations());
		ModKeybinds.initialize();
	}
}
