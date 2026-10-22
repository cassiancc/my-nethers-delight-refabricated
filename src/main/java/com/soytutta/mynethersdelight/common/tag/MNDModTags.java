package com.soytutta.mynethersdelight.common.tag;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

//? if >=1.21.11 {
//import vectorwing.farmersdelight.common.tag.ModTags;
//?} else {
import vectorwing.farmersdelight.common.tag.ModTags;
//?}

public final class MNDModTags {
	//? if >=1.21.11 <26.1.2 {
	/*public static final TagKey<Block> MINEABLE_WITH_KNIFE = ModTags.MINEABLE_WITH_KNIFE;
	public static final TagKey<Block> WILD_CROPS = ModTags.WILD_CROPS;
	public static final TagKey<Block> HEAT_SOURCES = ModTags.HEAT_SOURCES;
	public static final TagKey<Block> TRAY_HEAT_SOURCES = ModTags.TRAY_HEAT_SOURCES;
	public static final TagKey<Block> MUSHROOM_COLONY_GROWABLE_ON = ModTags.MUSHROOM_COLONY_GROWABLE_ON;
	public static final TagKey<Block> UNAFFECTED_BY_RICH_SOIL = ModTags.UNAFFECTED_BY_RICH_SOIL;
	public static final TagKey<Item> FEASTS = ModTags.FEASTS;
	public static final TagKey<Item> WILD_CROPS_ITEM = ModTags.WILD_CROPS_ITEM;
	public static final TagKey<Item> CABINETS = ModTags.CABINETS;
	public static final TagKey<Item> WOODEN_CABINETS = ModTags.WOODEN_CABINETS;
	public static final TagKey<Item> KNIVES = ModTags.KNIVES;
	// Farmers Delight 3.4 only ships the item variants of these tags, the mod keeps
	// block variants of its own.
	public static final TagKey<Block> CABINETS_BLOCK = MNDTags.Blocks.CABINETS;
	public static final TagKey<Block> FEASTS_BLOCK = MNDTags.Blocks.FEASTS;
	public static final TagKey<Block> WOODEN_CABINETS_BLOCK = MNDTags.Blocks.WOODEN_CABINETS;
	*///?} else {
	public static final TagKey<Block> MINEABLE_WITH_KNIFE = ModTags.Blocks.MINEABLE_WITH_KNIFE;
	public static final TagKey<Block> WILD_CROPS = ModTags.Blocks.WILD_CROPS;
	public static final TagKey<Block> HEAT_SOURCES = ModTags.Blocks.HEAT_SOURCES;
	public static final TagKey<Block> TRAY_HEAT_SOURCES = ModTags.Blocks.TRAY_HEAT_SOURCES;
	public static final TagKey<Block> MUSHROOM_COLONY_GROWABLE_ON = ModTags.Blocks.MUSHROOM_COLONY_GROWABLE_ON;
	public static final TagKey<Block> UNAFFECTED_BY_RICH_SOIL = ModTags.Blocks.UNAFFECTED_BY_RICH_SOIL;
	public static final TagKey<Item> FEASTS = ModTags.Items.FEASTS;
	public static final TagKey<Item> WILD_CROPS_ITEM = ModTags.Items.WILD_CROPS;
	public static final TagKey<Item> CABINETS = ModTags.Items.CABINETS;
	public static final TagKey<Item> WOODEN_CABINETS = ModTags.Items.CABINETS_WOODEN;
	public static final TagKey<Item> KNIVES = ModTags.Items.KNIVES;
	public static final TagKey<Block> CABINETS_BLOCK = ModTags.Blocks.CABINETS;
	public static final TagKey<Block> FEASTS_BLOCK = ModTags.Blocks.FEASTS;
	public static final TagKey<Block> WOODEN_CABINETS_BLOCK = ModTags.Blocks.CABINETS_WOODEN;
	//?}

	private MNDModTags() {
	}
}
