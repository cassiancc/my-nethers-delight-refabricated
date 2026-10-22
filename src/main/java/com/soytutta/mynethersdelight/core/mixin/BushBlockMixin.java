package com.soytutta.mynethersdelight.core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.soytutta.mynethersdelight.common.block.LetiosCompostBlock;
import com.soytutta.mynethersdelight.common.block.ResurgentSoilBlock;
import com.soytutta.mynethersdelight.common.block.ResurgentSoilFarmlandBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//? if >=1.21.11 {
//import net.minecraft.world.level.block.VegetationBlock;
//?} else {
import net.minecraft.world.level.block.BushBlock;
//?}

//? if >=1.21.11 {
/*@MixinEnvironment(type = MixinEnvironment.Env.MAIN)
@Mixin(VegetationBlock.class)
public class BushBlockMixin {
    @ModifyReturnValue(method = "canSurvive", at = @At("RETURN"))
    private boolean mynethersdelightrefabricated$allowPlantsOnBushes(boolean original, BlockState state, LevelReader level, BlockPos pos) {
		BlockState blockState = level.getBlockState(pos.below());

		if (state.getBlock() != (Object)this)
            return original;

        if (blockState.getBlock() instanceof LetiosCompostBlock)
            return ((Object)this) instanceof NetherWartBlock;

		if (blockState.getBlock() instanceof ResurgentSoilBlock block)
			return block.canSustainPlant(state).orElse(original);

		if (blockState.getBlock() instanceof ResurgentSoilFarmlandBlock block)
			return block.canSustainPlant(state).orElse(original);

        return original;
    }
}
*///?} else {
@MixinEnvironment(type = MixinEnvironment.Env.MAIN)
@Mixin(BushBlock.class)
public class BushBlockMixin {
    @ModifyReturnValue(method = "canSurvive", at = @At("RETURN"))
    private boolean mynethersdelightrefabricated$allowPlantsOnBushes(boolean original, BlockState state, LevelReader level, BlockPos pos) {
		BlockState blockState = level.getBlockState(pos.below());

		if (state.getBlock() != (Object)this)
            return original;

        if (blockState.getBlock() instanceof LetiosCompostBlock)
            return ((Object)this) instanceof NetherWartBlock;

		if (blockState.getBlock() instanceof ResurgentSoilBlock block)
			return block.canSustainPlant(state).orElse(original);

		if (blockState.getBlock() instanceof ResurgentSoilFarmlandBlock block)
			return block.canSustainPlant(state).orElse(original);

        return original;
    }
}
//?}
