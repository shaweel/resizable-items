package me.shaweel.transformableitems.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.vertex.PoseStack;

import me.shaweel.transformableitems.ConfigFile;

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
		SubmitNodeCollector submitNodeCollector,
		int i,
		CallbackInfo callbackInfo
	) {
		if (itemDisplayContext == ItemDisplayContext.FIRST_PERSON_LEFT_HAND) {
			poseStack.translate(ConfigFile.configData.xOffset, ConfigFile.configData.yOffset, ConfigFile.configData.zOffset);
			poseStack.scale(ConfigFile.configData.xScale, ConfigFile.configData.yScale, ConfigFile.configData.zScale);
		} else if (itemDisplayContext == ItemDisplayContext.FIRST_PERSON_RIGHT_HAND) {
			poseStack.translate(ConfigFile.configData.xOffset * -1, ConfigFile.configData.yOffset, ConfigFile.configData.zOffset);
			poseStack.scale(ConfigFile.configData.xScale, ConfigFile.configData.yScale, ConfigFile.configData.zScale);
		} else return;
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