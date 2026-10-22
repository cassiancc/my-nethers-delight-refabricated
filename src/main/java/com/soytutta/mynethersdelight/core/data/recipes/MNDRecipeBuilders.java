package com.soytutta.mynethersdelight.core.data.recipes;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SingleItemRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
//? if >=26.1.2 {
//import net.minecraft.world.item.crafting.CookingBookCategory;
//?}
import net.minecraft.world.level.ItemLike;

import java.util.Arrays;

/**
 * The recipe builders of the mod.
 * <p>
 * 1.21.11 asks the recipe builders for the holder getter of the items and no longer
 * accepts a tag key as an ingredient, this class hides both differences.
 */
public final class MNDRecipeBuilders {
	//? if >=1.21.11 {
	//private static HolderGetter<Item> items = BuiltInRegistries.ITEM;
	//? } else {
	private static HolderGetter<Item> items = null;
	//?}

	private MNDRecipeBuilders() {
	}

	/**
	 * Sets the holder getter of the items that is used to resolve the tags of the
	 * ingredients.
	 */
	public static void setItems(HolderGetter<Item> holderGetter) {
		items = holderGetter;
	}

	/** The holder getter of the items that is used to resolve the tags of the ingredients. */
	public static HolderGetter<Item> items() {
		return items;
	}

	public static ShapedRecipeBuilder shaped(RecipeCategory category, ItemLike result) {
		//? if >=1.21.11 {
		//return ShapedRecipeBuilder.shaped(items, category, result);
		//?} else {
		return ShapedRecipeBuilder.shaped(category, result);
		//?}
	}

	public static ShapedRecipeBuilder shaped(RecipeCategory category, ItemLike result, int count) {
		//? if >=1.21.11 {
		//return ShapedRecipeBuilder.shaped(items, category, result, count);
		//?} else {
		return ShapedRecipeBuilder.shaped(category, result, count);
		//?}
	}

	public static ShapelessRecipeBuilder shapeless(RecipeCategory category, ItemLike result) {
		//? if >=1.21.11 {
		//return ShapelessRecipeBuilder.shapeless(items, category, result);
		//?} else {
		return ShapelessRecipeBuilder.shapeless(category, result);
		//?}
	}

	public static ShapelessRecipeBuilder shapeless(RecipeCategory category, ItemLike result, int count) {
		//? if >=1.21.11 {
		//return ShapelessRecipeBuilder.shapeless(items, category, result, count);
		//?} else {
		return ShapelessRecipeBuilder.shapeless(category, result, count);
		//?}
	}

	/** An ingredient of all the items of the given tag. */
	public static Ingredient ingredient(TagKey<Item> tag) {
		//? if >=1.21.11 {
		//return Ingredient.of(items.get(tag).orElseThrow());
		//?} else {
		return Ingredient.of(tag);
		//?}
	}

	/**
	 * The items of the given tag, empty if the tag does not exist.
	 * <p>
	 * 1.21.11 keeps the items of a tag in a holder set that is only filled once the tag
	 * is bound, the tags of the mod itself are not bound while the data is generated.
	 */
	public static ItemLike[] tagItems(TagKey<Item> tag) {
		//? if >=1.21.11 {
		/*return items.get(tag)
				.filter(HolderSet.Named::isBound)
				.map(named -> named.stream().map(holder -> (ItemLike) holder.value()).toArray(ItemLike[]::new))
				.orElse(new ItemLike[0]);
		*///? } else {
		return Arrays.stream(Ingredient.of(tag).getItems()).map(ItemStack::getItem).toArray(ItemLike[]::new);
		//?}
	}

	/**
	 * A stonecutting recipe of a single item.
	 * <p>
	 * 26.1 asks the builder for the count of the result.
	 */
	public static SingleItemRecipeBuilder stonecutting(Ingredient ingredient, RecipeCategory category, ItemLike result) {
		//? if >=26.1.2 {
		//return SingleItemRecipeBuilder.stonecutting(ingredient, category, result, 1);
		//?} else {
		return SingleItemRecipeBuilder.stonecutting(ingredient, category, result);
		//?}
	}

	/**
	 * A smelting recipe of a food.
	 * <p>
	 * 26.1 asks the builder for the category of the recipe book.
	 */
	public static SimpleCookingRecipeBuilder smelting(Ingredient ingredient, RecipeCategory category, ItemLike result, float experience, int cookingTime) {
		//? if >=26.1.2 {
		//return SimpleCookingRecipeBuilder.smelting(ingredient, category, CookingBookCategory.FOOD, result, experience, cookingTime);
		//?} else {
		return SimpleCookingRecipeBuilder.smelting(ingredient, category, result, experience, cookingTime);
		//?}
	}
}
