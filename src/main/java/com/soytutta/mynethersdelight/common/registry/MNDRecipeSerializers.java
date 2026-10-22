package com.soytutta.mynethersdelight.common.registry;

import com.soytutta.mynethersdelight.MyNethersDelight;
import com.soytutta.mynethersdelight.common.crafting.BlazierTemperatureRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;

//? if >=1.21.11 {
//import net.minecraft.world.item.crafting.CustomRecipe;
//?} else {
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
//?}

public class MNDRecipeSerializers {

	//? if >=26.1.2 {
	/*public static final RecipeSerializer<BlazierTemperatureRecipe> BLAZIER_HEATING = Registry.register(
			BuiltInRegistries.RECIPE_SERIALIZER, MyNethersDelight.id("blazier_heating"),
			new RecipeSerializer<>(BlazierTemperatureRecipe.HEATING_MAP_CODEC, BlazierTemperatureRecipe.HEATING_STREAM_CODEC));

	public static final RecipeSerializer<BlazierTemperatureRecipe> BLAZIER_COOLING = Registry.register(
			BuiltInRegistries.RECIPE_SERIALIZER, MyNethersDelight.id("blazier_cooling"),
			new RecipeSerializer<>(BlazierTemperatureRecipe.COOLING_MAP_CODEC, BlazierTemperatureRecipe.COOLING_STREAM_CODEC));
	*///?} else if >=1.21.11 {
	/*public static final RecipeSerializer<BlazierTemperatureRecipe> BLAZIER_HEATING = Registry.register(
			BuiltInRegistries.RECIPE_SERIALIZER, MyNethersDelight.id("blazier_heating"),
			new CustomRecipe.Serializer<>(BlazierTemperatureRecipe::heating));

	public static final RecipeSerializer<BlazierTemperatureRecipe> BLAZIER_COOLING = Registry.register(
			BuiltInRegistries.RECIPE_SERIALIZER, MyNethersDelight.id("blazier_cooling"),
			new CustomRecipe.Serializer<>(BlazierTemperatureRecipe::cooling));
	*///?} else {
	public static final RecipeSerializer<BlazierTemperatureRecipe> BLAZIER_HEATING = Registry.register(
			BuiltInRegistries.RECIPE_SERIALIZER, MyNethersDelight.id("blazier_heating"),
			new SimpleCraftingRecipeSerializer<>(BlazierTemperatureRecipe::heating));

	public static final RecipeSerializer<BlazierTemperatureRecipe> BLAZIER_COOLING = Registry.register(
			BuiltInRegistries.RECIPE_SERIALIZER, MyNethersDelight.id("blazier_cooling"),
			new SimpleCraftingRecipeSerializer<>(BlazierTemperatureRecipe::cooling));
	//?}

	public static void initialize() {

	}
}
