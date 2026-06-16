package me.shaweel.transformableitems;

import com.mojang.brigadier.CommandDispatcher;

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;

public class ModCommands {
	public static boolean openConfig = false;

	private static int openConfig() {
		openConfig = true;
		return 1;
	}

	public static void initialize() {
		CommandDispatcher<FabricClientCommandSource> dispatcher = ClientCommandManager.DISPATCHER;
	
		dispatcher.register(ClientCommandManager.literal("transformableitems").executes(context -> { return openConfig(); }));
		dispatcher.register(ClientCommandManager.literal("ti").executes(context -> { return openConfig(); }));
	}
}
