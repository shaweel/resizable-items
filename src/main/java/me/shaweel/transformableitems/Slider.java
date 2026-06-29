package me.shaweel.transformableitems;

import java.util.function.Function;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
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
	public void drawButton(Minecraft screen, int mouseX, int mouseY) {
		//vanilla code
		if (!this.visible) return;
		FontRenderer fontrenderer = screen.fontRenderer;
		screen.getTextureManager().bindTexture(BUTTON_TEXTURES);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
		int i = this.getHoverState(this.hovered);
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		this.drawTexturedModalRect(this.x, this.y, 0, 46 + i * 20, this.width / 2, this.height);
		this.drawTexturedModalRect(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + i * 20, this.width / 2, this.height);
		this.mouseDragged(screen, mouseX, mouseY);
		int j = 14737632;

		if (packedFGColour != 0) { j = packedFGColour; }
		else if (!this.enabled) { j = 10526880; }
		else if (this.hovered) { j = 16777120; }

		//my code
		GuiUtils.drawContinuousTexturedBox(
			BUTTON_TEXTURES, 
			this.x + (int)(value * (float)(this.width - 8)), 
			this.y, 
			0, 66, SLIDER_THUMB_WIDTH, this.height, 
			200, 20, 
			2, 3, 2, 2, this.zLevel);

		//vanilla code
		this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
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
