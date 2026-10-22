package com.soytutta.mynethersdelight.platform.fabric;

//? fabric {

import com.soytutta.mynethersdelight.MyNethersDelight;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ClientModInitializer;

@Entrypoint("client")
public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		MyNethersDelight.onInitializeClient();
	}

}
//?}
