package me.shaweel.transformableitems;

import org.lwjgl.glfw.GLFW;
import net.minecraft.client.KeyMapping;

public class ModKeybinds {
	public static KeyMapping OPEN_CONFIG_KEYBIND;

	public static void initialize() {
		OPEN_CONFIG_KEYBIND = new KeyMapping("key.transformableitems.open_config", GLFW.GLFW_KEY_F7, "key.categories.transformableitems");
	}
}
