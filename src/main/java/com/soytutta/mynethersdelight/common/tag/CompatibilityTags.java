package com.soytutta.mynethersdelight.common.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class CompatibilityTags {
	public static final TagKey<Item> HORROR_LASAGNA_MEATS = externalItemTag("brewinandchewin", "foods/horror_meat");
	public static final TagKey<Item> RAW_MEATS = externalItemTag("brewinandchewin", "foods/jerky_meat");

	public CompatibilityTags() {
	}

	private static TagKey<Item> externalItemTag(String modId, String path) {
		return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(modId, path));
	}
}
