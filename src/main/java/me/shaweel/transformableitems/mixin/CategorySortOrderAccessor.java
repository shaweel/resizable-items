package me.shaweel.transformableitems.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.KeyMapping;

@Mixin(KeyMapping.class)
public interface CategorySortOrderAccessor {
	@Accessor("CATEGORY_SORT_ORDER")
	static Map<String, Integer> getCategorySortOrder() {
		throw new AssertionError();
	}
}