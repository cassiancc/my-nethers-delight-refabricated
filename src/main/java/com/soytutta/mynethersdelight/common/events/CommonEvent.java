package com.soytutta.mynethersdelight.common.events;

import com.soytutta.mynethersdelight.common.tag.MNDModTags;
import com.soytutta.mynethersdelight.common.utility.MNDTools;
import com.soytutta.mynethersdelight.common.utility.MNDEntities;
import com.soytutta.mynethersdelight.common.utility.MNDEntityData;

import com.soytutta.mynethersdelight.common.MNDConfiguration;
import com.soytutta.mynethersdelight.common.enchantment.PoachingData;
import com.soytutta.mynethersdelight.common.enchantment.PoachingFailureCase;
import com.soytutta.mynethersdelight.common.enchantment.PoachingFailureRegistry;
import com.soytutta.mynethersdelight.common.enchantment.PoachingOutcome;
import com.soytutta.mynethersdelight.common.entity.ia.EatMagmaCakeGoal;
import com.soytutta.mynethersdelight.common.registry.MNDEnchantmentComponents;
import com.soytutta.mynethersdelight.common.registry.MNDItems;
import com.soytutta.mynethersdelight.common.tag.MNDTags;
import com.soytutta.mynethersdelight.common.utility.EntityDropChanceAccessor;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.List;

public class CommonEvent {

	public static InteractionResult onFrogMagmaCakeInteraction(Player player, Level level, InteractionHand hand, Entity target, @Nullable EntityHitResult hitResult) {
		if (!MNDConfiguration.enableFrogMagmaCakeBehavior()) return InteractionResult.PASS;
		ItemStack stack = player.getItemInHand(hand);
		if (!stack.is(MNDItems.MAGMA_CAKE_SLICE)) return InteractionResult.PASS;
		if (!(target instanceof Frog frog) || !frog.isAlive()) return InteractionResult.PASS;
		boolean accepted = frog.goalSelector.getAvailableGoals()
				.stream().map(WrappedGoal::getGoal)
				.filter(EatMagmaCakeGoal.class::isInstance)
				.map(EatMagmaCakeGoal.class::cast)
				.anyMatch(goal -> goal.requestFeeding(player, hand));
		return accepted ? InteractionResult.SUCCESS : InteractionResult.PASS;
	}

	@Nullable
	public static PoachingOutcome evaluatePoaching(LivingEntity entity, DamageSource damageSource) {
		if (entity.level().isClientSide) return null;
		if (!(entity instanceof Mob mob)) return null;
		if (damageSource == null) return null;
		if (!(damageSource.getDirectEntity() instanceof LivingEntity directSource)) return null;

		ItemStack weapon = directSource.getItemInHand(InteractionHand.MAIN_HAND);
		if (!weapon.is(ItemTags.WEAPON_ENCHANTABLE)) return null;

		if (!EnchantmentHelper.has(weapon, MNDEnchantmentComponents.POACHING)) return null;
		if (!(mob.getMaxHealth() < 150.0F || mob.getType().is(MNDTags.SPECIAL_HUNT))) return null;
		if (!((directSource.hasEffect(MobEffects.LUCK) || directSource.hasEffect(MobEffects.UNLUCK)) && entity.level().getRandom().nextFloat() < 0.6F) && !weapon.is(MNDModTags.KNIVES) && !(entity.level().getRandom().nextFloat() < 0.4F)) {
			return null;
		}

		if (directSource instanceof Player player && !player.isCreative()) {
			if (!MNDTools.isGoldTool(weapon)) {
				int extraDamage = player.getRandom().nextInt(6) + 4;
				weapon.hurtAndBreak(extraDamage, player, EquipmentSlot.MAINHAND);
			}
		}

		if (mob instanceof Slime slime && slime.getSize() > 1) {
			return new PoachingOutcome(PoachingOutcome.Kind.SPLIT_SLIME, mob, damageSource, weapon, null);
		}

		PoachingData poachingData = EnchantmentHelper.pickHighestLevel(weapon, MNDEnchantmentComponents.POACHING).orElse(PoachingData.DEFAULT);
		Difficulty difficulty = entity.level().getDifficulty();
		float baseFailProbability = switch (difficulty) {
			case PEACEFUL -> poachingData.peacefulProbability();
			case EASY -> poachingData.easyProbability();
			case NORMAL -> poachingData.normalProbability();
			case HARD -> poachingData.hardProbability();
		};

		int luckLevel = directSource.hasEffect(MobEffects.LUCK) ? directSource.getEffect(MobEffects.LUCK).getAmplifier() + 1 : 0;
		int badLuckLevel = directSource.hasEffect(MobEffects.UNLUCK) ? directSource.getEffect(MobEffects.UNLUCK).getAmplifier() + 1 : 0;
		float failProbability = baseFailProbability - (luckLevel * 0.1F) + (badLuckLevel * 0.2F);

		boolean isFail = (mob.level().getRandom().nextFloat() < failProbability || (mob.isBaby() && mob.level().getRandom().nextFloat() < 0.2F) || weapon.is(MNDModTags.KNIVES)) && !mob.hasEffect(MobEffects.CONFUSION);

		if (!isFail) {
			return new PoachingOutcome(PoachingOutcome.Kind.SUCCESS, mob, damageSource, weapon, null);
		}

		PoachingFailureCase failureCase = PoachingFailureRegistry.findCaseFor(mob, weapon).orElse(null);
		PoachingOutcome.Kind kind = failureCase != null ? PoachingOutcome.Kind.TRANSFORM : PoachingOutcome.Kind.BACKLASH;
		return new PoachingOutcome(kind, mob, damageSource, weapon, failureCase);
	}

