package me.shaweel.transformableitems;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.config.GuiUtils;
import me.shaweel.transformableitems.functionalinterfaces.Function;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.MathHelper;

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
		FontRenderer fontrenderer = client.fontRenderer;
		client.getTextureManager().bindTexture(buttonTextures);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.field_146123_n = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
		int k = this.getHoverState(this.field_146123_n);
		GL11.glEnable(GL11.GL_BLEND);
		OpenGlHelper.glBlendFunc(770, 771, 1, 0);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + k * 20, this.width / 2, this.height);
		this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
		this.mouseDragged(client, mouseX, mouseY);
		int l = 14737632;

		if (packedFGColour != 0) { l = packedFGColour; }
		else if (!this.enabled) { l = 10526880; }
		else if (this.field_146123_n) { l = 16777120; }

		//my code
		GuiUtils.drawContinuousTexturedBox(
			buttonTextures, 
			this.xPosition + (int)(value * (float)(this.width - 8)), 
			this.yPosition, 
			0, 66, SLIDER_THUMB_WIDTH, this.height, 
			200, 20, 
			2, 3, 2, 2, this.zLevel);

		//vanilla code
		this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
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
