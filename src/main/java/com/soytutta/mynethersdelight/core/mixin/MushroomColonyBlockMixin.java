package com.soytutta.mynethersdelight.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.soytutta.mynethersdelight.common.block.ResurgentSoilBlock;
import com.soytutta.mynethersdelight.common.block.ResurgentSoilFarmlandBlock;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import vectorwing.farmersdelight.common.block.MushroomColonyBlock;

@MixinEnvironment(type = MixinEnvironment.Env.MAIN)
@Mixin(value = MushroomColonyBlock.class, remap = false)
public class MushroomColonyBlockMixin {
    @ModifyExpressionValue(method = "canSurvive", at = @At(value = "INVOKE", target = "Lvectorwing/farmersdelight/common/block/MushroomColonyBlock;mayPlaceOn(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Z"), remap = true)
    private boolean mynethersdelightrefabricated$allowPlantsOnMushroomColony(boolean original, BlockState state, LevelReader level, BlockPos pos) {
		BlockState blockState = level.getBlockState(pos.below());

		if (state.getBlock() != (Object)this)
			return original;

		if (blockState.getBlock() instanceof ResurgentSoilBlock block)
			return block.canSustainPlant(state).orElse(original);

		if (blockState.getBlock() instanceof ResurgentSoilFarmlandBlock block)
			return block.canSustainPlant(state).orElse(original);

		return original;
    }
}
