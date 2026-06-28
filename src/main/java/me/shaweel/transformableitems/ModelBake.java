package me.shaweel.transformableitems;

import java.util.Map;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;

public class ModelBake {
	public static void onModelBake(ModelBakeEvent event) {
		System.out.println("ModelBakeEvent fired");
		Map<ModelResourceLocation, IBakedModel> registry = event.getModelRegistry();

		for (Map.Entry<ModelResourceLocation, IBakedModel> entry : registry.entrySet()) {
			IBakedModel original = entry.getValue();
			entry.setValue(new TransformableItems(original));
		}
	}
}
