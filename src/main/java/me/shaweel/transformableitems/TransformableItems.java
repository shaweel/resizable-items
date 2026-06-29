package me.shaweel.transformableitems;

import java.util.List;
import java.util.Random;

import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;

public class TransformableItems implements IBakedModel {
	private final IBakedModel parent;

	public TransformableItems(IBakedModel parent) {
		this.parent = parent;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand) {
		return parent.getQuads(state, side, rand);
	}

	@Override
	public boolean isAmbientOcclusion() {
		return parent.isAmbientOcclusion();
	}

	@Override
	public boolean isGui3d() {
		return parent.isGui3d();
	}

	@Override
	public boolean isBuiltInRenderer() {
		return parent.isBuiltInRenderer();
	}

	@SuppressWarnings("deprecation")
	@Override
	public TextureAtlasSprite getParticleTexture() {
		return parent.getParticleTexture();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return parent.getOverrides();
	}

	@Override
	public boolean func_230044_c_() {
		return parent.func_230044_c_();
	}

	@Override
	public IBakedModel handlePerspective(TransformType transformType, MatrixStack matrixStack) {
		System.out.println(transformType);
		if (transformType == TransformType.FIRST_PERSON_LEFT_HAND) {
			matrixStack.translate(ConfigFile.configData.xOffset, ConfigFile.configData.yOffset, ConfigFile.configData.zOffset);
			matrixStack.scale(ConfigFile.configData.xScale, ConfigFile.configData.yScale, ConfigFile.configData.zScale);
		} else if (transformType == TransformType.FIRST_PERSON_RIGHT_HAND) {
			matrixStack.translate(ConfigFile.configData.xOffset * -1, ConfigFile.configData.yOffset, ConfigFile.configData.zOffset);
			matrixStack.scale(ConfigFile.configData.xScale, ConfigFile.configData.yScale, ConfigFile.configData.zScale);
		}
		return parent.handlePerspective(transformType, matrixStack);
	}
}
