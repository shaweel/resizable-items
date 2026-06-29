package me.shaweel.transformableitems;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

public class ConfigScreenFactory implements IModGuiFactory {
	@Override
	public void initialize(Minecraft minecraftInstance) {}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return java.util.Collections.emptySet();
	}

	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return ConfigScreen.class;
	}

	@Override
	public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
		return null;
	}
}
