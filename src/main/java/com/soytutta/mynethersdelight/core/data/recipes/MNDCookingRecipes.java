package com.soytutta.mynethersdelight.core.data.recipes;

import static com.soytutta.mynethersdelight.core.data.recipes.MNDRecipeBuilders.ingredient;
import static com.soytutta.mynethersdelight.core.data.recipes.MNDRecipeBuilders.shaped;
import static com.soytutta.mynethersdelight.core.data.recipes.MNDRecipeBuilders.shapeless;
import com.soytutta.mynethersdelight.common.tag.MNDCommonTags;

import com.soytutta.mynethersdelight.common.registry.MNDItems;
import com.soytutta.mynethersdelight.common.tag.MNDTags;
import com.soytutta.mynethersdelight.common.tag.MyCommonTags;
import net.fabricmc.fabric.api.recipe.v1.ingredient.DefaultCustomIngredients;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.crafting.Ingredient;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.CommonTags;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;

public class MNDCookingRecipes {
	public MNDCookingRecipes() {
	}

	private static CookingPotRecipeBuilder cookingPotRecipe(ItemLike result, int count, int cookingTime, float experience) {
		//? if >=1.21.11 {
		//return CookingPotRecipeBuilder.cookingPotRecipe(MNDRecipeBuilders.items(), result, count, cookingTime, experience);
		//?} else {
		return CookingPotRecipeBuilder.cookingPotRecipe(result, count, cookingTime, experience);
		//?}
	}

	private static CookingPotRecipeBuilder cookingPotRecipe(ItemLike result, int count, int cookingTime, float experience, ItemLike container) {
		//? if >=1.21.11 {
		//return CookingPotRecipeBuilder.cookingPotRecipe(MNDRecipeBuilders.items(), result, count, cookingTime, experience, container);
		//?} else {
		return CookingPotRecipeBuilder.cookingPotRecipe(result, count, cookingTime, experience, container);
		//?}
	}

	public static void register(RecipeOutput output) {
		cookMinecraftSoups(output);
		cookMeals(output);
	}

	private static void cookMinecraftSoups(RecipeOutput output) {
		cookingPotRecipe(MNDItems.STRIDER_STEW, 1, 400, 1.0F)
				.addIngredient(MyCommonTags.FOODS_RAW_STRIDER)
				.addIngredient(Ingredient.of(Items.WARPED_FUNGUS, Items.CRIMSON_FUNGUS))
				.addIngredient(Ingredient.of(Items.WARPED_FUNGUS, Items.CRIMSON_FUNGUS, Items.RED_MUSHROOM, Items.BROWN_MUSHROOM))
				.addIngredient(Ingredient.of(Items.WARPED_ROOTS, Items.CRIMSON_ROOTS, ModItems.STRAW.get()))
				.unlockedByAnyIngredient(Items.CRIMSON_FUNGUS, Items.WARPED_FUNGUS, MNDItems.STRIDER_SLICE, MNDItems.MINCED_STRIDER)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/strider_stew");
		cookingPotRecipe(MNDItems.SPICY_NOODLE_SOUP, 1, 200, 1.0F)
				.addIngredient(MyCommonTags.FOODS_RICE_PASTA)
				.addIngredient(MNDCommonTags.FOODS_COOKED_EGG)
				.addIngredient(MNDTags.HOT_SPICE)
				.addIngredient(MyCommonTags.FOODS_RAW_HOGLIN)
				.unlockedByAnyIngredient(MNDItems.GHASTA, MNDItems.BULLET_PEPPER, Items.PORKCHOP)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/spicy_noodle_soup");
		cookingPotRecipe(MNDItems.TWISTED_GHASTA, 1, 200, 1.0F)
				.addIngredient(MNDItems.GHASTA)
				.addIngredient(Ingredient.of(Items.WARPED_FUNGUS, Items.TWISTING_VINES), 2)
				.unlockedByAnyIngredient(MNDItems.GHASTA, Items.WARPED_FUNGUS, Items.TWISTING_VINES)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/twisted_ghasta");
		cookingPotRecipe(MNDItems.FRIES_GHASTA, 1, 100, 1.0F, Items.PAPER)
				.addIngredient(MNDItems.GHASTA, 2)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/fries_ghasta");
		cookingPotRecipe(MNDItems.GIANT_TAKOYAKI, 1, 200, 1.0F)
				.addIngredient(MyCommonTags.FOODS_GIANT_TENTACLES)
				.addIngredient(MyCommonTags.FOODS_GIANT_TENTACLES)
				.addIngredient(MNDCommonTags.FOODS_DOUGH)
				.addIngredient(ModItems.ONION.get())
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/giant_tentacles");
		cookingPotRecipe(MNDItems.SPICY_HOGLIN_STEW, 1, 200, 1.0F)
				.addIngredient(Ingredient.of(MNDItems.HOGLIN_LOIN, MNDItems.HOGLIN_SAUSAGE, MNDItems.ROASTED_SAUSAGE, MNDItems.COOKED_LOIN))
				.addIngredient(ConventionalItemTags.POTATO_CROPS)
				.addIngredient(ConventionalItemTags.CARROT_CROPS)
				.addIngredient(Ingredient.of(MNDItems.BULLET_PEPPER, MNDItems.PEPPER_POWDER))
				.unlockedByAnyIngredient(MNDItems.HOGLIN_LOIN, MNDItems.BULLET_PEPPER)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/spicy_hoglin_stew");
	}

