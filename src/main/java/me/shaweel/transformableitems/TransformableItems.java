package me.shaweel.transformableitems;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("transformableitems")
public class TransformableItems {
	public TransformableItems(FMLJavaModLoadingContext modLoadingContext) {
		ConfigFile.load();
		MinecraftForge.registerConfigScreen((mc, screen) -> new ConfigScreen());
	}
}