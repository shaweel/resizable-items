package me.shaweel.transformableitems;

import net.minecraft.client.Minecraft;
import static  net.minecraft.client.renderer.GlStateManager.pushMatrix;
import static  net.minecraft.client.renderer.GlStateManager.popMatrix;
import static  net.minecraft.client.renderer.GlStateManager.translate;
import static  net.minecraft.client.renderer.GlStateManager.scale;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class CustomItemRenderer extends ItemRenderer {
	public CustomItemRenderer(Minecraft client) {
		super(client);
	}

	@Override
	public void renderItem(EntityLivingBase entitylivingbaseIn, ItemStack heldStack, TransformType transformType) {
		pushMatrix();

		if (transformType == TransformType.FIRST_PERSON) {
			translate(ConfigFile.configData.xOffset * -2.5f , ConfigFile.configData.yOffset * 2.5f, ConfigFile.configData.zOffset * 2.5f);
			scale(ConfigFile.configData.xScale, ConfigFile.configData.yScale, ConfigFile.configData.zScale);
		}

		super.renderItem(entitylivingbaseIn, heldStack, transformType);
		popMatrix();
	}
}
