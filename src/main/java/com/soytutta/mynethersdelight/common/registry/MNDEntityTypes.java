package com.soytutta.mynethersdelight.common.registry;

import com.soytutta.mynethersdelight.MyNethersDelight;
import com.soytutta.mynethersdelight.common.entity.StriderRockEntity;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.Builder;
import net.minecraft.world.entity.MobCategory;

public class MNDEntityTypes {
	public static final EntityType<StriderRockEntity> STRIDER_ROCK = register("strider_rock", Builder.<StriderRockEntity>of(StriderRockEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10));

	public static void initialize() {

	}

	private static <T extends Entity> EntityType<T> register(String string, Builder<T> builder) {
		//? if >=1.21.11 {
		/*// 1.21.11 needs the registry key of the entity type while it is built.
		ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, MyNethersDelight.id(string));
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, key, builder.build(key));
		*///?} else {
		return Registry.register(BuiltInRegistries.ENTITY_TYPE, MyNethersDelight.id(string), builder.build(string));
		//?}
	}
}
