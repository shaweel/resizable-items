package me.shaweel.transformableitems;

import net.minecraft.client.settings.KeyBinding;

public class ModKeybinds {
	public static KeyBinding OPEN_CONFIG_KEYBIND;

	public static void initialize() {
		OPEN_CONFIG_KEYBIND = new KeyBinding("key.transformableitems.open_config", 296, "key.categories.transformableitems");
	}
}
