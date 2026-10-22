package com.soytutta.mynethersdelight.common.registry;

import com.soytutta.mynethersdelight.MyNethersDelight;
import com.soytutta.mynethersdelight.common.enchantment.PoachingData;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;

import java.util.List;

public class MNDEnchantmentComponents {

	public static final DataComponentType<List<PoachingData>> POACHING =
			Registry.register(
					BuiltInRegistries.ENCHANTMENT_EFFECT_COMPONENT_TYPE,
					MyNethersDelight.id("poaching"),
					DataComponentType.<List<PoachingData>>builder()
							.persistent(PoachingData.CODEC.listOf())
							.build()
			);

	public static void initialize() {
	}
}
