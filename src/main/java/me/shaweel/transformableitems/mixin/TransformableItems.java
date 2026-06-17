package me.shaweel.transformableitems.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import me.shaweel.transformableitems.Config;

@Mixin(ItemInHandRenderer.class)
public class TransformableItems {
	@Shadow private float mainHandHeight;
	@Shadow private float offHandHeight;
	@Shadow private float oMainHandHeight;
	@Shadow private float oOffHandHeight;
	@Shadow private ItemStack mainHandItem;
	@Shadow private ItemStack offHandItem;
	
	@Inject(method = "renderItem", at = @At("HEAD"))
	private void transform(
		LivingEntity livingEntity,
		ItemStack itemStack,
		ItemDisplayContext itemDisplayContext,
		PoseStack poseStack,
		MultiBufferSource multiBufferSource,
		int i,
		CallbackInfo callbackInfo
	) {
		if (itemDisplayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND) {
			poseStack.translate(Config.configData.xOffset, Config.configData.yOffset, Config.configData.zOffset);
			poseStack.scale(Config.configData.xScale, Config.configData.yScale, Config.configData.zScale);
		} else if (itemDisplayContext == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND) {
			poseStack.translate(Config.configData.xOffset * -1, Config.configData.yOffset, Config.configData.zOffset);
			poseStack.scale(Config.configData.xScale, Config.configData.yScale, Config.configData.zScale);
		} else return;
	}

	@Inject(method = "tick", at = @At("HEAD"), cancellable = true)
	private void tick(CallbackInfo callbackInfo) {
		if (Config.configData.itemHeightAnimation) return;
		oMainHandHeight = 1f;
		oOffHandHeight = 1f;
		mainHandHeight = 1f;
		offHandHeight = 1f;

		mainHandItem = Minecraft.getInstance().player.getMainHandItem();
		offHandItem = Minecraft.getInstance().player.getOffhandItem();

		callbackInfo.cancel();
	}
}