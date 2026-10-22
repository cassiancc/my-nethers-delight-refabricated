package com.soytutta.mynethersdelight.common.item;

import com.google.common.collect.Lists;
import com.soytutta.mynethersdelight.common.registry.MNDEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Strider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
//? if >=1.21.11 {
//import net.minecraft.world.item.component.TooltipDisplay;
//?}
import net.minecraft.world.level.Level;
import vectorwing.farmersdelight.common.Configuration;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.utility.TextUtils;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class HotCreamConeItem extends ConsumableItem {

	public static final List<MobEffectInstance> EFFECTS = Lists.newArrayList(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 1), new MobEffectInstance(MNDEffects.GPUNGENT, 1200, 0, false, false));

	public HotCreamConeItem(Properties properties) {
		super(properties, false, true);
	}

	public SoundEvent getEatingSound() {
		return SoundEvents.HONEY_DRINK;
	}

	public void affectConsumer(ItemStack stack, Level level, LivingEntity consumer) {
		boolean removedEffect = false;

		if (!consumer.fireImmune()) {
			consumer.setRemainingFireTicks(10);
		}

		Iterator<MobEffectInstance> iterator = consumer.getActiveEffects().iterator();

		while (iterator.hasNext() && !removedEffect) {
			MobEffectInstance effectInstance = iterator.next();
			int remainingDuration = effectInstance.getDuration();
			int fireResistanceDuration = (remainingDuration / 5);
			int pungentDuration = (fireResistanceDuration / 2);

			if (fireResistanceDuration > 200) {
				consumer.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, fireResistanceDuration * 3));
			} else {
				consumer.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400));
			}
			if (pungentDuration > 200) {
				consumer.addEffect(new MobEffectInstance(MNDEffects.GPUNGENT, pungentDuration * 3, 2, false, false, true));
			} else {
				consumer.addEffect(new MobEffectInstance(MNDEffects.GPUNGENT, 600, 2, false, false, true));
			}

			consumer.removeEffect(effectInstance.getEffect());
			removedEffect = true;
		}

		if (removedEffect) {
			level.playSound(null, consumer.blockPosition(), SoundEvents.LAVA_EXTINGUISH, consumer.getSoundSource(), 1.0F, 1.0F);
		}
	}

	//? if >=1.21.11 {
	/*@Override
	public void appendHoverText(ItemStack stack, net.minecraft.world.item.Item.TooltipContext context, TooltipDisplay display, Consumer<Component> tooltip, TooltipFlag isAdvanced) {
		this.addEffectTooltip(stack, context, tooltip);
	}
	*///?} else {
	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag isAdvanced) {
		this.addEffectTooltip(stack, context, tooltip::add);
	}
	//?}

	private void addEffectTooltip(ItemStack stack, net.minecraft.world.item.Item.TooltipContext context, Consumer<Component> tooltip) {
		if (!Configuration.ENABLE_FOOD_EFFECT_TOOLTIP.get()) {
			return;
		}

		MutableComponent textWhenFeeding = TextUtils.getTranslation("tooltip.strider_feed.when_feeding");
		tooltip.accept(textWhenFeeding.withStyle(ChatFormatting.GRAY));

		for (MobEffectInstance effectInstance : EFFECTS) {
			MutableComponent effectDescription = Component.literal(" ");
			MutableComponent effectName = Component.translatable(effectInstance.getDescriptionId());
			effectDescription.append(effectName);
			MobEffect effect = effectInstance.getEffect().value();

			if (effectInstance.getAmplifier() > 0) {
				effectDescription.append(" ").append(Component.translatable("potion.potency." + effectInstance.getAmplifier()));
			}

			if (effectInstance.getDuration() > 20) {
				effectDescription.append(" (").append(MobEffectUtil.formatDuration(effectInstance, 1.0F, context.tickRate())).append(")");
			}

			tooltip.accept(effectDescription.withStyle(effect.getCategory().getTooltipFormatting()));
		}
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity target, InteractionHand hand) {
		if (target instanceof Strider strider) {
			if (strider.isAlive()) {
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.PASS;
	}
}
