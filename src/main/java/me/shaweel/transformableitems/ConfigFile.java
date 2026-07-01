package me.shaweel.transformableitems;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.shaweel.transformableitems.ConfigFile.ConfigData;
import net.minecraft.client.Minecraft;

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
	private static final File FILE = new File(Minecraft.getMinecraft().mcDataDir, "config/transformableitems.json");
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	public static void save() {
		try {
			FILE.getParentFile().mkdirs();

			Writer writer = new OutputStreamWriter(new FileOutputStream(FILE), "UTF-8");

			writer.write(GSON.toJson(configData).replace("  ", "\t"));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void load() {
		try {
			if (!FILE.exists()) return;

			InputStream inputStream = new FileInputStream(FILE);
			byte[] data = new byte[(int) FILE.length()];

			inputStream.read(data);
			inputStream.close();

			configData = GSON.fromJson(new String(data, "UTF-8"), ConfigData.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
