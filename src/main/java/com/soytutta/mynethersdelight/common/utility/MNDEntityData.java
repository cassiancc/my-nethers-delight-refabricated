package com.soytutta.mynethersdelight.common.utility;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

//? if >=1.21.11 {
/*import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
*///? }

public final class MNDEntityData {
	private MNDEntityData() {
	}

	public static void copyData(Entity source, Entity target, Level level) {
		//? if >=1.21.11 {
		/*TagValueOutput output = TagValueOutput.createWithContext(ProblemReporter.DISCARDING, level.registryAccess());
		source.saveWithoutId(output);
		target.load(TagValueInput.create(ProblemReporter.DISCARDING, level.registryAccess(), output.buildResult()));
		*///?} else {
		CompoundTag tag = new CompoundTag();
		source.save(tag);
		target.load(tag);
		//?}
	}

	public static void copyAdditionalData(Entity source, Entity target, Level level) {
		//? if >=1.21.11 {
		/*TagValueOutput output = TagValueOutput.createWithContext(ProblemReporter.DISCARDING, level.registryAccess());
		source.saveWithoutId(output);
		CompoundTag tag = output.buildResult();
		tag.remove("UUID");
		tag.remove("Health");
		target.load(TagValueInput.create(ProblemReporter.DISCARDING, level.registryAccess(), tag));
		*///?} else {
		CompoundTag tag = new CompoundTag();
		source.save(tag);
		tag.remove("UUID");
		tag.remove("Health");
		target.load(tag);
		//?}
	}
}
