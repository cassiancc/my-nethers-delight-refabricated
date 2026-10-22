package com.soytutta.mynethersdelight.common.block.utility;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class MNDShapes {
	public static final VoxelShape TRAY_OUTER = Block.box(1, 0, 1, 15, 2, 15);
	public static final VoxelShape TRAY_INNER = Block.box(2, 1, 2, 14, 2, 14);
	public static final VoxelShape TRAY_SHAPE = Shapes.joinUnoptimized(TRAY_OUTER, TRAY_INNER, BooleanOp.OR);
	public static final VoxelShape POWDERY_LARGE_SHAPE = Block.box(5.0, 0.0, 5.0, 11.0, 16.0, 11.0);
	public static final VoxelShape POWDERY_SMALL_SHAPE = Block.box(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);
	public static final VoxelShape POWDERY_COLLISION_SHAPE = Block.box(6.5, 0.0, 6.5, 9.5, 16.0, 9.5);

	private MNDShapes() {
	}
}
