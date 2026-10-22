package com.soytutta.mynethersdelight.core.data.recipes;

import static com.soytutta.mynethersdelight.core.data.recipes.MNDRecipeBuilders.ingredient;

import com.soytutta.mynethersdelight.common.registry.MNDItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.common.crafting.ingredient.ItemAbilityIngredient;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;
import vectorwing.farmersdelight.refabricated.ItemAbility;

public class MNDCuttingRecipes {
	//? if >=1.21.11 {
	/*// Farmers Delight 3.4 no longer exposes the tool tags of its cutting recipes
	private static final Ingredient KNIVES = ingredient(vectorwing.farmersdelight.common.tag.CommonTags.TOOLS_KNIFE);
	private static final Ingredient AXES = ingredient(net.minecraft.tags.ItemTags.AXES);
	private static final Ingredient AXES_STRIP = ingredient(net.minecraft.tags.ItemTags.AXES);
	*///? } else {
	private static final Ingredient KNIVES = vectorwing.farmersdelight.data.recipe.CuttingRecipes.KNIVES;
	private static final Ingredient AXES = vectorwing.farmersdelight.data.recipe.CuttingRecipes.AXES;
	private static final Ingredient AXES_STRIP = vectorwing.farmersdelight.data.recipe.CuttingRecipes.AXES_STRIP;
	//?}

	public static void register(RecipeOutput output) {
		cuttingAnimalItems(output);
		strippingWood(output);
		salvagingWoodenFurniture(output);
		cuttingVegetables(output);
		cuttingFoods(output);
	}

