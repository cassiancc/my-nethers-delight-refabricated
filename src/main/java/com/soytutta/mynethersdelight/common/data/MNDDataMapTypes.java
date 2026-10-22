package com.soytutta.mynethersdelight.common.data;

import com.soytutta.mynethersdelight.MyNethersDelight;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class MNDDataMapTypes {
	public static final ResourceKey<Registry<PlantRuleSet>> PROPAGATION_TRANSFORMATIONS =
			ResourceKey.createRegistryKey(MyNethersDelight.id("propagation_transformations"));

	public static final ResourceKey<Registry<PlantRuleSet>> LETIOS_COMPOST_TRANSFORMATIONS =
			ResourceKey.createRegistryKey(MyNethersDelight.id("letios_compost_transformations"));

	public static void register() {
		DynamicRegistries.register(PROPAGATION_TRANSFORMATIONS, PlantRuleSet.CODEC);
		DynamicRegistries.register(LETIOS_COMPOST_TRANSFORMATIONS, PlantRuleSet.CODEC);
	}
}
