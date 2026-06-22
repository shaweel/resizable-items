package me.shaweel.transformableitems.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

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

@Mixin(ItemInHandRenderer.class)
public class TransformableItems {
	@Shadow private float mainHandHeight;
	@Shadow private float offHandHeight;
	@Shadow private float oMainHandHeight;
	@Shadow private float oOffHandHeight;
	@Shadow private ItemStack mainHandItem;
	@Shadow private ItemStack offHandItem;

	private boolean pushed = false;

	@Inject(
		method = "renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/block/model/ItemTransforms$TransformType;Z)V", 
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
		method = "renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/block/model/ItemTransforms$TransformType;Z)V", 
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
		oMainHandHeight = 1f;
		oOffHandHeight = 1f;
		mainHandHeight = 1f;
		offHandHeight = 1f;

		mainHandItem = Minecraft.getInstance().player.getMainHandItem();
		offHandItem = Minecraft.getInstance().player.getOffhandItem();

		callbackInfo.cancel();
	}
}