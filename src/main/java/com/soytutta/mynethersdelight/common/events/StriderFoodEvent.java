package com.soytutta.mynethersdelight.common.events;

import com.soytutta.mynethersdelight.common.registry.MNDItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
//? if >=26.1.2 {
//import net.minecraft.world.item.ItemStackTemplate;
//?}
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.common.registry.ModParticleTypes;
import vectorwing.farmersdelight.common.utility.MathUtils;

import static com.soytutta.mynethersdelight.common.item.HotCreamConeItem.EFFECTS;

public class StriderFoodEvent {
	@SuppressWarnings("unused")
	public static InteractionResult onStriderFoodApplied(Player player, Level level, InteractionHand hand, Entity target, @Nullable EntityHitResult hitResult) {
		ItemStack itemStack = player.getItemInHand(hand);
		if (target instanceof Strider strider && strider.isAlive() && itemStack.getItem().equals(MNDItems.HOT_CREAM_CONE)) {
			strider.setHealth(strider.getMaxHealth());
			for (MobEffectInstance effect : EFFECTS) {
				strider.addEffect(new MobEffectInstance(effect));
			}
			strider.level().playSound(null, target.blockPosition(), SoundEvents.GENERIC_EAT, SoundSource.PLAYERS, 0.8F, 0.8F);
			for (int i = 0; i < 5; ++i) {
				double xSpeed = MathUtils.RAND.nextGaussian() * 0.02D;
				double ySpeed = MathUtils.RAND.nextGaussian() * 0.02D;
				double zSpeed = MathUtils.RAND.nextGaussian() * 0.02D;
				strider.level().addParticle(ModParticleTypes.STAR.get(), strider.getRandomX(0.75D), strider.getRandomY() + 0.5D, strider.getRandomZ(0.75D), xSpeed, ySpeed, zSpeed);
			}
			if (!player.isCreative()) {
				//? if >=26.1.2 {
				/*ItemStackTemplate remainingItem = itemStack.getItem().getCraftingRemainder();
				if (remainingItem != null) {
					player.addItem(remainingItem.create());
				}
				*///?} else if >=1.21.11 {
				/*ItemStack remainingItem = itemStack.getItem().getCraftingRemainder();
				if (!remainingItem.isEmpty()) {
					player.addItem(remainingItem);
				}
				*///?} else {
				Item remainingItem = itemStack.getItem().getCraftingRemainingItem();
				if (remainingItem != null) {
					player.addItem(new ItemStack(remainingItem));
				}
				//?}
				itemStack.shrink(1);
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
}
