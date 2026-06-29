package me.shaweel.transformableitems;

import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "transformableitems", guiFactory = "me.shaweel.transformableitems.ConfigScreenFactory")
public class TransformableItemsInitializer {
	public TransformableItemsInitializer() {
		ConfigFile.load();

		try {
			Field field = Minecraft.class.getDeclaredField("itemRenderer");
			field.setAccessible(true);
			field.set(Minecraft.getMinecraft(), new CustomItemRenderer(Minecraft.getMinecraft()));
		} catch (Exception e) {
			System.err.println("Failed to replace ItemRenderer:");
			e.printStackTrace();
		}

		MinecraftForge.EVENT_BUS.register(ModKeybinds.class);
		MinecraftForge.EVENT_BUS.register(ItemHeightAnimations.class);
		ModKeybinds.initialize();
	}
}