package com.soytutta.mynethersdelight.platform.fabric.datagen;

import com.soytutta.mynethersdelight.core.data.MNDDataGenerators;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class FabricDataGeneratorEntrypoint implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		MNDDataGenerators.onInitializeDataGenerator(generator);
	}
}
