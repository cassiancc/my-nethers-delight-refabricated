package com.soytutta.mynethersdelight.core.mixin;

import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Strider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@MixinEnvironment(type = MixinEnvironment.Env.CLIENT)
@Mixin(Strider.class)
public abstract class StriderVisualMixin extends LivingEntity {

	@Shadow @Final private static EntityDataAccessor<Boolean> mynetherdelight_DATA_IS_PUNGENT;
    protected StriderVisualMixin() { super(null, null); } // Constructor dummy

    @Inject(method = "isSuffocating()Z", at = @At("HEAD"), cancellable = true)
    private void overrideSuffocatingVisuals(CallbackInfoReturnable<Boolean> cir) {
        if (this.getEntityData().get(mynetherdelight_DATA_IS_PUNGENT)) {
            cir.setReturnValue(false);
        }
    }
}
