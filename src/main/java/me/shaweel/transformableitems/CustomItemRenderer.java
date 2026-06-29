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
	public void renderItemSide(EntityLivingBase entitylivingbaseIn, ItemStack heldStack, TransformType transformType, boolean leftHanded) {
		pushMatrix();

		if (transformType == TransformType.FIRST_PERSON_RIGHT_HAND) {
			translate(ConfigFile.configData.xOffset * -1 , ConfigFile.configData.yOffset, ConfigFile.configData.zOffset);
			scale(ConfigFile.configData.xScale, ConfigFile.configData.yScale, ConfigFile.configData.zScale);
		} else if (transformType == TransformType.FIRST_PERSON_LEFT_HAND) {
			translate(ConfigFile.configData.xOffset , ConfigFile.configData.yOffset, ConfigFile.configData.zOffset);
			scale(ConfigFile.configData.xScale, ConfigFile.configData.yScale, ConfigFile.configData.zScale);
		}

		super.renderItemSide(entitylivingbaseIn, heldStack, transformType, leftHanded);
		popMatrix();
	}
}