	private static void cuttingAnimalItems(RecipeOutput output) {
		saveCutting(output, "mynethersdelight:cutting/ghasmati",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MNDItems.GHASTA),
						KNIVES,
						MNDItems.GHASMATI)
				.addResultWithChance(MNDItems.GHASMATI, 0.05F));
		saveCutting(output, "mynethersdelight:cutting/strider_egg",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MNDItems.STRIDER_ROCK),
						new ItemAbilityIngredient(ItemAbility.PICKAXE_DIG).toVanilla(),
						MNDItems.STRIDER_EGG)
				.addResultWithChance(Items.BONE_MEAL, 0.25F));
		saveCutting(output, "mynethersdelight:cutting/minced_strider",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MNDItems.STRIDER_SLICE),
						KNIVES,
						MNDItems.MINCED_STRIDER, 2)
				.addResult(Items.STRING)
				.addResultWithChance(Items.STRING, 0.5F, 2));
		saveCutting(output, "mynethersdelight:cutting/hoglin_sausage",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MNDItems.HOGLIN_LOIN),
						KNIVES,
						MNDItems.HOGLIN_SAUSAGE, 2));
		saveCutting(output, "mynethersdelight:cutting/hoglin_hide",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MNDItems.HOGLIN_HIDE),
						KNIVES,
						Items.LEATHER, 4)
				.addResultWithChance(Items.LEATHER, 0.5F, 2));
		saveCutting(output, "mynethersdelight:cutting/balze_rod",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.BLAZE_ROD),
						KNIVES,
						Items.BLAZE_POWDER, 3)
				.addResultWithChance(Items.BLAZE_POWDER, 0.25F, 1));

		saveCutting(output, "mynethersdelight:cutting/skoglin_trophy",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MNDItems.HOGLIN_TROPHY),
						KNIVES,
						MNDItems.SKOGLIN_TROPHY)
				.addResult(Items.LEATHER, 2)
				.addResultWithChance(Items.LEATHER, 0.5F));
		saveCutting(output, "mynethersdelight:cutting/skoglin_trophy_alt",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MNDItems.ZOGLIN_TROPHY),
						KNIVES,
						MNDItems.SKOGLIN_TROPHY)
				.addResult(Items.ROTTEN_FLESH, 2)
				.addResultWithChance(Items.ROTTEN_FLESH, 0.5F));

		saveCutting(output, "mynethersdelight:cutting/hoglin_trophy",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MNDItems.WAXED_HOGLIN_TROPHY),
						new ItemAbilityIngredient(ItemAbility.AXE_STRIP).toVanilla(),
						MNDItems.HOGLIN_TROPHY));
	}

	private static void strippingWood(RecipeOutput output) {
		stripLogForBark(output, MNDItems.BLOCK_OF_POWDERY_CANNON, MNDItems.BLOCK_OF_STRIPPED_POWDERY_CANNON);
	}

	private static void salvagingWoodenFurniture(RecipeOutput output) {
		salvagePlankFromFurniture(output, MNDItems.POWDERY_PLANKS, MNDItems.POWDERY_DOOR, MNDItems.POWDERY_TRAPDOOR, MNDItems.POWDERY_SIGN, MNDItems.POWDERY_HANGING_SIGN, MNDItems.POWDERY_FENCE, MNDItems.POWDERY_FENCE_GATE, MNDItems.POWDERY_PRESSURE_PLATE, MNDItems.POWDERY_BUTTON, MNDItems.POWDERY_CABINET);
	}

	private static void cuttingVegetables(RecipeOutput output) {
		saveCutting(output, "farmersdelight:cutting/pumpkin_slice_alt",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.CARVED_PUMPKIN),
						KNIVES,
						ModItems.PUMPKIN_SLICE.get(), 1)
				.addResultWithChance(Items.PUMPKIN_SEEDS, 0.25F, 1));
		saveCutting(output, "mynethersdelight:cutting/gunpowder_and_stick_cannon",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MNDItems.POWDER_CANNON),
						KNIVES,
						Items.STICK, 1)
				.addResultWithChance(Items.GUNPOWDER, 0.25F, 1));
		saveCutting(output, "mynethersdelight:cutting/stick_bamboo",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(Items.BAMBOO),
						KNIVES,
						Items.STICK, 1));
		saveCutting(output, "mynethersdelight:cutting/bullet_pepper",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MNDItems.BULLET_PEPPER),
						KNIVES,
						MNDItems.PEPPER_POWDER, 1)
				.addResultWithChance(MNDItems.PEPPER_POWDER, 0.25F, 1));
		saveCutting(output, "mynethersdelight:cutting/crimson_fungus",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MNDItems.CRIMSON_FUNGUS_COLONY),
						KNIVES,
						Items.CRIMSON_FUNGUS, 5));
		saveCutting(output, "mynethersdelight:cutting/warped_fungus",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MNDItems.WARPED_FUNGUS_COLONY),
						KNIVES,
						Items.WARPED_FUNGUS, 5));
	}

	private static void cuttingFoods(RecipeOutput output) {
		saveCutting(output, "mynethersdelight:cutting/magma_cake",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MNDItems.MAGMA_CAKE),
						KNIVES,
						MNDItems.MAGMA_CAKE_SLICE, 7));
		saveCutting(output, "mynethersdelight:cutting/slices_of_bread",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MNDItems.BREAD_LOAF_BLOCK),
						KNIVES,
						MNDItems.SLICES_OF_BREAD, 5));
		saveCutting(output, "mynethersdelight:cutting/ghast_dough",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(MNDItems.GHAST_SOURDOUGH),
						KNIVES,
						MNDItems.GHAST_DOUGH, 3));
	}

	private static void stripLogForBark(RecipeOutput output, ItemLike log, ItemLike strippedLog) {
		saveCutting(output, "farmersdelight:cutting/" + BuiltInRegistries.ITEM.getKey(log.asItem()).getPath(),
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(log), AXES_STRIP, strippedLog)
				.addResult(ModItems.STRAW.get())
				.addSound(SoundEvents.AXE_STRIP));
	}

	private static void salvagePlankFromFurniture(RecipeOutput output, ItemLike plank, ItemLike... furniture) {
		//? if >=1.21.11 {
		/*saveCutting(output, "mynethersdelight:salvaging/powdery_furniture",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(furniture), AXES, plank, 1, 75));
		*///? } else {
		saveCutting(output, "mynethersdelight:salvaging/powdery_furniture",
				CuttingBoardRecipeBuilder.cuttingRecipe(Ingredient.of(furniture), AXES, plank, 1, 0.75F));
		//?}
	}

	private static void saveCutting(RecipeOutput output, String id, CuttingBoardRecipeBuilder builder) {
		//? if >=1.21.11 {
		//builder.save(output, ResourceKey.create(Registries.RECIPE, ResourceLocation.parse(id)));
		//? } else {
		builder.build(output, id);
		//?}
	}
}
