package com.soytutta.mynethersdelight.common.tag;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

//? if >=1.21.11 {
//import vectorwing.farmersdelight.common.tag.CommonTags;
//?} else {
import vectorwing.farmersdelight.common.tag.CommonTags;
//?}

public final class MNDCommonTags {
	//? if >=1.21.11 <26.1.2 {
	/*public static final TagKey<Item> FOODS_COOKED_EGG = CommonTags.FOODS_COOKED_EGG;
	public static final TagKey<Item> FOODS_COOKED_PORK = CommonTags.FOODS_COOKED_PORK;
	public static final TagKey<Item> FOODS_DOUGH = CommonTags.FOODS_DOUGH;
	public static final TagKey<Item> FOODS_PASTA = CommonTags.FOODS_PASTA;
	public static final TagKey<Item> FOODS_RAW_CHICKEN = CommonTags.FOODS_RAW_CHICKEN;
	public static final TagKey<Item> FOODS_RAW_PORK = CommonTags.FOODS_RAW_PORK;
	public static final TagKey<Item> FOODS_ONION = CommonTags.FOODS_ONION;
	public static final TagKey<Item> CROPS_ONION = CommonTags.CROPS_ONION;
	public static final TagKey<Item> CROPS_RICE = CommonTags.CROPS_RICE;
	*///?} else {
	public static final TagKey<Item> FOODS_COOKED_EGG = CommonTags.Items.FOODS_COOKED_EGG;
	public static final TagKey<Item> FOODS_COOKED_PORK = CommonTags.Items.FOODS_COOKED_PORK;
	public static final TagKey<Item> FOODS_DOUGH = CommonTags.Items.FOODS_DOUGH;
	public static final TagKey<Item> FOODS_PASTA = CommonTags.Items.FOODS_PASTA;
	public static final TagKey<Item> FOODS_RAW_CHICKEN = CommonTags.Items.FOODS_RAW_CHICKEN;
	public static final TagKey<Item> FOODS_RAW_PORK = CommonTags.Items.FOODS_RAW_PORK;
	public static final TagKey<Item> FOODS_ONION = CommonTags.Items.FOODS_ONION;
	public static final TagKey<Item> CROPS_ONION = CommonTags.Items.CROPS_ONION;
	public static final TagKey<Item> CROPS_RICE = CommonTags.Items.CROPS_RICE;
	//?}

	private MNDCommonTags() {
	}
}
