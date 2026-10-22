package com.soytutta.mynethersdelight.core.mixin;

import com.soytutta.mynethersdelight.common.utility.EntityDropChanceAccessor;
import com.soytutta.mynethersdelight.common.utility.PoachingLootCapture;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@MixinEnvironment(type = MixinEnvironment.Env.MAIN)
@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements EntityDropChanceAccessor {

    @Shadow
    protected abstract void dropAllDeathLoot(ServerLevel pLevel, DamageSource pDamageSource);

    @Inject(method = "dropAllDeathLoot", at = @At("HEAD"))
    private void mynethersdelightrefabricated$beginPoachingHunt(ServerLevel level, DamageSource damageSource, CallbackInfo ci) {
        PoachingLootCapture.beginHunt((LivingEntity) (Object) this, level, damageSource);
    }

    @Inject(method = "dropAllDeathLoot", at = @At("RETURN"))
    private void mynethersdelightrefabricated$endPoachingHunt(ServerLevel level, DamageSource damageSource, CallbackInfo ci) {
        PoachingLootCapture.endHunt((LivingEntity) (Object) this);
    }

    //? if >=1.21.11 {
    /*@Inject(method = "dropExperience", at = @At("HEAD"), cancellable = true)
    private void mynethersdelightrefabricated$suppressNestedExperience(ServerLevel level, Entity killer, CallbackInfo ci) {
        if (PoachingLootCapture.isGeneratingLoot()) {
            ci.cancel();
        }
    }
    *///? } else {
    @Inject(method = "dropExperience", at = @At("HEAD"), cancellable = true)
    private void mynethersdelightrefabricated$suppressNestedExperience(Entity killer, CallbackInfo ci) {
        if (PoachingLootCapture.isGeneratingLoot()) {
            ci.cancel();
        }
    }
    //?}

    @Override
    public List<ItemStack> callGenerateLoot(DamageSource damageSource) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (!(self.level() instanceof ServerLevel serverLevel)) {
            return new ArrayList<>();
        }

        return PoachingLootCapture.collect(() -> this.dropAllDeathLoot(serverLevel, damageSource));
    }
}