	private static void cookMeals(RecipeOutput output) {
		cookingPotRecipe(MNDItems.BOILED_EGG, 4, 200, 1.0F)
				.addIngredient(ingredient(MNDTags.BOILED_EGG_CANDIDATE), 4)
				.unlockedByAnyIngredient(Items.EGG)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/boiled_eggs");
		cookingPotRecipe(MNDItems.SCOTCH_EGGS, 1, 200, 1.0F)
				.addIngredient(ingredient(MyCommonTags.FOODS_BOILED_EGG), 2)
				.addIngredient(Ingredient.of(ModItems.MINCED_BEEF.get(), MNDItems.HOGLIN_SAUSAGE))
				.addIngredient(ConventionalItemTags.BREAD_FOODS)
				.unlockedByAnyIngredient(Items.EGG)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/scotch_eggs");
		cookingPotRecipe(MNDItems.EGG_SOUP, 1, 200, 1.0F)
				.addIngredient(MyCommonTags.FOODS_BOILED_EGG)
				.addIngredient(ingredient(MNDCommonTags.FOODS_COOKED_EGG), 2)
				.addIngredient(MNDCommonTags.FOODS_ONION)
				.unlockedByAnyIngredient(Items.EGG)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/egg_soup");
		cookingPotRecipe(MNDItems.DEVILED_EGG, 2, 100, 1.0F)
				.addIngredient(MyCommonTags.FOODS_BOILED_EGG)
				.addIngredient(MNDTags.HOT_SPICE)
				.addIngredient(Ingredient.of(MNDItems.HOGLIN_SAUSAGE, ModItems.BACON.get()))
				.unlockedByAnyIngredient(MNDItems.STRIDER_EGG, MNDItems.BOILED_EGG)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/deviled_egg");
		cookingPotRecipe(MNDItems.STRIDER_WITH_GRILLED_FUNGUS, 1, 400, 1.0F)
				.addIngredient(MNDItems.STRIDER_SLICE)
				.addIngredient(Items.CRIMSON_FUNGUS, 1)
				.addIngredient(Items.WARPED_FUNGUS, 1)
				.addIngredient(Ingredient.of(Items.WARPED_FUNGUS, Items.CRIMSON_FUNGUS, Items.RED_MUSHROOM, Items.BROWN_MUSHROOM))
				.unlockedByAnyIngredient(Items.CRIMSON_FUNGUS, Items.WARPED_FUNGUS, MNDItems.STRIDER_SLICE, MNDItems.MINCED_STRIDER)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/strider_and_grilled_fungus");


		cookingPotRecipe(MNDItems.CRIMSON_STROGANOFF, 1, 400, 1.0F)
				.addIngredient(MNDItems.MINCED_STRIDER)
				.addIngredient(Items.CRIMSON_FUNGUS, 2)
				.addIngredient(ConventionalItemTags.MILK_DRINKS)
				.addIngredient(MNDCommonTags.FOODS_PASTA)
				.unlockedByAnyIngredient(MNDItems.MINCED_STRIDER, Items.CRIMSON_FUNGUS)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/crimson_stroganoff");

		cookingPotRecipe(MNDItems.FRIED_HOGLIN_CHOP, 1, 200, 1.0F)
				.addIngredient(MNDItems.HOGLIN_LOIN)
				.addIngredient(Items.WHEAT)
				.addIngredient(ConventionalItemTags.MILK_DRINKS)
				.addIngredient(ConventionalItemTags.EGGS)
				.addIngredient(MNDItems.BULLET_PEPPER)
				.unlockedByAnyIngredient(MNDItems.HOGLIN_LOIN, MNDItems.BULLET_PEPPER)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/fried_hoglin_chop");

		cookingPotRecipe(MNDItems.HOT_WINGS, 1, 150, 0.35F)
				.addIngredient(MNDCommonTags.FOODS_RAW_CHICKEN)
				.addIngredient(MNDTags.HOT_SPICE)
				.addIngredient(MNDCommonTags.CROPS_ONION)
				.unlockedByAnyIngredient(MNDItems.BULLET_PEPPER)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/hot_wings");

		cookingPotRecipe(MNDItems.SPICY_CURRY, 1, 200, 1.0F)
				.addIngredient(ConventionalItemTags.RAW_MEAT_FOODS)
				.addIngredient(ConventionalItemTags.MILK_DRINKS)
				.addIngredient(MNDTags.HOT_SPICE)
				.addIngredient(MNDCommonTags.CROPS_RICE)
				.addIngredient(vegetablesPatch())
				.addIngredient(Ingredient.of(Items.PUMPKIN, ModItems.PUMPKIN_SLICE.get()))
				.unlockedByAnyIngredient(MNDItems.BULLET_PEPPER)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/spicy_curry");

		cookingPotRecipe(MNDItems.ROAST_STUFFED_HOGLIN, 1, 700, 2.0F, Items.BOWL)
				.addIngredient(MNDTags.HOT_SPICE)
				.addIngredient(MNDItems.RAW_STUFFED_HOGLIN)
				.addIngredient(Items.CRIMSON_FUNGUS)
				.addIngredient(ModItems.NETHER_SALAD.get(), 2)
				.unlockedByAnyIngredient(MNDItems.RAW_STUFFED_HOGLIN)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/roast_stuffed_hoglin");

		cookingPotRecipe(MNDItems.CHILIDOG, 1, 200, 0.35F, MNDItems.HOTDOG)
				.addIngredient(MNDTags.HOT_SPICE)
				.addIngredient(Ingredient.of(MNDItems.MINCED_STRIDER, ModItems.MINCED_BEEF.get()))
				.addIngredient(Items.NETHER_WART)
				.unlockedByAnyIngredient(MNDItems.HOTDOG)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/chilidog");
		cookingPotRecipe(MNDItems.SAUSAGE_AND_POTATOES, 1, 200, 0.35F)
				.addIngredient(ConventionalItemTags.POTATO_CROPS)
				.addIngredient(MNDItems.HOGLIN_SAUSAGE, 2)
				.unlockedByAnyIngredient(MNDItems.HOGLIN_SAUSAGE)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/sausage_and_potatoes");

		cookingPotRecipe(MNDItems.HOT_CREAM, 1, 50, 1.0F, Items.BUCKET)
				.addIngredient(MNDTags.HOT_SPICE)
				.addIngredient(ConventionalItemTags.EGGS)
				.addIngredient(Ingredient.of(MNDItems.BULLET_PEPPER, MNDItems.PEPPER_POWDER))
				.addIngredient(Items.MAGMA_CREAM)
				.addIngredient(Items.LAVA_BUCKET)
				.addIngredient(Items.MAGMA_CREAM)

				.unlockedByAnyIngredient(Items.LAVA_BUCKET)
				.setRecipeBookTab(CookingPotRecipeBookTab.DRINKS)
				.save(output, "mynethersdelight:cooking/hotcream");

		cookingPotRecipe(MNDItems.ROCK_SOUP, 1, 50, 0.35F)
				.addIngredient(Items.MAGMA_CREAM, 2)
				.addIngredient(Ingredient.of(MNDItems.STRIDER_ROCK, MNDItems.STRIDER_EGG), 2)

				.unlockedByAnyIngredient(Items.MAGMA_CREAM)
				.setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
				.save(output, "mynethersdelight:cooking/rock_soup");
		cookingPotRecipe(MNDItems.BURNT_ROLL, 2, 50, 0.35F)
				.addIngredient(Items.MAGMA_CREAM, 2)
				.addIngredient(ConventionalItemTags.RAW_MEAT_FOODS)

				.unlockedByAnyIngredient(Items.MAGMA_CREAM)
				.setRecipeBookTab(CookingPotRecipeBookTab.MISC)
				.save(output, "mynethersdelight:cooking/burn_roll");
	}

	private static Ingredient vegetablesPatch() {
		return DefaultCustomIngredients.difference(ingredient(ConventionalItemTags.VEGETABLE_FOODS), Ingredient.of(Items.MELON_SLICE));
	}
}
