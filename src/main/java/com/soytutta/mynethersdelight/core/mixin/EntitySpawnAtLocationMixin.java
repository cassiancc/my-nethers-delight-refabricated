package com.soytutta.mynethersdelight.core.mixin;

import com.soytutta.mynethersdelight.common.utility.PoachingLootCapture;
import dev.kikugie.fletching_table.annotation.MixinEnvironment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

//? if >=1.21.11 {
//import net.minecraft.server.level.ServerLevel;
//? }

@MixinEnvironment(type = MixinEnvironment.Env.MAIN)
@Mixin(Entity.class)
public class EntitySpawnAtLocationMixin {

	//? if >=1.21.11 {
    /*@Inject(
            method = "spawnAtLocation(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/ItemStack;F)Lnet/minecraft/world/entity/item/ItemEntity;",
            at = @At("HEAD"),
            cancellable = true
    )
    private void mynethersdelightrefabricated$captureDrop(ServerLevel level, ItemStack stack, float yOffset, CallbackInfoReturnable<ItemEntity> cir) {
        capture(stack, cir);
    }
	*///? } else {
	@Inject(
            method = "spawnAtLocation(Lnet/minecraft/world/item/ItemStack;F)Lnet/minecraft/world/entity/item/ItemEntity;",
            at = @At("HEAD"),
            cancellable = true
    )
    private void mynethersdelightrefabricated$captureDrop(ItemStack stack, float yOffset, CallbackInfoReturnable<ItemEntity> cir) {
        capture(stack, cir);
    }
	//?}

    private static void capture(ItemStack stack, CallbackInfoReturnable<ItemEntity> cir) {
        if (stack.isEmpty()) {
            cir.setReturnValue(null);
            return;
        }

        if (PoachingLootCapture.tryCapture(stack)) {
            cir.setReturnValue(null);
        }
    }
}
