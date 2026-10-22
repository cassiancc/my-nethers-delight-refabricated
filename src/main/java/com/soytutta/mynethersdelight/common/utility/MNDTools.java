package com.soytutta.mynethersdelight.common.utility;

import com.soytutta.mynethersdelight.common.tag.MNDModTags;

import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.common.tag.ModTags;

public final class MNDTools {
	private MNDTools() {
	}

	public static boolean isGoldTool(ItemStack stack) {
		//? if >=1.21.11 {
		//return stack.get(net.minecraft.core.component.DataComponents.MAX_DAMAGE) == net.minecraft.world.item.ToolMaterial.GOLD.durability();
		//?} else {
		return stack.getItem() instanceof net.minecraft.world.item.TieredItem tiered && tiered.getTier() == net.minecraft.world.item.Tiers.GOLD;
		//?}
	}

	public static boolean isKnife(ItemStack stack) {
		//? if >=1.21.11 {
		//return stack.is(MNDModTags.KNIVES);
		//?} else {
		return vectorwing.farmersdelight.common.utility.ItemUtils.isKnife(stack);
		//?}
	}

	public static net.minecraft.world.entity.EquipmentSlot slotForHand(net.minecraft.world.InteractionHand hand) {
		//? if >=1.21.11 {
		//return hand.asEquipmentSlot();
		//?} else {
		return net.minecraft.world.entity.LivingEntity.getSlotForHand(hand);
		//?}
	}
}
