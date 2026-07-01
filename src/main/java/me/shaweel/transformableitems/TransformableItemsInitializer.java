package me.shaweel.transformableitems;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;

import java.lang.reflect.Field;

@Mod(modid = "transformableitems", version = "1.1", name = "Transformable Items", guiFactory = "me.shaweel.transformableitems.ConfigScreenFactory")
public class TransformableItemsInitializer {
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		try {
			Field itemRenderer = EntityRenderer.class.getDeclaredField("field_78516_c");
			itemRenderer.setAccessible(true);
			itemRenderer.set(Minecraft.getMinecraft().entityRenderer, new CustomItemRenderer(Minecraft.getMinecraft()));
		} catch (Exception e) {
			System.out.println("failed to replace itemrenderer");
			e.printStackTrace();
		}
	}
	
	public TransformableItemsInitializer() {
		ConfigFile.load();
		FMLCommonHandler.instance().bus().register(new ModKeybinds());
		FMLCommonHandler.instance().bus().register(new ItemHeightAnimations());
		ModKeybinds.initialize();
	}
}
