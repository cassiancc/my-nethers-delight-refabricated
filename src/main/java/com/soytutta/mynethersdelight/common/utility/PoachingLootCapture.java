package com.soytutta.mynethersdelight.common.utility;

import com.soytutta.mynethersdelight.common.enchantment.PoachingOutcome;
import com.soytutta.mynethersdelight.common.events.CommonEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public final class PoachingLootCapture {

    private static final ThreadLocal<Deque<List<ItemStack>>> CAPTURES = ThreadLocal.withInitial(ArrayDeque::new);
    private static final ThreadLocal<PoachingOutcome> ACTIVE_HUNT = new ThreadLocal<>();
    private static final ThreadLocal<Boolean> INSIDE_GENERATION = ThreadLocal.withInitial(() -> false);

    private PoachingLootCapture() {
    }

    public static boolean tryCapture(ItemStack stack) {
        Deque<List<ItemStack>> captures = CAPTURES.get();
        if (captures.isEmpty()) return false;
        captures.peek().add(stack.copy());
        return true;
    }

    public static boolean isGeneratingLoot() {
        return INSIDE_GENERATION.get();
    }

    public static List<ItemStack> collect(Runnable generateLoot) {
        Deque<List<ItemStack>> captures = CAPTURES.get();
        List<ItemStack> collected = new ArrayList<>();
        boolean wasGenerating = INSIDE_GENERATION.get();
        captures.push(collected);
        INSIDE_GENERATION.set(true);
        try {
            generateLoot.run();
        } finally {
            INSIDE_GENERATION.set(wasGenerating);
            captures.pop();
        }
        return collected;
    }

    public static void beginHunt(LivingEntity entity, ServerLevel level, DamageSource damageSource) {
        if (INSIDE_GENERATION.get()) return;

        if (ACTIVE_HUNT.get() != null) {ACTIVE_HUNT.remove();
            if (!CAPTURES.get().isEmpty()) CAPTURES.get().pop();
            return;
        }

        PoachingOutcome outcome = CommonEvent.evaluatePoaching(entity, damageSource);
        if (outcome == null) return;

        CAPTURES.get().push(new ArrayList<>());
        ACTIVE_HUNT.set(outcome);
    }

    /** Injected at {@code LivingEntity#dropAllDeathLoot} RETURN. */
    public static void endHunt(LivingEntity entity) {
        if (INSIDE_GENERATION.get()) return;

        PoachingOutcome outcome = ACTIVE_HUNT.get();
        if (outcome == null) return;
        ACTIVE_HUNT.remove();

        List<ItemStack> normalDrops = CAPTURES.get().pop();
        List<ItemStack> poachedDrops;
        try {
            poachedDrops = CommonEvent.runPoachingOutcome(outcome);
        } finally {
            normalDrops.clear();
        }

        for (ItemStack stack : poachedDrops) {
            //? if >=1.21.11 {
            /*if (entity.level() instanceof ServerLevel serverLevel) {
                entity.spawnAtLocation(serverLevel, stack);
            }
            *///?} else {
            entity.spawnAtLocation(stack);
            //?}
        }
    }
}
