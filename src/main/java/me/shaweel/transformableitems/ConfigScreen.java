package me.shaweel.transformableitems;

import me.shaweel.transformableitems.functionalinterfaces.Function;
import me.shaweel.transformableitems.functionalinterfaces.Supplier;
import me.shaweel.transformableitems.functionalinterfaces.Consumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class ConfigScreen extends GuiScreen { 
	public ConfigScreen() {
		super();
	}

	public ConfigScreen(GuiScreen parent) {
		super();
	}

	public enum OptionTypes { FLOAT_SLIDER, BOOLEAN_OPTION }
	public static final int DONE_BUTTON_PADDING = 7;
	public static final int TITLE_PADDING = 12;
	public static final int RESET_BUTTON_WIDTH = 50;
	public static final int DONE_BUTTON_WIDTH = 200;
	public static final int WIDGET_WIDTH = 208;
	public static final int WIDGET_HEIGHT = 20;
	public static final int WIDGET_PADDING = 4;

	public static final float MIN_SCALE = 0;
	public static final float MAX_SCALE = 2;
	public static final float MIN_OFFSET = -1;
	public static final float MAX_OFFSET = 1;

	public static final float DEFAULT_SCALE = 1f;
	public static final float DEFAULT_OFFSET = 0f;
	
	private int currentId = 0;

	private int row(int index, int rowAmount) {
		int headerSpace = TITLE_PADDING + this.fontRendererObj.FONT_HEIGHT;
		int footerSpace = DONE_BUTTON_PADDING + WIDGET_HEIGHT;

		int allWidgetsHeight = rowAmount * WIDGET_HEIGHT + (rowAmount - 1) * WIDGET_PADDING;

		int usableHeight = this.height - headerSpace - footerSpace;
		int startY = headerSpace + (usableHeight - allWidgetsHeight) / 2;

		return startY + (WIDGET_HEIGHT + WIDGET_PADDING) * index;
	}
	
	private int x() {
		return x(WIDGET_WIDTH + WIDGET_PADDING + RESET_BUTTON_WIDTH);
	}

	private int x(int w) {
		return this.width / 2 - w / 2;
	}

	private float denormalize(float min, float max, float x) {
		return (float)(min + x * (max - min));
	}

	private float normalize(float min, float max, float x) {
		return (x - min) / (max - min);
	}

	private void createButton(final int x, final int y, final int w, final int h, final String name, final Runnable action) {
		this.buttonList.add(new GuiButton(this.currentId, x, y, w, h, name) {
			@Override
			public boolean mousePressed(Minecraft client, int mouseX, int mouseY) {
				if (!super.mousePressed(client, mouseX, mouseY)) return false;
				action.run();
				return true;
			}
		});
		this.currentId++;
	}

	private void createSlider(final int x, final int y, final int w, final int h, final String name, final float min, final float max, 
					final Supplier<Float> getter, final Consumer<Float> setter, final Float defaultValue) {
		final Slider slider = new Slider(this.currentId, x, y, w, h, name, normalize(min, max, getter.get()), new Function<Float,String>() {
			public String apply(Float value) {
				float denormalized = Math.round(denormalize(min, max, value) * 100f) / 100f;
			
				setter.accept(denormalized);
				ConfigFile.save();
				return String.format("%.2f", denormalized);
			}
		});
		this.buttonList.add(slider);
		this.currentId++;

		createButton(
			x + w + WIDGET_PADDING, 
			y, 
			RESET_BUTTON_WIDTH, 
			h, 
			"Reset", 
			new Runnable() {
				public void run() {
					slider.changeValue(normalize(min, max, defaultValue));
				}	
			}
		);
	}

	private String getBooleanText(final String name, final boolean value) {
		return String.format("%s: %s", name, value ? "ON" : "OFF");
	}

	private void createBooleanOption(final int x, final int y, final int w, final int h, final String name, 
						final Supplier<Boolean> getter, final Consumer<Boolean> setter, final Boolean defaultValue) {
		final GuiButton booleanOption = new GuiButton(this.currentId, x, y, w, h, getBooleanText(name, getter.get())) {
			@Override
			public boolean mousePressed(Minecraft client, int mouseX, int mouseY) {
				if (!super.mousePressed(client, mouseX, mouseY)) return false;

				boolean newValue = !getter.get();
				setter.accept(newValue);

				this.displayString = getBooleanText(name, newValue);

				ConfigFile.save();
				return true;
			}
		};
		this.buttonList.add(booleanOption);
		this.currentId++;

		createButton(
			x + w + WIDGET_PADDING, 
			y, 
			RESET_BUTTON_WIDTH, 
			h, 
			"Reset", 
			new Runnable() {
				public void run() {
					setter.accept(defaultValue);
					ConfigFile.save();
					booleanOption.displayString = getBooleanText(name, defaultValue);
				}	
			}
		);
	}

	private void createText(final int x, final int y, final String text) {
		drawString(this.fontRendererObj, text, x, y, 0xFFFFFF);
	}

	@Override
	public void initGui() {
		super.initGui();

		this.currentId = 0;

		createSlider(
			x(), 
			row(0, 7), 
			WIDGET_WIDTH, 
			WIDGET_HEIGHT, 
			"X Scale", 
			MIN_SCALE, 
			MAX_SCALE, 
			new Supplier<Float>() {
				public Float get() {
					return ConfigFile.configData.xScale;
				}
			}, 
			new Consumer<Float>() {
				public void accept(Float value) {
					ConfigFile.configData.xScale = value;
				}
			}, 
			DEFAULT_SCALE
		);

		createSlider(
			x(), 
			row(1, 7), 
			WIDGET_WIDTH, 
			WIDGET_HEIGHT, 
			"Y Scale", 
			MIN_SCALE, 
			MAX_SCALE, 
			new Supplier<Float>() {
				public Float get() {
					return ConfigFile.configData.yScale;
				}
			}, 
			new Consumer<Float>() {
				public void accept(Float value) {
					ConfigFile.configData.yScale = value;
				}
			}, 
			DEFAULT_SCALE
		);

		createSlider(
			x(), 
			row(2, 7), 
			WIDGET_WIDTH, 
			WIDGET_HEIGHT, 
			"Z Scale", 
			MIN_SCALE, 
			MAX_SCALE, 
			new Supplier<Float>() {
				public Float get() {
					return ConfigFile.configData.zScale;
				}
			}, 
			new Consumer<Float>() {
				public void accept(Float value) {
					ConfigFile.configData.zScale = value;
				}
			}, 
			DEFAULT_SCALE
		);


		createSlider(
			x(), 
			row(3, 7), 
			WIDGET_WIDTH, 
			WIDGET_HEIGHT, 
			"X Offset", 
			MIN_OFFSET, 
			MAX_OFFSET, 
			new Supplier<Float>() {
				public Float get() {
					return ConfigFile.configData.xOffset;
				}
			}, 
			new Consumer<Float>() {
				public void accept(Float value) {
					ConfigFile.configData.xOffset = value;
				}
			}, 
			DEFAULT_OFFSET
		);

		createSlider(
			x(), 
			row(4, 7), 
			WIDGET_WIDTH, 
			WIDGET_HEIGHT, 
			"Y Offset", 
			MIN_OFFSET, 
			MAX_OFFSET, 
			new Supplier<Float>() {
				public Float get() {
					return ConfigFile.configData.yOffset;
				}
			}, 
			new Consumer<Float>() {
				public void accept(Float value) {
					ConfigFile.configData.yOffset = value;
				}
			}, 
			DEFAULT_OFFSET
		);

		createSlider(
			x(), 
			row(5, 7), 
			WIDGET_WIDTH, 
			WIDGET_HEIGHT, 
			"Z Offset", 
			MIN_OFFSET, 
			MAX_OFFSET, 
			new Supplier<Float>() {
				public Float get() {
					return ConfigFile.configData.zOffset;
				}
			}, 
			new Consumer<Float>() {
				public void accept(Float value) {
					ConfigFile.configData.zOffset = value;
				}
			}, 
			DEFAULT_OFFSET
		);

		createBooleanOption(
			x(),
			row(6, 7),
			WIDGET_WIDTH,
			WIDGET_HEIGHT,
			"Item Height Animations",
			new Supplier<Boolean>() {
				public Boolean get() {
					return ConfigFile.configData.itemHeightAnimations;
				}
			},
			new Consumer<Boolean>() {
				public void accept(Boolean value) {
					ConfigFile.configData.itemHeightAnimations = value;
				}
			},
			true
		);

		final ConfigScreen self = this;

		createButton(
			x(DONE_BUTTON_WIDTH), 
			this.height - DONE_BUTTON_PADDING - WIDGET_HEIGHT, 
			DONE_BUTTON_WIDTH, 
			WIDGET_HEIGHT, 
			"Done",
			new Runnable() {
				public void run() {
					self.mc.displayGuiScreen(null);
				}
			}
		);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick) {
		this.drawDefaultBackground();

		createText(x(this.fontRendererObj.getStringWidth("Transformable Items Configuration")), TITLE_PADDING, "Transformable Items Configuration");

		super.drawScreen(mouseX, mouseY, partialTick);
	}
}
