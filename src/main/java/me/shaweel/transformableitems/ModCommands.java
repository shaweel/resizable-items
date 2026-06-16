package me.shaweel.transformableitems;

import com.mojang.brigadier.CommandDispatcher;

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ModCommands {
	private static boolean openConfig = false;

	private static int openConfig() {
		openConfig = true;
		return 1;
	}

	public static void initialize() {
		CommandDispatcher<FabricClientCommandSource> dispatcher = ClientCommandManager.DISPATCHER;
	
		dispatcher.register(ClientCommandManager.literal("transformableitems").executes(context -> { return openConfig(); }));
		dispatcher.register(ClientCommandManager.literal("ti").executes(context -> { return openConfig(); }));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (!openConfig || client.screen != null) return;
			openConfig = false;
			client.setScreen(Config.create(client.screen));
		});
	}
}
