package me.shaweel.transformableitems.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.FirstPersonRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.mojang.blaze3d.platform.GlStateManager.popMatrix;
import static com.mojang.blaze3d.platform.GlStateManager.pushMatrix;
import static com.mojang.blaze3d.platform.GlStateManager.translated;
import static com.mojang.blaze3d.platform.GlStateManager.scalef;

import me.shaweel.transformableitems.ConfigFile;

@Mixin(FirstPersonRenderer.class)
public class TransformableItems {
	@Shadow private float equippedProgressMainHand;
	@Shadow private float equippedProgressOffHand;
	@Shadow private float prevEquippedProgressMainHand;
	@Shadow private float prevEquippedProgressOffHand;
	@Shadow private ItemStack itemStackMainHand;
	@Shadow private ItemStack itemStackOffHand;
	
	private boolean pushed = false;

	@Inject(
		method = "renderItemSide(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/model/ItemCameraTransforms$TransformType;Z)V", 
		at = @At("HEAD")
	)
	private void transform(
		LivingEntity livingEntity,
		ItemStack itemStack,
		TransformType transformType,
		boolean bl,
		CallbackInfo callbackInfo
	) {
		if (transformType == TransformType.FIRST_PERSON_LEFT_HAND) {
			pushed = true;
			pushMatrix();
			translated(ConfigFile.configData.xOffset, ConfigFile.configData.yOffset, ConfigFile.configData.zOffset);
			scalef(ConfigFile.configData.xScale, ConfigFile.configData.yScale, ConfigFile.configData.zScale);
		} else if (transformType == TransformType.FIRST_PERSON_RIGHT_HAND) {
			pushed = true;
			pushMatrix();
			translated(ConfigFile.configData.xOffset * -1, ConfigFile.configData.yOffset, ConfigFile.configData.zOffset);
			scalef(ConfigFile.configData.xScale, ConfigFile.configData.yScale, ConfigFile.configData.zScale);
		} else return;
	}

	@Inject(
		method = "renderItemSide(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/model/ItemCameraTransforms$TransformType;Z)V", 
		at = @At("TAIL")
	)
	private void pop(
		LivingEntity livingEntity,
		ItemStack itemStack,
		TransformType transformType,
		boolean bl,
		CallbackInfo callbackInfo
	) {
		if (transformType != TransformType.FIRST_PERSON_RIGHT_HAND && transformType != TransformType.FIRST_PERSON_LEFT_HAND) return;
		if (!pushed) return;
		pushed = false;
		popMatrix();
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