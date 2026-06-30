package me.shaweel.transformableitems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ItemHeightAnimations {
	@SubscribeEvent
	public void clientTick(TickEvent.ClientTickEvent event) {
		if (event.phase != TickEvent.Phase.END || ConfigFile.configData.itemHeightAnimations) return;
		ItemRenderer itemRenderer = Minecraft.getMinecraft().entityRenderer.itemRenderer;
		itemRenderer.equippedProgress = 1f;
		itemRenderer.prevEquippedProgress = 1f;
		if (Minecraft.getMinecraft().thePlayer == null) return;
		itemRenderer.itemToRender = Minecraft.getMinecraft().thePlayer.getHeldItem();
	}
}
