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
		ConfigBuilder builder = ConfigBuilder.create().setParentScreen(parent).setTitle("Transformable Items");
		builder.setSavingRunnable(Config::save);
		ConfigCategory config = builder.getOrCreateCategory("Configuration");
		
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();
		config.addEntry(entryBuilder.startIntSlider("X Size", (int)(configData.xScale * 100), 10, 200)
			.setDefaultValue(100)
			.setTextGetter(value -> (String.format("%.2f", value / 100f)))
			.setSaveConsumer(value -> configData.xScale = value / 100f)
			.build());

		config.addEntry(entryBuilder.startIntSlider("Y Size", (int)(configData.yScale * 100), 10, 200)
			.setDefaultValue(100)
			.setTextGetter(value -> (String.format("%.2f", value / 100f)))
			.setSaveConsumer(value -> configData.yScale = value / 100f)
			.build());

		config.addEntry(entryBuilder.startIntSlider("Z Size", (int)(configData.zScale * 100), 10, 200)
			.setDefaultValue(100)
			.setTextGetter(value -> (String.format("%.2f", value / 100f)))
			.setSaveConsumer(value -> configData.zScale = value / 100f)
			.build());

		config.addEntry(entryBuilder.startIntSlider("X Offset", (int)(configData.xOffset * 100), -100, 100)
			.setDefaultValue(0)
			.setTextGetter(value -> (String.format("%.2f", value / 100f)))
			.setSaveConsumer(value -> configData.xOffset = value / 100f)
			.build());

		config.addEntry(entryBuilder.startIntSlider("Y Offset", (int)(configData.yOffset * 100), -100, 100)
			.setDefaultValue(0)
			.setTextGetter(value -> (String.format("%.2f", value / 100f)))
			.setSaveConsumer(value -> configData.yOffset = value / 100f)
			.build());

		config.addEntry(entryBuilder.startIntSlider("Z Offset", (int)(configData.zOffset * 100), -100, 100)
			.setDefaultValue(0)
			.setTextGetter(value -> (String.format("%.2f", value / 100f)))
			.setSaveConsumer(value -> configData.zOffset = value / 100f)
			.build());

		config.addEntry(entryBuilder.startBooleanToggle("Item Height Animations", configData.itemHeightAnimation)
			.setDefaultValue(true)
			.setSaveConsumer(value -> configData.itemHeightAnimation = value)
			.build());

		return builder.build();
	}
}
