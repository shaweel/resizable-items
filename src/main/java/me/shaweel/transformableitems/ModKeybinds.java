package me.shaweel.transformableitems;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;

public class ModKeybinds {
	public static KeyBinding OPEN_CONFIG_KEYBIND;

	public static void initialize() {
		OPEN_CONFIG_KEYBIND = new KeyBinding("key.transformableitems.open_config", 65, "key.categories.transformableitems");
		ClientRegistry.registerKeyBinding(OPEN_CONFIG_KEYBIND);
	}
	
	@SubscribeEvent
	public void clientTick(TickEvent.ClientTickEvent event) {
		Minecraft client = Minecraft.getMinecraft();
		if (event.phase != TickEvent.Phase.END || OPEN_CONFIG_KEYBIND == null || !OPEN_CONFIG_KEYBIND.isPressed() || client.currentScreen != null) return;
		client.displayGuiScreen(new ConfigScreen());
	}
}
