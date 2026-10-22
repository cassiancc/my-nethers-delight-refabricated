package com.soytutta.mynethersdelight.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.soytutta.mynethersdelight.common.block.ResurgentSoilBlock;
import com.soytutta.mynethersdelight.common.block.ResurgentSoilFarmlandBlock;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@MixinEnvironment(type = MixinEnvironment.Env.MAIN)
@Mixin(MushroomBlock.class)
public class MushroomBlockMixin {
    @ModifyExpressionValue(method = "canSurvive", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/MushroomBlock;mayPlaceOn(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Z"))
    private boolean mynethersdelightrefabricated$allowPlantsOnMushroom(boolean original, BlockState state, LevelReader level, BlockPos pos) {
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
