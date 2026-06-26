package me.shaweel.transformableitems;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod("transformableitems")
public class TransformableItems {
	public TransformableItems() {
		ConfigFile.load();
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (client, parent) -> new ConfigScreen());
		MixinBootstrap.init();
		Mixins.addConfiguration("transformableitems.mixins.json");
		MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
	}
}