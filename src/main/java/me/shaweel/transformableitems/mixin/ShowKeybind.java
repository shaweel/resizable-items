package me.shaweel.transformableitems.mixin;

import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.shaweel.transformableitems.ModKeybinds;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.GameSettings;

@Mixin(GameSettings.class)
public class ShowKeybind {
	@Mutable @Shadow public KeyBinding[] keyBindings;

	@Inject(method = "loadOptions", at = @At("HEAD"))
	private void addKeybind(CallbackInfo callbackInfo) {
		if (ModKeybinds.OPEN_CONFIG_KEYBIND != null) return;

		Map<String, Integer> sortOrder = CategorySortOrderAccessor.getCategorySortOrder();
		int smallestAvailable = 1;
		while (sortOrder.containsValue(smallestAvailable)) {
			smallestAvailable++;
		}

		sortOrder.put("key.categories.transformableitems", smallestAvailable);
		ModKeybinds.initialize();
		keyBindings = ArrayUtils.add(keyBindings, ModKeybinds.OPEN_CONFIG_KEYBIND);
	}
}