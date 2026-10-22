package com.soytutta.mynethersdelight.common.utility;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Player;

public class MNDTextUtils {
    public MNDTextUtils() {
    }
    public static MutableComponent getTranslation(String key, Object... args) {
        return Component.translatable("mynethersdelight." + key, args);
    }

    //? if >=26.1.2 {
    /*public static void sendActionBar(Player player, Component message) {
        player.sendOverlayMessage(message);
    }
    *///?} else {
    public static void sendActionBar(Player player, Component message) {
        player.displayClientMessage(message, true);
    }
    //?}
}
