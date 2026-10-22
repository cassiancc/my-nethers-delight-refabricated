package com.soytutta.mynethersdelight.common.utility;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

//? if >=1.21.11 {
/*import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.item.consume_effects.ConsumeEffect;
*///? } else {
import net.minecraft.world.food.FoodProperties;
//?}

public final class MNDFoodEffects {
	private MNDFoodEffects() {
	}

	public static void apply(Level level, LivingEntity entity, ItemStack stack) {
		//? if >=1.21.11 {
		/*Consumable consumable = stack.get(DataComponents.CONSUMABLE);
		if (consumable != null) {
			for (ConsumeEffect effect : consumable.onConsumeEffects()) {
				if (effect instanceof ApplyStatusEffectsConsumeEffect statusEffects
						&& !level.isClientSide
						&& level.getRandom().nextFloat() < statusEffects.probability()) {
					for (var instance : statusEffects.effects()) {
						entity.addEffect(instance);
					}
				}
			}
		}
		*///?} else {
		FoodProperties food = stack.get(DataComponents.FOOD);
		if (food != null) {
			for (FoodProperties.PossibleEffect effect : food.effects()) {
				if (!level.isClientSide && effect != null && level.getRandom().nextFloat() < effect.probability()) {
					entity.addEffect(effect.effect());
				}
			}
		}
		//?}
	}
}
