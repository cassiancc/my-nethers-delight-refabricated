package com.soytutta.mynethersdelight.client.renderer.state;

//? if >=1.21.11 {
/*import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.core.Direction;

import java.util.ArrayList;
import java.util.List;

/^*
 * Render state of the blazier, 1.21.11 renders block entities from an extracted state.
 ^/
public class BlazierRenderState extends BlockEntityRenderState {
	public Direction facing = Direction.NORTH;
	public boolean twoSlots;
	public boolean smoking;
	public List<ItemStackRenderState> items = new ArrayList<>();
}
*///?}
