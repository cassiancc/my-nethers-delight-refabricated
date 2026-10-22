package com.soytutta.mynethersdelight.core.data.recipes;

import static com.soytutta.mynethersdelight.core.data.recipes.MNDRecipeBuilders.ingredient;
import static com.soytutta.mynethersdelight.core.data.recipes.MNDRecipeBuilders.shaped;
import static com.soytutta.mynethersdelight.core.data.recipes.MNDRecipeBuilders.shapeless;
import com.soytutta.mynethersdelight.MyNethersDelight;
import com.soytutta.mynethersdelight.common.registry.MNDItems;
import com.soytutta.mynethersdelight.common.tag.MNDTags;
// 26.2 split the criterion package into the triggers and the predicates.
//? if >=26.2 {
//import net.minecraft.advancements.triggers.InventoryChangeTrigger;
//?} else {
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
//?}
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public class MNDSmeltingRecipes {

	public static void register(RecipeOutput output) {
		foodSmeltingRecipes("boiled_egg", ingredient(MNDTags.BOILED_EGG_CANDIDATE), MNDRecipeBuilders.tagItems(MNDTags.BOILED_EGG_CANDIDATE), MNDItems.BOILED_EGG, 0.35F, output);
		foodSmeltingRecipes("hoglin_loin", Ingredient.of(MNDItems.HOGLIN_LOIN), new ItemLike[] { MNDItems.HOGLIN_LOIN }, MNDItems.COOKED_LOIN, 0.35F, output);
		foodSmeltingRecipes("hoglin_sausage", Ingredient.of(MNDItems.HOGLIN_SAUSAGE), new ItemLike[] { MNDItems.HOGLIN_SAUSAGE }, MNDItems.ROASTED_SAUSAGE, 0.35F, output);

		MNDRecipeBuilders.smelting(Ingredient.of(MNDItems.GHAST_DOUGH), RecipeCategory.FOOD,
						Items.BREAD, 0.35F, 200)
				.unlockedBy("has_dough", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.GHAST_DOUGH))
				.save(output, MyNethersDelight.id("bread") + "_from_smelting");
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(MNDItems.GHAST_DOUGH), RecipeCategory.FOOD,
						Items.BREAD, 0.35F, 100)
				.unlockedBy("has_dough", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.GHAST_DOUGH))
				.save(output, MyNethersDelight.id("bread") + "_from_smoking");

		MNDRecipeBuilders.smelting(Ingredient.of(MNDItems.GHAST_SOURDOUGH), RecipeCategory.FOOD,
						MNDItems.BREAD_LOAF_BLOCK, 0.35F, 400)
				.unlockedBy("has_dough", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.GHAST_SOURDOUGH))
				.save(output, MyNethersDelight.id("bread_loaf") + "_from_smelting");
		SimpleCookingRecipeBuilder.smoking(Ingredient.of(MNDItems.GHAST_SOURDOUGH), RecipeCategory.FOOD,
						MNDItems.BREAD_LOAF_BLOCK, 0.35F, 200)
				.unlockedBy("has_dough", InventoryChangeTrigger.TriggerInstance.hasItems(MNDItems.GHAST_SOURDOUGH))
				.save(output, MyNethersDelight.id("bread_loaf") + "_from_smoking");
		foodSmeltingRecipes("toast", Ingredient.of(MNDItems.SLICES_OF_BREAD), new ItemLike[] { MNDItems.SLICES_OF_BREAD }, MNDItems.TOASTS, 0.15F, output);

	}

	private static void foodSmeltingRecipes(String name, Ingredient ingredient, ItemLike[] items, ItemLike result, float experience, RecipeOutput output) {
		String namePrefix = ResourceLocation.fromNamespaceAndPath("mynethersdelight", name).toString();

		RecipeBuilder smeltingRecipe = MNDRecipeBuilders.smelting(ingredient, RecipeCategory.FOOD, result, experience, 200)
				.unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(items));
		smeltingRecipe.save(output, namePrefix + "_cooking");

		RecipeBuilder campfireRecipe = SimpleCookingRecipeBuilder.campfireCooking(ingredient, RecipeCategory.FOOD, result, experience, 600)
				.unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(items));
		campfireRecipe.save(output, namePrefix + "_from_campfire_cooking");

		RecipeBuilder smokingRecipe = SimpleCookingRecipeBuilder.smoking(ingredient, RecipeCategory.FOOD, result, experience, 100)
				.unlockedBy(name, InventoryChangeTrigger.TriggerInstance.hasItems(items));
		smokingRecipe.save(output, namePrefix + "_from_smoking");
	}
}
