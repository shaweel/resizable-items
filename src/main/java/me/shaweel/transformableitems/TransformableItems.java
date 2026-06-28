package me.shaweel.transformableitems;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

import static net.minecraft.client.renderer.GlStateManager.translated;
import static net.minecraft.client.renderer.GlStateManager.scalef;

public class TransformableItems implements IBakedModel {
	private final IBakedModel parent;

	public TransformableItems(IBakedModel parent) {
		this.parent = parent;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, Random rand) {
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

	@Override
	public TextureAtlasSprite getParticleTexture() {
		return parent.getParticleTexture();
	}

	@Override
	public ItemOverrideList getOverrides() {
		return parent.getOverrides();
	}

	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective(TransformType type) {
		Pair<? extends IBakedModel, Matrix4f> pair = parent.handlePerspective(type);
		if (type != TransformType.FIRST_PERSON_LEFT_HAND && type != TransformType.FIRST_PERSON_RIGHT_HAND) return pair;

		float x = type == TransformType.FIRST_PERSON_RIGHT_HAND ? ConfigFile.configData.xOffset * -1 : ConfigFile.configData.xOffset;
		translated(x, ConfigFile.configData.yOffset, ConfigFile.configData.zOffset);
		scalef(ConfigFile.configData.xScale, ConfigFile.configData.yScale, ConfigFile.configData.zScale);
		return pair;
	}
}
