package me.shaweel.transformableitems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ModKeybinds {
	public static KeyBinding OPEN_CONFIG_KEYBIND;

	public static void initialize() {
		OPEN_CONFIG_KEYBIND = new KeyBinding("key.transformableitems.open_config", 296, "key.categories.transformableitems");
		ClientRegistry.registerKeyBinding(OPEN_CONFIG_KEYBIND);
	}
	
	@SubscribeEvent
	public static void clientTick(TickEvent.ClientTickEvent event) {
		Minecraft client = Minecraft.getInstance();
		if (event.phase != TickEvent.Phase.END || OPEN_CONFIG_KEYBIND == null || !OPEN_CONFIG_KEYBIND.isPressed() || client.currentScreen != null) return;
		client.displayGuiScreen(new ConfigScreen());
	}
}
