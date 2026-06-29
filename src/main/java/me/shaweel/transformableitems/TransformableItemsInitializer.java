package me.shaweel.transformableitems;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = "transformableitems", version = "1.1", guiFactory = "me.shaweel.transformableitems.ConfigScreenFactory")
public class TransformableItemsInitializer {
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		Minecraft.getMinecraft().entityRenderer.itemRenderer = new CustomItemRenderer(Minecraft.getMinecraft());
	}
	
	public TransformableItemsInitializer() {
		ConfigFile.load();
		MinecraftForge.EVENT_BUS.register(ModKeybinds.class);
		MinecraftForge.EVENT_BUS.register(ItemHeightAnimations.class);
		ModKeybinds.initialize();
	}
}