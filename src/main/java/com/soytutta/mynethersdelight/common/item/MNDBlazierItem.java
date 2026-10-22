package com.soytutta.mynethersdelight.common.item;

import com.soytutta.mynethersdelight.common.block.BlazierBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.block.Block;

//? if >=1.21.11 {
//import net.minecraft.world.item.component.TooltipDisplay;
//?}

import java.util.List;
import java.util.function.Consumer;

public class MNDBlazierItem extends BlockItem {
	private static final int EXTINGUISHED_TOOLTIP_COLOR = 0x8B8B8B;

	public MNDBlazierItem(Block block, Properties properties) {
		super(block, properties);
	}

	//? if >=1.21.11 {
	/*@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context,
								TooltipDisplay display, Consumer<Component> tooltip, net.minecraft.world.item.TooltipFlag tooltipFlag) {
		addHeatTooltip(stack, tooltip);
	}
	*///? } else {
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context,
								List<Component> tooltip, net.minecraft.world.item.TooltipFlag tooltipFlag) {
		addHeatTooltip(stack, tooltip::add);
	}
	//?}

	public static void addHeatTooltip(ItemStack stack, Consumer<Component> tooltip) {
		BlockItemStateProperties properties = stack.getOrDefault(DataComponents.BLOCK_STATE,
				BlockItemStateProperties.EMPTY);
		Boolean lit = properties.get(BlazierBlock.LIT);
		BlazierBlock.HeatLevel heat = properties.get(BlazierBlock.HEAT);
		BlazierBlock.HeatLevel displayedHeat = heat == null ? BlazierBlock.HeatLevel.SMELTING : heat;
		String heatName = Boolean.FALSE.equals(lit)
				? "extinguished"
				: displayedHeat.getSerializedName();
		int heatColor = Boolean.FALSE.equals(lit)
				? EXTINGUISHED_TOOLTIP_COLOR
				: displayedHeat.getTooltipColor();
		Component heatComponent = Component.translatable(
						"tooltip.mynethersdelight.blazier.heat." + heatName)
				.withStyle(style -> style.withColor(net.minecraft.network.chat.TextColor.fromRgb(heatColor)));

		tooltip.accept(Boolean.FALSE.equals(lit)
				? heatComponent
				: Component.translatable("tooltip.mynethersdelight.blazier.heat",
						heatComponent).withStyle(ChatFormatting.GRAY));
	}
}
