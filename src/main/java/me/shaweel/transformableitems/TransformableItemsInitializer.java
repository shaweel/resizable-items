package me.shaweel.transformableitems;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("transformableitems")
public class TransformableItemsInitializer {
	public TransformableItemsInitializer() {
		ConfigFile.load();
		ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (client, parent) -> new ConfigScreen());
		MinecraftForge.EVENT_BUS.register(ModKeybinds.class);
		MinecraftForge.EVENT_BUS.register(ItemHeightAnimations.class);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ModelBake::onModelBake);
		ModKeybinds.initialize();
	}
}