package com.soytutta.mynethersdelight.core.mixin;

import com.soytutta.mynethersdelight.common.utility.EntityDropChanceAccessor;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@MixinEnvironment(type = MixinEnvironment.Env.MAIN)
@Mixin(Mob.class)
public abstract class MobMixin extends LivingEntity implements EntityDropChanceAccessor {

    public MobMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow protected abstract void dropCustomDeathLoot(ServerLevel level, DamageSource damageSource, boolean recentlyHit);

    @Override
    public void callDropCustomDeathLoot(ServerLevel level, DamageSource damageSource, boolean recentlyHit){
        this.dropCustomDeathLoot(level, damageSource, recentlyHit);
    }
}
