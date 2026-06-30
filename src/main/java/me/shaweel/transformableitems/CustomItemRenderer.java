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

		translate(ConfigFile.configData.xOffset * -1 , ConfigFile.configData.yOffset, ConfigFile.configData.zOffset);
		scale(ConfigFile.configData.xScale, ConfigFile.configData.yScale, ConfigFile.configData.zScale);

		super.renderItem(entitylivingbaseIn, heldStack, transformType);
		popMatrix();
	}
}
