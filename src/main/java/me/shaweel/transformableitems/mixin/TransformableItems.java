package me.shaweel.transformableitems.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FirstPersonRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.matrix.MatrixStack;

import me.shaweel.transformableitems.ConfigFile;

@Mixin(FirstPersonRenderer.class)
public class TransformableItems {
	@Shadow private float equippedProgressMainHand;
	@Shadow private float equippedProgressOffHand;
	@Shadow private float prevEquippedProgressMainHand;
	@Shadow private float prevEquippedProgressOffHand;
	@Shadow private ItemStack itemStackMainHand;
	@Shadow private ItemStack itemStackOffHand;
	
	@Inject(method = "renderItemSide", at = @At("HEAD"))
	private void transform(
		LivingEntity livingEntity,
		ItemStack itemStack,
		TransformType transformType,
		boolean bl,
		MatrixStack matrixStack,
		IRenderTypeBuffer multiBufferSource,
		int i,
		CallbackInfo callbackInfo
	) {
		if (transformType == TransformType.FIRST_PERSON_LEFT_HAND) {
			matrixStack.translate(ConfigFile.configData.xOffset, ConfigFile.configData.yOffset, ConfigFile.configData.zOffset);
			matrixStack.scale(ConfigFile.configData.xScale, ConfigFile.configData.yScale, ConfigFile.configData.zScale);
		} else if (transformType == TransformType.FIRST_PERSON_RIGHT_HAND) {
			matrixStack.translate(ConfigFile.configData.xOffset * -1, ConfigFile.configData.yOffset, ConfigFile.configData.zOffset);
			matrixStack.scale(ConfigFile.configData.xScale, ConfigFile.configData.yScale, ConfigFile.configData.zScale);
		} else return;
	}

	@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
	private void tick(CallbackInfo callbackInfo) {
		if (ConfigFile.configData.itemHeightAnimations) return;
		prevEquippedProgressMainHand = 1f;
		prevEquippedProgressOffHand = 1f;
		equippedProgressMainHand = 1f;
		equippedProgressOffHand = 1f;

		itemStackMainHand = Minecraft.getInstance().player.getHeldItemMainhand();
		itemStackOffHand = Minecraft.getInstance().player.getHeldItemOffhand();

		callbackInfo.cancel();
	}
}