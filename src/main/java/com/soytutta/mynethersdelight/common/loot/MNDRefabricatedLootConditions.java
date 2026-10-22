package com.soytutta.mynethersdelight.common.loot;

import com.soytutta.mynethersdelight.MyNethersDelight;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

public class MNDRefabricatedLootConditions {
    public static void registerLootConditions() {
        //? if >=26.1.2 {
        //Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, MyNethersDelight.id("always_false"), AlwaysFalseCondition.CODEC);
        //?} else {
        Registry.register(BuiltInRegistries.LOOT_CONDITION_TYPE, MyNethersDelight.id("always_false"), AlwaysFalseCondition.TYPE);
        //?}
    }
}