	public static List<ItemStack> runPoachingOutcome(PoachingOutcome outcome) {
		Mob mob = outcome.mob();
		ItemStack weapon = outcome.weapon();

		return switch (outcome.kind()) {
			case SPLIT_SLIME -> splitSlime(mob);
			case TRANSFORM -> transform(mob, outcome);
			case BACKLASH -> backlash(mob, outcome.damageSource());
			case SUCCESS -> generatePoachedLoot(mob, weapon);
		};
	}

	private static List<ItemStack> splitSlime(Mob mob) {
		if (mob instanceof Slime slime) {
			for (int i = 0; i < 2; i++) {
				Entity spawned = MNDEntities.create(mob.getType(), mob.level());
				if (spawned instanceof Slime mobCopy) {
					mobCopy.setSize(slime.getSize() - 1, true);
					//? if >=1.21.11 {
					//mobCopy.snapTo(mob.getX() + i, mob.getY(), mob.getZ() + i, mob.getYRot(), mob.getXRot());
					//?} else {
					mobCopy.moveTo(mob.getX() + i, mob.getY(), mob.getZ() + i, mob.getYRot(), mob.getXRot());
					//?}
					mob.level().addFreshEntity(mobCopy);
				}
			}
		}
		return List.of();
	}

	private static List<ItemStack> transform(Mob mob, PoachingOutcome outcome) {
		PoachingFailureCase failureCase = outcome.failureCase();
		if (failureCase == null) return List.of();

		Mob newMob = MNDEntities.create(failureCase.getTargetType(), mob.level());
		if (newMob == null) return List.of();

		mob.level().playSound(null, mob.getX(), mob.getY(), mob.getZ(), failureCase.getTransformSound(), SoundSource.PLAYERS, 1.0F, 1.0F);

		if (mob.level() instanceof ServerLevel serverLevel) {
			serverLevel.sendParticles(ParticleTypes.LARGE_SMOKE, mob.getX(), mob.getY(0.5), mob.getZ(), 25, 0.5, 0.5, 0.5, 0.05);
		}

		failureCase.getSetupAction().accept(mob, newMob);
		//? if >=1.21.11 {
		//newMob.snapTo(mob.getX(), mob.getY(), mob.getZ(), mob.getYRot(), mob.getXRot());
		//?} else {
		newMob.moveTo(mob.getX(), mob.getY(), mob.getZ(), mob.getYRot(), mob.getXRot());
		//?}
		newMob.setYHeadRot(mob.getYHeadRot());
		newMob.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 600, 0));
		mob.level().addFreshEntity(newMob);

		mob.discard();
		return List.of();
	}

	private static List<ItemStack> backlash(Mob mob, DamageSource damageSource) {
		mob.level().getEntitiesOfClass(Mob.class, mob.getBoundingBox().inflate(10)).stream().filter(nearby -> nearby.getType() == mob.getType()).forEach(nearby -> {
			mob.level().playSound(null, mob.getX(), mob.getY(), mob.getZ(), net.minecraft.sounds.SoundEvents.SOUL_ESCAPE, SoundSource.PLAYERS, 0.5F, 1.0F);
			nearby.hurt(damageSource, 0.0F);
		});
		return List.of();
	}

	private static List<ItemStack> generatePoachedLoot(Mob mob, ItemStack weapon) {
		if (!(mob.level() instanceof ServerLevel serverLevel)) return List.of();
		Entity hunterRaw = MNDEntities.create(EntityType.PIGLIN_BRUTE, mob.level());
		if (!(hunterRaw instanceof PiglinBrute hunter)) return List.of();

		DamageSource hunterDamage;
		List<ItemStack> lootToDrop;

		if (mob.isBaby()) {
			hunterDamage = mob.level().damageSources().mobAttack(hunter);
			Mob adultCopy = (Mob) MNDEntities.create(mob.getType(), serverLevel);
			if (adultCopy == null) return List.of();

			MNDEntityData.copyData(mob, adultCopy, mob.level());
			if (adultCopy instanceof AgeableMob ageableCopy) {
				ageableCopy.setBaby(false);
			}
			EntityDropChanceAccessor adultCopyAcc = (EntityDropChanceAccessor) adultCopy;
			lootToDrop = adultCopyAcc.callGenerateLoot(hunterDamage);
			adultCopy.remove(Entity.RemovalReason.DISCARDED);
		} else {
			ItemStack knife = new ItemStack(ModItems.FLINT_KNIFE.get());
			EnchantmentHelper.setEnchantments(knife, weapon.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY));
			EnchantmentHelper.updateEnchantments(knife, (enchants) -> enchants.removeIf(e -> e.is(MNDTags.POACHING_ENCHANTMENT)));
			hunter.setItemInHand(InteractionHand.MAIN_HAND, knife);

			hunterDamage = mob.level().damageSources().mobAttack(hunter);
			EntityDropChanceAccessor acc = (EntityDropChanceAccessor) mob;
			lootToDrop = acc.callGenerateLoot(hunterDamage);
		}

		hunter.remove(Entity.RemovalReason.DISCARDED);
		return lootToDrop;
	}


	public static void transferBasicMobData(Mob original, Mob newMob) {
		if (original.hasCustomName()) {
			newMob.setCustomName(original.getCustomName());
			newMob.setCustomNameVisible(original.isCustomNameVisible());
		}

		if (original.isPersistenceRequired()) {
			newMob.setPersistenceRequired();
		}

		for (EquipmentSlot slot : EquipmentSlot.values()) {
			newMob.setItemSlot(slot, original.getItemBySlot(slot).copy());
		}

		original.getActiveEffects().forEach(effect -> newMob.addEffect(new MobEffectInstance(effect)));

		if (original.isNoAi()) {
			newMob.setNoAi(true);
		}

		if (original.isBaby()) newMob.setBaby(true);
	}

	public static void makeHostile(Mob mobToAnger, LivingEntity target) {
		if (target == null) {
			return;
		}
		if (mobToAnger instanceof NeutralMob neutralMob) {
			//? if >=1.21.11 {
			//neutralMob.setPersistentAngerTarget(net.minecraft.world.entity.EntityReference.of(target.getUUID()));
			//?} else {
			neutralMob.setPersistentAngerTarget(target.getUUID());
			//?}
			neutralMob.startPersistentAngerTimer();
		}

		mobToAnger.setTarget(target);
	}

	public static void transferDataAndMakeHostile(Mob original, Mob newMob) {
		transferBasicMobData(original, newMob);
		makeHostile(newMob, original.getLastHurtByMob());
	}

	public static void transferFullHorseData(AbstractHorse original, AbstractHorse newHorse) {
		MNDEntityData.copyAdditionalData(original, newHorse, original.level());

		transferBasicMobData(original, newHorse);
	}
}
