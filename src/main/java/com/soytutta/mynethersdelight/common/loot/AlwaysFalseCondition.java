package com.soytutta.mynethersdelight.common.loot;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
//? if <26.1.2 {
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
//?}

public class AlwaysFalseCondition implements LootItemCondition {
    public static final MapCodec<AlwaysFalseCondition> CODEC = MapCodec.unit(AlwaysFalseCondition::new);
	//? if <26.1.2 {
    public static final LootItemConditionType TYPE = new LootItemConditionType(CODEC);
	//?}

    @Override
    public boolean test(LootContext context) {
        return false;
    }

	//? if >=26.1.2 {
    /*@Override
    public MapCodec<? extends LootItemCondition> codec() {
        return CODEC;
    }
	*///?} else {
    @Override
    public LootItemConditionType getType() {
        return TYPE;
    }
	//?}
}
