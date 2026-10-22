package com.soytutta.mynethersdelight.core.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class MNDDataGenerators {

	public static void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(MNDBlockTags::new);
		pack.addProvider(MNDItemTags::new);
		pack.addProvider(MNDRecipes::new);
		pack.addProvider(MNDLang::new);
		pack.addProvider(MNDBlockStates::new);
	}
}
