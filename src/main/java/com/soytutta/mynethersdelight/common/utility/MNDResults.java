package com.soytutta.mynethersdelight.common.utility;

import net.minecraft.world.InteractionResult;

public final class MNDResults {
	private MNDResults() {
	}

	public static InteractionResult success() {
		//? if >=1.21.11 {
		//return InteractionResult.SUCCESS;
		//?} else {
		return InteractionResult.SUCCESS;
		//?}
	}

	public static InteractionResult consume() {
		//? if >=1.21.11 {
		//return InteractionResult.CONSUME;
		//?} else {
		return InteractionResult.CONSUME;
		//?}
	}

	public static InteractionResult sidedSuccess(boolean clientSide) {
		//? if >=1.21.11 {
		//return clientSide ? InteractionResult.SUCCESS : InteractionResult.CONSUME;
		//?} else {
		return InteractionResult.sidedSuccess(clientSide);
		//?}
	}
}
