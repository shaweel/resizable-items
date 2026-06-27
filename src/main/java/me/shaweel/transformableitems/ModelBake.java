package me.shaweel.transformableitems;

import java.util.Map;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;

public class ModelBake {
	public static void onModelBake(ModelBakeEvent event) {
		Map<ResourceLocation, IBakedModel> registry = event.getModelRegistry();

		for (Map.Entry<ResourceLocation, IBakedModel> entry : registry.entrySet()) {
			IBakedModel original = entry.getValue();
			entry.setValue(new TransformableItems(original));
		}
	}
}
