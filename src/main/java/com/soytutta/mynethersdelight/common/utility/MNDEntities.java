package com.soytutta.mynethersdelight.common.utility;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public final class MNDEntities {
	private MNDEntities() {
	}

	public static <T extends Entity> T create(EntityType<T> type, Level level) {
		//? if >=1.21.11 {
		//return type.create(level, net.minecraft.world.entity.EntitySpawnReason.TRIGGERED);
		//?} else {
		return type.create(level);
		//?}
	}
}
