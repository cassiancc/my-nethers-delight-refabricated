package com.soytutta.mynethersdelight.common.utility;

import net.minecraft.world.effect.MobEffectInstance;

import java.util.ArrayList;
import java.util.List;

public record MNDFood(int nutrition, float saturationModifier, boolean alwaysEdible, boolean fast, List<Effect> effects) {

	public record Effect(MobEffectInstance instance, float probability) {
	}

	public static final class Builder {
		private int nutrition;
		private float saturationModifier;
		private boolean alwaysEdible;
		private boolean fast;
		private final List<Effect> effects = new ArrayList<>();

		public Builder nutrition(int nutrition) {
			this.nutrition = nutrition;
			return this;
		}

		public Builder saturationModifier(float saturationModifier) {
			this.saturationModifier = saturationModifier;
			return this;
		}

		public Builder alwaysEdible() {
			this.alwaysEdible = true;
			return this;
		}

		public Builder fast() {
			this.fast = true;
			return this;
		}

		public Builder effect(MobEffectInstance instance, float probability) {
			this.effects.add(new Effect(instance, probability));
			return this;
		}

		public MNDFood build() {
			return new MNDFood(nutrition, saturationModifier, alwaysEdible, fast, List.copyOf(effects));
		}
	}
}
