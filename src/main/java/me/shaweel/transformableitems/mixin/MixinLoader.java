package me.shaweel.transformableitems.mixin;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import cpw.mods.modlauncher.api.IEnvironment;
import cpw.mods.modlauncher.api.ITransformationService;
import cpw.mods.modlauncher.api.ITransformer;

public class MixinLoader implements ITransformationService {
	@Override public String name() { return "transformableitems_mixin_loader"; }
	@Override public void onLoad(IEnvironment env, Set<String> otherServices) {}
	@SuppressWarnings("rawtypes") @Override public List<ITransformer> transformers() { return Collections.emptyList(); }
	@Override public void beginScanning(IEnvironment environment) {}

	@Override
	public void initialize(IEnvironment environment) {
		MixinBootstrap.init();
		Mixins.addConfiguration("transformableitems.mixins.json");
		MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
	}
}
