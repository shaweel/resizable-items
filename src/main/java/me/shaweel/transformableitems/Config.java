package me.shaweel.transformableitems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TextComponent;

public class Config {
	public static class ConfigData {
		public float xScale = 1f;
		public float yScale = 1f;
		public float zScale = 1f;
		public float xOffset = 0f;
		public float yOffset = 0f;
		public float zOffset = 0f;
		public boolean itemHeightAnimation = true;
	}

	public static ConfigData configData = new ConfigData();
	private static final Path FILE = Minecraft.getInstance().gameDirectory.toPath().resolve("config/transformable-items.json");
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	public static void save() {
		try {
			Files.writeString(FILE, GSON.toJson(configData).replace("  ", "\t"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void load() {
		try {
			if (!Files.exists(FILE)) return;
               		configData = GSON.fromJson(Files.readString(FILE), ConfigData.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Screen create(Screen parent) { 
		ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle(new TextComponent("Transformable Items"));
		builder.setSavingRunnable(Config::save);
		ConfigCategory config = builder.getOrCreateCategory(new TextComponent("Configuration"));
		
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();
		config.addEntry(entryBuilder.startIntSlider(new TextComponent("X Size"), (int)(configData.xScale * 100), 10, 200)
			.setDefaultValue(100)
			.setTextGetter(value -> new TextComponent(String.format("%.2f", value / 100f)))
			.setSaveConsumer(value -> configData.xScale = value / 100f)
			.build());

		config.addEntry(entryBuilder.startIntSlider(new TextComponent("Y Size"), (int)(configData.yScale * 100), 10, 200)
			.setDefaultValue(100)
			.setTextGetter(value -> new TextComponent(String.format("%.2f", value / 100f)))
			.setSaveConsumer(value -> configData.yScale = value / 100f)
			.build());

		config.addEntry(entryBuilder.startIntSlider(new TextComponent("Z Size"), (int)(configData.zScale * 100), 10, 200)
			.setDefaultValue(100)
			.setTextGetter(value -> new TextComponent(String.format("%.2f", value / 100f)))
			.setSaveConsumer(value -> configData.zScale = value / 100f)
			.build());

		config.addEntry(entryBuilder.startIntSlider(new TextComponent("X Offset"), (int)(configData.xOffset * 100), -100, 100)
			.setDefaultValue(0)
			.setTextGetter(value -> new TextComponent(String.format("%.2f", value / 100f)))
			.setSaveConsumer(value -> configData.xOffset = value / 100f)
			.build());

		config.addEntry(entryBuilder.startIntSlider(new TextComponent("Y Offset"), (int)(configData.yOffset * 100), -100, 100)
			.setDefaultValue(0)
			.setTextGetter(value -> new TextComponent(String.format("%.2f", value / 100f)))
			.setSaveConsumer(value -> configData.yOffset = value / 100f)
			.build());

		config.addEntry(entryBuilder.startIntSlider(new TextComponent("Z Offset"), (int)(configData.zOffset * 100), -100, 100)
			.setDefaultValue(0)
			.setTextGetter(value -> new TextComponent(String.format("%.2f", value / 100f)))
			.setSaveConsumer(value -> configData.zOffset = value / 100f)
			.build());

		config.addEntry(entryBuilder.startBooleanToggle(new TextComponent("Item Height Animations"), configData.itemHeightAnimation)
			.setDefaultValue(true)
			.build());

		return builder.build();

		/*return YetAnotherConfigLib.createBuilder()
		.title(new TextComponent("Transformable Items"))
		.category(ConfigCategory.createBuilder()
			.name(new TextComponent("Configuration"))
			.option(Option.<Float>createBuilder()
				.name(new TextComponent("X Scale"))
				.binding(
					1f,
					() -> configData.xScale,
					v -> configData.xScale = v
				)
				.controller(opt ->
					FloatSliderControllerBuilder.create(opt)
						.range(0.1f, 2f)
						.step(0.01f)
						.formatValue(v -> new TextComponent(String.format("%.2f", v)))
				)
				.build())

			.option(Option.<Float>createBuilder()
				.name(new TextComponent("Y Scale"))
				.binding(
					1f,
					() -> configData.yScale,
					v -> configData.yScale = v
				)
				.controller(opt ->
					FloatSliderControllerBuilder.create(opt)
						.range(0.1f, 2f)
						.step(0.01f)
						.formatValue(v -> new TextComponent(String.format("%.2f", v)))
				)
				.build())
			
			.option(Option.<Float>createBuilder()
				.name(new TextComponent("Z Scale"))
				.binding(
					1f,
					() -> configData.zScale,
					v -> configData.zScale = v
				)
				.controller(opt ->
					FloatSliderControllerBuilder.create(opt)
						.range(0.1f, 2f)
						.step(0.01f)
						.formatValue(v -> new TextComponent(String.format("%.2f", v)))
				)
				.build())

			.option(Option.<Float>createBuilder()
				.name(new TextComponent("X Offset"))
				.binding(
					0f,
					() -> configData.xOffset,
					v -> configData.xOffset = v
				)
				.controller(opt ->
					FloatSliderControllerBuilder.create(opt)
						.range(-1f, 1f)
						.step(0.01f)
						.formatValue(v -> new TextComponent(String.format("%.2f", v)))
				)
				.build())

			.option(Option.<Float>createBuilder()
				.name(new TextComponent("Y Offset"))
				.binding(
					0f,
					() -> configData.yOffset,
					v -> configData.yOffset = v
				)
				.controller(opt ->
					FloatSliderControllerBuilder.create(opt)
						.range(-1f, 1f)
						.step(0.01f)
						.formatValue(v -> new TextComponent(String.format("%.2f", v)))
				)
				.build())
			
			.option(Option.<Float>createBuilder()
				.name(new TextComponent("Z Offset"))
				.binding(
					0f,
					() -> configData.zOffset,
					v -> configData.zOffset = v
				)
				.controller(opt ->
					FloatSliderControllerBuilder.create(opt)
						.range(-1f, 1f)
						.step(0.01f)
						.formatValue(v -> new TextComponent(String.format("%.2f", v)))
				)
				.build())

			.option(Option.<Boolean>createBuilder()
				.name(new TextComponent("Item Height Animation"))
				.binding(
					true,
					() -> configData.itemHeightAnimation,
					v -> configData.itemHeightAnimation = v
				)
				.controller(opt ->
					BooleanControllerBuilder.create(opt)
				)
				.build())
			.build())
			.save(Config::save)
			.build()
			.generateScreen(parent);*/
	}
}
