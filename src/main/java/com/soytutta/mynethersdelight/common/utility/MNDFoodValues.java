package com.soytutta.mynethersdelight.common.utility;

import com.soytutta.mynethersdelight.common.registry.MNDEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import vectorwing.farmersdelight.common.registry.ModEffects;

//? if >=1.21.11 {
/*import net.minecraft.world.food.FoodConstants;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
*///?}

public class MNDFoodValues {
	public static final int BRIEF_DURATION = 600;
	public static final int SHORT_DURATION = 1200;
	public static final int MODERATE_DURATION = 2400;
	public static final int MEDIUM_DURATION = 3600;
	public static final int LONG_DURATION = 6000;

	//? if >=1.21.11 {
	/*public static Item.Properties properties(MNDFood food) {
		Consumable.Builder consumable = Consumable.builder();
		for (MNDFood.Effect effect : food.effects()) {
			consumable.onConsume(new ApplyStatusEffectsConsumeEffect(effect.instance(), effect.probability()));
		}
		if (food.fast()) {
			consumable.consumeSeconds(0.8F);
		}

		return new Item.Properties().food(
				new FoodProperties(food.nutrition(), FoodConstants.saturationByModifier(food.nutrition(), food.saturationModifier()), food.alwaysEdible()),
				consumable.build()
		);
	}
	*///?} else {
	public static Item.Properties properties(MNDFood food) {
		FoodProperties.Builder builder = new FoodProperties.Builder()
				.nutrition(food.nutrition())
				.saturationModifier(food.saturationModifier());
		if (food.alwaysEdible()) {
			builder.alwaysEdible();
		}
		if (food.fast()) {
			builder.fast();
		}
		for (MNDFood.Effect effect : food.effects()) {
			builder.effect(effect.instance(), effect.probability());
		}

		return new Item.Properties().food(builder.build());
	}
	//?}

