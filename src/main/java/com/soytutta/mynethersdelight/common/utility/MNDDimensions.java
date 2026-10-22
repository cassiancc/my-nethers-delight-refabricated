package com.soytutta.mynethersdelight.common.utility;

import net.minecraft.world.level.Level;

public final class MNDDimensions {
	private MNDDimensions() {
	}

	public static boolean isUltraWarm(Level level) {
		//? if >=1.21.11 {
		//return level.dimension().equals(Level.NETHER);
		//?} else {
		return level.dimensionType().ultraWarm();
		//?}
	}
}
