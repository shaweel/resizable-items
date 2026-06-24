package me.shaweel.transformableitems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraft.client.Minecraft;
import net.neoforged.fml.loading.FMLPaths;

public class ConfigFile {
	public static class ConfigData {
		public float xScale = 1f;
		public float yScale = 1f;
		public float zScale = 1f;
		public float xOffset = 0f;
		public float yOffset = 0f;
		public float zOffset = 0f;
		public boolean itemHeightAnimations = true;
	}

	public static ConfigData configData = new ConfigData();
	private static final Path FILE = FMLPaths.CONFIGDIR.get().resolve("transformableitems.json");
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
}
