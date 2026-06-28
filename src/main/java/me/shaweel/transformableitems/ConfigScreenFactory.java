package me.shaweel.transformableitems;

import java.util.Set;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.IModGuiFactory;

public class ConfigScreenFactory implements IModGuiFactory {
	@Override
	public void initialize(Minecraft minecraftInstance) {
		System.out.println("CONFIG FACTORY INITIALIZED");
	}

	@Override
	public boolean hasConfigGui() {
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		return new ConfigScreen();
	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}
}
