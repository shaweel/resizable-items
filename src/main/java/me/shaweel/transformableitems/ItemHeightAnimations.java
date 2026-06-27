package me.shaweel.transformableitems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FirstPersonRenderer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ItemHeightAnimations {
	@SubscribeEvent
	public static void clientTick(TickEvent.ClientTickEvent event) {
		if (event.phase != TickEvent.Phase.END || ConfigFile.configData.itemHeightAnimations) return;

		FirstPersonRenderer firstPersonRenderer = Minecraft.getInstance().getFirstPersonRenderer();
		firstPersonRenderer.equippedProgressMainHand = 1f;
		firstPersonRenderer.equippedProgressOffHand = 1f;
		firstPersonRenderer.prevEquippedProgressMainHand = 1f;
		firstPersonRenderer.prevEquippedProgressOffHand = 1f;
		if (Minecraft.getInstance().player == null) return;
		firstPersonRenderer.itemStackMainHand = Minecraft.getInstance().player.getHeldItemMainhand();
		firstPersonRenderer.itemStackOffHand = Minecraft.getInstance().player.getHeldItemOffhand();
	}
}
