package me.shaweel.transformableitems;

import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glScalef;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

public class CustomItemRenderer extends ItemRenderer {
	public CustomItemRenderer(Minecraft client) {
		super(client);
	}

	@Override
	public void renderItem(EntityLivingBase entitylivingbaseIn, ItemStack heldStack, int i, ItemRenderType itemRenderType) {
		glPushMatrix();

		if (itemRenderType == ItemRenderType.EQUIPPED_FIRST_PERSON) {
			glTranslatef(ConfigFile.configData.xOffset * -2.5f , ConfigFile.configData.yOffset * 2.5f, ConfigFile.configData.zOffset * 2.5f);
			glScalef(ConfigFile.configData.xScale, ConfigFile.configData.yScale, ConfigFile.configData.zScale);
		}

		super.renderItem(entitylivingbaseIn, heldStack, i, itemRenderType);
		glPopMatrix();
	}
}