	// STRIDER
	public static final MNDFood STRIDER_EGG = (new MNDFood.Builder())
			.nutrition(3).saturationModifier(0.3F)
			.alwaysEdible().build();
	public static final MNDFood ENCHANTED_GOLDEN_EGG = (new MNDFood.Builder())
			.nutrition(4).saturationModifier(1.0F)
			.effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0), 1.0F)
			.effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1.0F)
			.effect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 3), 1.0F)
			.effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 2), 1.0F)
			.alwaysEdible().build();
	public static final MNDFood GOLDEN_EGG = (new MNDFood.Builder())
			.nutrition(4).saturationModifier(1.0F)
			.effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 0), 1.0F)
			.effect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 0), 1.0F)
			.alwaysEdible().build();
	public static final MNDFood BOILED_EGG = (new MNDFood.Builder())
			.nutrition(4).saturationModifier(0.7F)
			.alwaysEdible().build();
	public static final MNDFood DEVILED_EGG = (new MNDFood.Builder())
			.nutrition(5).saturationModifier(0.7F)
			.effect(new MobEffectInstance(MNDEffects.BPUNGENT, 200, 0, false, false, true), 1.0F)
			.alwaysEdible().fast().build();
	public static final MNDFood SCOTCH_EGGS = (new MNDFood.Builder())
			.nutrition(12).saturationModifier(0.8F)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, MEDIUM_DURATION, 0, false, false), 1.0F)
			.build();
	public static final MNDFood EGG_SOUP = (new MNDFood.Builder())
			.nutrition(10).saturationModifier(0.7F)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, MODERATE_DURATION, 0, false, false), 1.0F)
			.build();

	public static final MNDFood STRIDER_SLICE = (new MNDFood.Builder())
			.nutrition(4).saturationModifier(0.7f)
			.effect(new MobEffectInstance(MobEffects.POISON, 200, 0), 0.15F)
			.build();
	public static final MNDFood MINCED_STRIDER = (new MNDFood.Builder())
			.nutrition(2).saturationModifier(0.75f)
			.build();
	public static final MNDFood BLEEDING_TARTAR = (new MNDFood.Builder())
			.nutrition(6).saturationModifier(0.75f)
			.effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0), 1.0F)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, BRIEF_DURATION, 0, false, false), 1.0F)
			.build();
	public static final MNDFood STRIDER_AND_GRILLED_FUNGUS = (new MNDFood.Builder())
			.nutrition(10).saturationModifier(0.9f)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, SHORT_DURATION, 0, false, false), 1.0F)
			.build();
	public static final MNDFood STRIDER_STEW = (new MNDFood.Builder())
			.nutrition(8).saturationModifier(0.6f)
			.effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 800, 0), 1.0F)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, SHORT_DURATION, 0, false, false), 1.0F)
			.build();
	public static final MNDFood CRIMSON_STROGANOFF = (new MNDFood.Builder())
			.nutrition(11).saturationModifier(0.75f)
			.effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0), 1.0F)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, MEDIUM_DURATION, 0, false, false), 1.0F)
			.build();
	public static final MNDFood STRIDERLOAF = (new MNDFood.Builder())
			.nutrition(5).saturationModifier(1.25f)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, SHORT_DURATION, 0, false, false), 1.0F)
			.build();
	public static final MNDFood COLD_STRIDERLOAF = (new MNDFood.Builder())
			.nutrition(9).saturationModifier(0.25f)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, BRIEF_DURATION, 0, false, false), 1.0F)
			.build();

	// HOGLIN
	public static final MNDFood HOGLIN_LOIN = (new MNDFood.Builder())
			.nutrition(4).saturationModifier(0.35f)
			.effect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0), 0.4F)
			.build();
	// HOGLIN-Sausage
	public static final MNDFood HOGLIN_SAUSAGE = (new MNDFood.Builder())
			.nutrition(2).saturationModifier(0.25f)
			.effect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0), 0.4F)
			.fast().build();
	public static final MNDFood ROASTED_SAUSAGE = (new MNDFood.Builder())
			.nutrition(4).saturationModifier(0.35f)
			.fast().build();
	public static final MNDFood HOTDOG = (new MNDFood.Builder())
			.nutrition(8).saturationModifier(0.45f)
			.build();
	public static final MNDFood HOTDOG_WITH_MIXED_SALAD = (new MNDFood.Builder())
			.nutrition(10).saturationModifier(0.5f)
			.effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1.0F)
			.build();
	public static final MNDFood HOTDOG_WITH_NETHER_SALAD = (new MNDFood.Builder())
			.nutrition(10).saturationModifier(0.5f)
			.effect(new MobEffectInstance(MobEffects.CONFUSION, 240, 0), 0.3F)
			.build();
	public static final MNDFood CHILIDOG = (new MNDFood.Builder())
			.nutrition(11).saturationModifier(0.5f)
			.effect(new MobEffectInstance(MNDEffects.GPUNGENT, 600, 0, false, false, true), 1.0F)
			.effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 300, 0), 1.0F)
			.build();
	public static final MNDFood SAUSAGE_AND_POTATOES = (new MNDFood.Builder())
			.nutrition(11).saturationModifier(0.55f)
			.build();
	public static final MNDFood BREAKFAST_SAMPLER = (new MNDFood.Builder())
			.nutrition(15).saturationModifier(0.75f)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, MODERATE_DURATION, 0, false, false), 1.0F)
			.build();
	// HOGLIN-Loin
	public static final MNDFood COOKED_LOIN = (new MNDFood.Builder())
			.nutrition(7).saturationModifier(0.75f)
			.build();
	public static final MNDFood NETHER_BURGER = (new MNDFood.Builder())
			.nutrition(12).saturationModifier(0.75f)
			.build();
	public static final MNDFood BLUE_TENDERLOIN_STEAK = (new MNDFood.Builder())
			.nutrition(9).saturationModifier(0.8f)
			.effect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0), 1.0F)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, SHORT_DURATION, 0, false, false), 1.0F)
			.build();
	public static final MNDFood RED_LOIN_STICK = (new MNDFood.Builder())
			.nutrition(10).saturationModifier(0.7F)
			.build();
	public static final MNDFood BACON_WRAPPED_SAUSAGE_STICK = (new MNDFood.Builder())
			.nutrition(10).saturationModifier(0.6F)
			.build();
	public static final MNDFood FRIED_HOGLIN_CHOP = (new MNDFood.Builder())
			.nutrition(12).saturationModifier(0.9f)
			.effect(new MobEffectInstance(MNDEffects.BPUNGENT, 1200, 0, false, false, true), 1.0F)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, MEDIUM_DURATION, 0, false, false), 1.0F)
			.build();

	// GHASTA
	public static final MNDFood GHASTA = (new MNDFood.Builder())
			.nutrition(3).saturationModifier(1.0F)
			.effect(new MobEffectInstance(MobEffects.HUNGER, 600, 0), 0.3F)
			.build();
	public static final MNDFood TWISTED_GHASTA = (new MNDFood.Builder())
			.nutrition(10).saturationModifier(0.65F)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, MODERATE_DURATION, 0, false, false), 1.0F)
			.build();
	public static final MNDFood GIANT_TAKOYAKI = (new MNDFood.Builder())
			.nutrition(12).saturationModifier(0.75F)
			.build();
	public static final MNDFood FRIES_GHASTA = (new MNDFood.Builder())
			.nutrition(8).saturationModifier(0.6F)
			.alwaysEdible().fast().build();
	public static final MNDFood GHAST_SALAD = (new MNDFood.Builder())
			.nutrition(8).saturationModifier(0.85F)
			.effect(new MobEffectInstance(MobEffects.REGENERATION, 300, 0), 1.0F)
			.build();
	public static final MNDFood DRIED_GHAST_WITH_MILK = (new MNDFood.Builder())
			.nutrition(6).saturationModifier(0.45F)
			.alwaysEdible().fast().build();
	public static final MNDFood SIZZLING_PUDDING = (new MNDFood.Builder())
			.nutrition(9).saturationModifier(0.65F)
			.effect(new MobEffectInstance(MNDEffects.BPUNGENT, 100, 0, false, false, true), 1.0F)
			.build();
	public static final MNDFood TEAR_POPSICLE = (new MNDFood.Builder())
			.nutrition(3).saturationModifier(0.2F)
			.effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 1, false, false), 1.0F)
			.alwaysEdible().fast().build();
	public static final MNDFood SPICY_NOODLE_SOUP = (new MNDFood.Builder())
			.nutrition(16).saturationModifier(0.65F)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, LONG_DURATION, 0, false, false), 1.0F)
			.effect(new MobEffectInstance(MNDEffects.BPUNGENT, 1200, 1, false, false, true), 1.0F)
			.build();
	public static final MNDFood SPICY_COTTON = (new MNDFood.Builder())
			.nutrition(3).saturationModifier(2.25F)
			.effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 300, 0), 1.0F)
			.alwaysEdible().build();
	public static final MNDFood GHASTA_WITH_CREAM = (new MNDFood.Builder())
			.nutrition(8).saturationModifier(0.9F)
			.effect(new MobEffectInstance(MNDEffects.BPUNGENT, 600, 0, false, false, true), 1.0F)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, SHORT_DURATION, 0, false, false), 1.0F)
			.build();
	public static final MNDFood SLICES_OF_BREAD = (new MNDFood.Builder())
			.nutrition(5).saturationModifier(0.6F)
			.build();
	public static final MNDFood TOASTS = (new MNDFood.Builder())
			.nutrition(7).saturationModifier(0.6F)
			.build();

	// SPICY
	public static final MNDFood BULLET_PEPPER = (new MNDFood.Builder())
			.nutrition(2).saturationModifier(0.5f)
			.effect(new MobEffectInstance(MNDEffects.BPUNGENT, 100, 2, false, false, true), 1.0F)
			.alwaysEdible().build();
	public static final MNDFood STUFFED_PEPPER = (new MNDFood.Builder())
			.nutrition(8).saturationModifier(0.8f)
			.effect(new MobEffectInstance(MNDEffects.BPUNGENT, 300, 2, false, false, true), 1.0F)
			.alwaysEdible().build();
	public static final MNDFood SPICY_SKEWER = (new MNDFood.Builder())
			.nutrition(7).saturationModifier(0.9f)
			.effect(new MobEffectInstance(MNDEffects.BPUNGENT, 1200, 0, false, false, true), 1.0F)
			.build();
	public static final MNDFood SPICY_HOGLIN_STEW = (new MNDFood.Builder())
			.nutrition(12).saturationModifier(0.8f)
			.effect(new MobEffectInstance(MNDEffects.BPUNGENT, 1200, 1, false, false, true), 1.0F)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, MEDIUM_DURATION, 0, false, false), 1.0F)
			.build();
	public static final MNDFood HOT_WINGS = (new MNDFood.Builder())
			.nutrition(6).saturationModifier(0.45f)
			.effect(new MobEffectInstance(MNDEffects.BPUNGENT, 1200, 0, false, false, true), 1.0F)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, 1200, 0, false, false), 1.0F)
			.alwaysEdible().fast().build();
	public static final MNDFood HOT_WINGS_BUCKET = (new MNDFood.Builder())
			.nutrition(18).saturationModifier(0.45f)
			.effect(new MobEffectInstance(MNDEffects.BPUNGENT, 3600, 0, false, false, true), 1.0F)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, MEDIUM_DURATION, 0, false, false), 1.0F)
			.alwaysEdible().build();
	public static final MNDFood SPICY_CURRY = (new MNDFood.Builder())
			.nutrition(15).saturationModifier(0.65f)
			.effect(new MobEffectInstance(MNDEffects.BPUNGENT, 6000, 1, false, false, true), 1.0F)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, MEDIUM_DURATION, 0, false, false), 1.0F)
			.build();

	// MAGMA CUBE
	public static final MNDFood ROCK_SOUP = (new MNDFood.Builder())
			.nutrition(7).saturationModifier(0.75f)
			.effect(new MobEffectInstance(MNDEffects.BPUNGENT, 1200, 2, false, false, true), 1.0F)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, MEDIUM_DURATION, 0, false, false), 1.0F)
			.build();
	public static final MNDFood BURNT_ROLL = (new MNDFood.Builder())
			.nutrition(10).saturationModifier(0.6f)
			.effect(new MobEffectInstance(MNDEffects.BPUNGENT, 600, 1, false, false, true), 1.0F)
			.alwaysEdible().build();
	public static final MNDFood MAGMA_CAKE_SLICE = (new MNDFood.Builder())
			.nutrition(2).saturationModifier(1.0f)
			.effect(new MobEffectInstance(MNDEffects.GPUNGENT, 400, 2, false, false, true), 1.0F)
			.effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 0, false, false), 1.0F)
			.effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0, false, false), 1.0F)
			.alwaysEdible().fast().build();

	// STUFFED HOGLIN
	public static final MNDFood ROAST_EAR = (new MNDFood.Builder())
			.nutrition(2).saturationModifier(0.5f)
			.alwaysEdible().fast().build();
	public static final MNDFood PLATE_OF_STUFFED_HOGLIN_SNOUT = (new MNDFood.Builder())
			.nutrition(14).saturationModifier(0.9f)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, LONG_DURATION, 0, false, false), 1.0F)
			.build();
	public static final MNDFood PLATE_OF_STUFFED_HOGLIN_HAM = (new MNDFood.Builder())
			.nutrition(12).saturationModifier(0.75f)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, MEDIUM_DURATION, 0, false, false), 1.0F)
			.build();
	public static final MNDFood PLATE_OF_STUFFED_HOGLIN = (new MNDFood.Builder())
			.nutrition(8).saturationModifier(0.6f)
			.effect(new MobEffectInstance(ModEffects.NOURISHMENT, MODERATE_DURATION, 0, false, false), 1.0F)
			.build();

	// LAVA
	public static final MNDFood HOT_CREAM_CONE = (new MNDFood.Builder())
			.nutrition(4).saturationModifier(1.0f)
			.effect(new MobEffectInstance(MNDEffects.GPUNGENT, 200, 0, false, false, true), 1.0F)
			.effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0), 1.0F)
			.alwaysEdible().build();
	public static final MNDFood HOT_CREAM = (new MNDFood.Builder())
			.nutrition(1).saturationModifier(8.0f)
			.effect(new MobEffectInstance(MNDEffects.GPUNGENT, 600, 2, false, false, true), 1.0F)
			.effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0), 1.0F)
			.alwaysEdible().build();
}
