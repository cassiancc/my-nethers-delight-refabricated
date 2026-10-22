package com.soytutta.mynethersdelight.common.enchantment;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public record PoachingOutcome(
        Kind kind,
        Mob mob,
        DamageSource damageSource,
        ItemStack weapon,
        @Nullable PoachingFailureCase failureCase
) {

    public enum Kind {
        /** {@code Size > 1} slimes split instead of rolling for a hunt. */
        SPLIT_SLIME,
        /** The mob is replaced by {@link #failureCase()}'s target. */
        TRANSFORM,
        /** No matching transformation; nearby same-type mobs are struck by soul. */
        BACKLASH,
        /** Loot is regenerated with a temporary Piglin Brute as the killer. */
        SUCCESS
    }
}
