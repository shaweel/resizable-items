package me.shaweel.transformableitems;

import java.util.List;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.state.IBlockState;
import static net.minecraft.client.renderer.GlStateManager.translate;
import static net.minecraft.client.renderer.GlStateManager.scale;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;

public class TransformableItems implements IBakedModel {
	private final IBakedModel parent;

	public TransformableItems(IBakedModel parent) {
		this.parent = parent;
	}

	@Override
	public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
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
		translate(x, ConfigFile.configData.yOffset, ConfigFile.configData.zOffset);
		scale(ConfigFile.configData.xScale, ConfigFile.configData.yScale, ConfigFile.configData.zScale);
		return pair;
	}
}
