package me.shaweel.transformableitems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ItemHeightAnimations {
	@SubscribeEvent
	public void clientTick(TickEvent.ClientTickEvent event) {
		if (event.phase != TickEvent.Phase.END || ConfigFile.configData.itemHeightAnimations) return;
		ItemRenderer itemRenderer = Minecraft.getMinecraft().entityRenderer.itemRenderer;
		itemRenderer.equippedProgressMainHand = 1f;
		itemRenderer.equippedProgressOffHand = 1f;
		itemRenderer.prevEquippedProgressMainHand = 1f;
		itemRenderer.prevEquippedProgressOffHand = 1f;
		if (Minecraft.getMinecraft().thePlayer == null) return;
		itemRenderer.itemStackMainHand = Minecraft.getMinecraft().thePlayer.getHeldItemMainhand();
		itemRenderer.itemStackOffHand = Minecraft.getMinecraft().thePlayer.getHeldItemOffhand();
	}
}
