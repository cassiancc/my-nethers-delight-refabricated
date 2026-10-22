package com.soytutta.mynethersdelight.common.registry;

import com.soytutta.mynethersdelight.MyNethersDelight;
import com.soytutta.mynethersdelight.common.effect.GoodPungentEffect;
import com.soytutta.mynethersdelight.common.effect.PungentEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;

public class MNDEffects {
	public static final Holder<MobEffect> GPUNGENT = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, MyNethersDelight.id("g_pungent"), new GoodPungentEffect());
	public static final Holder<MobEffect> BPUNGENT = Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, MyNethersDelight.id("b_pungent"), new PungentEffect());

	public static void initialize() {

	}
}
