package com.soytutta.mynethersdelight.platform.fabric;

//? fabric {

import com.soytutta.mynethersdelight.MyNethersDelight;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ModInitializer;

@Entrypoint("main")
public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		MyNethersDelight.onInitialize();
		FabricEventSubscriber.registerEvents();
	}
}
//?}
