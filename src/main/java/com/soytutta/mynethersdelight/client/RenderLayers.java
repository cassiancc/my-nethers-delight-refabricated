package com.soytutta.mynethersdelight.client;

import net.minecraft.world.level.block.Block;

//? if >=1.21.11 <26.1.2 {
/*import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
*///?} else if <26.1.2 {
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
//?}

public final class RenderLayers {
	private RenderLayers() {
	}

	public static void cutout(Block block) {
		//? if >=1.21.11 <26.1.2 {
		//BlockRenderLayerMap.putBlock(block, ChunkSectionLayer.CUTOUT);
		//?} else if <26.1.2 {
		BlockRenderLayerMap.INSTANCE.putBlock(block, RenderType.cutout());
		//?}
	}
}
