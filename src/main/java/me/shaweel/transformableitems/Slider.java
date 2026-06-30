package me.shaweel.transformableitems;

import java.util.function.Function;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import net.minecraftforge.fml.client.config.GuiUtils;

public class Slider extends GuiButton {
	public static int SLIDER_THUMB_WIDTH = 8;

	public float value;
	public String name;
	public Function<Float, String> applyValue;
	private boolean dragging;

	public void changeValue(float newValue) {
		newValue = MathHelper.clamp_float(newValue, 0f, 1f);
		this.value = newValue;
		this.displayString = String.format("%s: %s", this.name, this.applyValue.apply(this.value));
	}

	private void updateValue(double mouseX) {
		changeValue((float) ((mouseX - (this.xPosition + SLIDER_THUMB_WIDTH / 2)) / (this.width - SLIDER_THUMB_WIDTH)));
	}

	public Slider(int id, int x, int y, int w, int h, String name, float defaultValue, Function<Float, String> applyValue) {
		super(id, x, y, w, h, String.format("%s: %s", name, applyValue.apply(defaultValue)));
		this.name = name;
		this.applyValue = applyValue;
		changeValue(defaultValue);
	}

	@Override
	public void drawButton(Minecraft client, int mouseX, int mouseY) {
		//vanilla code
		FontRenderer fontrenderer = client.fontRendererObj;
		client.getTextureManager().bindTexture(buttonTextures);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
		int i = this.getHoverState(this.hovered);
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.blendFunc(770, 771);
		this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + i * 20, this.width / 2, this.height);
		this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
		this.mouseDragged(client, mouseX, mouseY);
		int j = 14737632;

		if (packedFGColour != 0) { j = packedFGColour; }
		else if (!this.enabled) { j = 10526880; }
		else if (this.hovered) { j = 16777120; }

		//my code
		GuiUtils.drawContinuousTexturedBox(
			buttonTextures, 
			this.xPosition + (int)(value * (float)(this.width - 8)), 
			this.yPosition, 
			0, 66, SLIDER_THUMB_WIDTH, this.height, 
			200, 20, 
			2, 3, 2, 2, this.zLevel);

		//vanilla code
		this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, j);
	}

	@Override
	public int getHoverState(boolean par1) {
		return 0;
	}

	@Override
	public boolean mousePressed(Minecraft client, int mouseX, int mouseY) {
		if (!super.mousePressed(client, mouseX, mouseY)) return false;
		this.dragging = true;
		this.updateValue(mouseX);
		return true;
	}

	@Override
	protected void mouseDragged(Minecraft client, int mouseX, int mouseY) {
		if (!this.dragging) return;
		this.updateValue(mouseX);
	}

	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		this.dragging = false;
	}
}
