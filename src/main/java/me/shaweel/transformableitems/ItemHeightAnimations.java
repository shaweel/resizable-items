package me.shaweel.transformableitems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.ItemRenderer;

import java.lang.reflect.Field;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ItemHeightAnimations {
	@SubscribeEvent
	public void clientTick(TickEvent.ClientTickEvent event) {
		if (event.phase != TickEvent.Phase.END || ConfigFile.configData.itemHeightAnimations) return;
		ItemRenderer itemRenderer = Minecraft.getMinecraft().entityRenderer.itemRenderer;
		try {
			Field equippedProgress = ItemRenderer.class.getDeclaredField("field_78454_c");
			Field prevEquippedProgress = ItemRenderer.class.getDeclaredField("field_78451_d");
			equippedProgress.setAccessible(true);
			prevEquippedProgress.setAccessible(true);
			equippedProgress.set(itemRenderer, 1f);
			prevEquippedProgress.set(itemRenderer, 1f);

			if (Minecraft.getMinecraft().thePlayer == null) return;
			Field itemToRender = ItemRenderer.class.getDeclaredField("field_78453_b");
			itemToRender.setAccessible(true);
			itemToRender.set(itemRenderer, Minecraft.getMinecraft().thePlayer.getHeldItem());
		} catch (Exception e) {
			System.out.println("Failed to disable Item Height Animations");
			e.printStackTrace();
		}
	}
}
