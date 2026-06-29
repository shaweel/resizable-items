package me.shaweel.transformableitems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ItemHeightAnimations {
	@SubscribeEvent
	public static void clientTick(TickEvent.ClientTickEvent event) {
		if (event.phase != TickEvent.Phase.END || ConfigFile.configData.itemHeightAnimations) return;
		ItemRenderer itemRenderer = Minecraft.getMinecraft().entityRenderer.itemRenderer;
		itemRenderer.equippedProgressMainHand = 1f;
		itemRenderer.equippedProgressOffHand = 1f;
		itemRenderer.prevEquippedProgressMainHand = 1f;
		itemRenderer.prevEquippedProgressOffHand = 1f;
		if (Minecraft.getMinecraft().player == null) return;
		itemRenderer.itemStackMainHand = Minecraft.getMinecraft().player.getHeldItemMainhand();
		itemRenderer.itemStackOffHand = Minecraft.getMinecraft().player.getHeldItemOffhand();
	}
}
