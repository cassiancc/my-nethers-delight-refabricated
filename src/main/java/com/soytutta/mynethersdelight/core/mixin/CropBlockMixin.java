package com.soytutta.mynethersdelight.core.mixin;

import com.soytutta.mynethersdelight.common.block.ResurgentSoilBlock;
import com.soytutta.mynethersdelight.common.block.ResurgentSoilFarmlandBlock;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@MixinEnvironment(type = MixinEnvironment.Env.MAIN)
@Mixin(CropBlock.class)
public class CropBlockMixin {
	//? if <26.1.2 {
    @ModifyVariable(method = "getGrowthSpeed", at = @At(value = "LOAD", ordinal = 1), ordinal = 1)
    private static float mynethersdelightrefabricated$modifyGrowthSpeedForNonFarmland(float original, Block block, BlockGetter level, BlockPos pos) {
        BlockState belowState = level.getBlockState(pos.below());
        if (belowState.getBlock() instanceof ResurgentSoilBlock block1 && block1.canSustainPlant(block).get() && original < 0.00001F)
            return 1.0F;

        if (belowState.getBlock() instanceof ResurgentSoilFarmlandBlock block1 && block1.canSustainPlant(block).get() && original < 0.00001F) {
            if (belowState.hasProperty(ResurgentSoilFarmlandBlock.MOISTURE) && belowState.getValue(ResurgentSoilFarmlandBlock.MOISTURE) > 0)
                return 3.0F;
            return 1.0F;
        }

        return original;
    }
	//?}
}
