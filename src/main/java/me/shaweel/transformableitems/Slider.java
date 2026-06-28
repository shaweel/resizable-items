package me.shaweel.transformableitems;

import java.util.function.Function;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.config.GuiUtils;

public class Slider extends GuiButton {
	public static int SLIDER_THUMB_WIDTH = 8;

	public float value;
	public String name;
	public Function<Float, String> applyValue;
	private boolean dragging;

	public void changeValue(float newValue) {
		newValue = MathHelper.clamp(newValue, 0f, 1f);
		this.value = newValue;
		this.displayString = String.format("%s: %s", this.name, this.applyValue.apply(this.value));
	}

	private void updateValue(double mouseX) {
		changeValue((float) ((mouseX - (this.x + SLIDER_THUMB_WIDTH / 2)) / (this.width - SLIDER_THUMB_WIDTH)));
	}

	public Slider(int id, int x, int y, int w, int h, String name, float defaultValue, Function<Float, String> applyValue) {
		super(id, x, y, w, h, String.format("%s: %s", name, applyValue.apply(defaultValue)));
		this.name = name;
		this.applyValue = applyValue;
		changeValue(defaultValue);
	}

	@Override
	protected void renderBg(Minecraft client, int mouseX, int mouseY) {
		if (!this.visible) return;
		GuiUtils.drawContinuousTexturedBox(
			BUTTON_TEXTURES, 
			this.x + (int)(value * (float)(this.width - 8)), 
			this.y, 
			0, 66, SLIDER_THUMB_WIDTH, this.height, 
			200, 20, 
			2, 3, 2, 2, this.zLevel);
	}

	@Override
	public int getHoverState(boolean par1) {
		return 0;
	}

	@Override
	public void onClick(double mouseX, double mouseY) {
		this.dragging = true;
		this.updateValue(mouseX);
	}

	@Override
	protected void onDrag(double mouseX, double mouseY, double mouseDX, double mouseDY) {
		if (!this.dragging) return;
		this.updateValue(mouseX);
	}

	@Override
	public void onRelease(double mouseX, double mouseY) {
		this.dragging = false;
	}
}
