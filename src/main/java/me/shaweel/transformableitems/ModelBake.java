package me.shaweel.transformableitems;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.registry.IRegistry;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModelBake {
	@SubscribeEvent
	public static void onModelBake(ModelBakeEvent event) {
		IRegistry<ModelResourceLocation, IBakedModel> registry = event.getModelRegistry();

		for (ModelResourceLocation key : registry.getKeys()) {
			IBakedModel original = registry.getObject(key);
			registry.putObject(key, new TransformableItems(original));
		}
	}
}
