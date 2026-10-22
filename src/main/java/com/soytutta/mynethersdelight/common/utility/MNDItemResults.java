package com.soytutta.mynethersdelight.common.utility;

//? if >=1.21.11 {
//import net.minecraft.world.InteractionResult;
//?} else {
//?}

public final class MNDItemResults {
	//? if >=1.21.11 {
	/*public static final InteractionResult PASS = InteractionResult.TRY_WITH_EMPTY_HAND;
	public static final InteractionResult SUCCESS = InteractionResult.SUCCESS;
	public static final InteractionResult CONSUME = InteractionResult.CONSUME;
	public static final InteractionResult SKIP_DEFAULT = InteractionResult.PASS;
	*///? } else {
	public static final net.minecraft.world.ItemInteractionResult PASS = net.minecraft.world.ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	public static final net.minecraft.world.ItemInteractionResult SUCCESS = net.minecraft.world.ItemInteractionResult.SUCCESS;
	public static final net.minecraft.world.ItemInteractionResult CONSUME = net.minecraft.world.ItemInteractionResult.CONSUME;
	public static final net.minecraft.world.ItemInteractionResult SKIP_DEFAULT = net.minecraft.world.ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
	//?}

	private MNDItemResults() {
	}
}
