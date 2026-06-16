package me.shaweel.transformableitems.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.shaweel.transformableitems.Config;
import me.shaweel.transformableitems.ModCommands;
import net.minecraft.client.Minecraft;

@Mixin(Minecraft.class)
public class GuiOpener {
	@Inject(method = "tick", at = @At("TAIL"))
	private void onTick(CallbackInfo callbackInfo) {
		Minecraft client = Minecraft.getInstance();
		if (!ModCommands.openConfig || client.screen != null) return;
		ModCommands.openConfig = false;
		client.setScreen(Config.create(client.screen));
	}
}
