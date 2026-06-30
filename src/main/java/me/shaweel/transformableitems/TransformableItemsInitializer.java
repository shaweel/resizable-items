package me.shaweel.transformableitems;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

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
