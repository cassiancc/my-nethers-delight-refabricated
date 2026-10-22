package com.soytutta.mynethersdelight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.soytutta.mynethersdelight.common.block.BlazierBlock;
import com.soytutta.mynethersdelight.common.block.entity.BlazierBlockEntity;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
//? if <26.2 {
/*26.2 removed the multi buffer source, the render state of the blazier submits the
   items of the blazier itself.*/
import net.minecraft.client.renderer.MultiBufferSource;
//?}
//? if <1.21.11 {
import net.minecraft.client.renderer.entity.ItemRenderer;
//?}
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;


//? if >=1.21.11 {
/*import com.soytutta.mynethersdelight.client.renderer.state.BlazierRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;

import java.util.ArrayList;
import java.util.List;

public class BlazeFireRenderer implements BlockEntityRenderer<BlazierBlockEntity, BlazierRenderState> {

    private static final float SIZE = 0.375F;
    private static final float TWO_SLOT_OFFSET = 0.22F;

    private final ItemModelResolver itemModelResolver;

    public BlazeFireRenderer(BlockEntityRendererProvider.Context context) {
        this.itemModelResolver = context.itemModelResolver();
    }

    @Override
    public BlazierRenderState createRenderState() {
        return new BlazierRenderState();
    }

    @Override
    public void extractRenderState(BlazierBlockEntity blockEntity, BlazierRenderState state, float partialTick,
                                   Vec3 cameraPos, @Nullable ModelFeatureRenderer.CrumblingOverlay crumbling) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTick, cameraPos, crumbling);

        BlazierBlock.HeatLevel heat = blockEntity.getBlockState().getValue(BlazierBlock.HEAT);
        int seed = (int) blockEntity.getBlockPos().asLong();

        state.facing = blockEntity.getBlockState().getValue(BlazierBlock.FACING);
        state.twoSlots = BlazierBlockEntity.getMaxSlots(heat) == 2;
        state.smoking = heat == BlazierBlock.HeatLevel.SMOKING;
        state.items = new ArrayList<>();

        NonNullList<ItemStack> items = blockEntity.getItems();
        for (int j = 0; j < items.size(); j++) {
            ItemStackRenderState itemState = new ItemStackRenderState();
            this.itemModelResolver.updateForTopItem(itemState, items.get(j), ItemDisplayContext.FIXED,
                    blockEntity.getLevel(), null, seed + j);
            state.items.add(itemState);
        }
    }

    @Override
    public void submit(BlazierRenderState state, PoseStack poseStack, SubmitNodeCollector collector,
                       CameraRenderState cameraState) {
        if (state.twoSlots) {
            this.submitTwoSlots(state, poseStack, collector);
        } else {
            this.submitFourSlots(state, poseStack, collector);
        }
    }

    // Render para 4 items (tipo campfire).
    private void submitFourSlots(BlazierRenderState state, PoseStack poseStack, SubmitNodeCollector collector) {
        Vec3[] positions = new Vec3[] {
                new Vec3(-0.25, 0, -0.25),
                new Vec3(0.25, 0, -0.25),
                new Vec3(-0.25, 0, 0.25),
                new Vec3(0.25, 0, 0.25)
        };

        int count = Math.min(state.items.size(), 4);

        for (int j = 0; j < count; j++) {
            ItemStackRenderState itemState = state.items.get(j);
            if (itemState.isEmpty()) continue;

            Vec3 pos = positions[j];

            poseStack.pushPose();
            poseStack.translate(0.5F + pos.x, 0.386F, 0.5F + pos.z);

            Direction slotDir = Direction.from2DDataValue((j + state.facing.get2DDataValue()) % 4);
            float yRot = -slotDir.toYRot();

            poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            poseStack.scale(SIZE, SIZE, SIZE);

            itemState.submit(poseStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);

            poseStack.popPose();
        }
    }

    private void submitTwoSlots(BlazierRenderState state, PoseStack poseStack, SubmitNodeCollector collector) {
        float yBase = state.smoking ? 0.15F : 0.386F;

        Direction perp = state.facing.getClockWise();
        float[] offsets = { -TWO_SLOT_OFFSET, TWO_SLOT_OFFSET };

        int count = Math.min(state.items.size(), 2);

        for (int j = 0; j < count; j++) {
            ItemStackRenderState itemState = state.items.get(j);
            if (itemState.isEmpty()) continue;

            float ox = perp.getStepX() * offsets[j];
            float oz = perp.getStepZ() * offsets[j];

            poseStack.pushPose();
            poseStack.translate(0.5F + ox, yBase, 0.5F + oz);

            Direction slotDir = Direction.from2DDataValue((j + state.facing.get2DDataValue()) % 4);
            float yRot = -slotDir.toYRot();

            poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            poseStack.scale(SIZE, SIZE, SIZE);

            itemState.submit(poseStack, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);

            poseStack.popPose();
        }
    }
}

*///?} else {
public class BlazeFireRenderer implements BlockEntityRenderer<BlazierBlockEntity> {

    private static final float SIZE = 0.375F;
    private static final float TWO_SLOT_OFFSET = 0.22F;

    private final ItemRenderer itemRenderer;

    public BlazeFireRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(BlazierBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        BlazierBlock.HeatLevel heat = blockEntity.getBlockState().getValue(BlazierBlock.HEAT);
        Direction facing = blockEntity.getBlockState().getValue(BlazierBlock.FACING);
        NonNullList<ItemStack> items = blockEntity.getItems();
        int seed = (int) blockEntity.getBlockPos().asLong();

        int maxSlots = BlazierBlockEntity.getMaxSlots(heat);

        if (maxSlots == 2) {
            renderTwoSlots(blockEntity, heat, facing, items, poseStack, bufferSource, packedLight, packedOverlay, seed);
        } else {
            renderFourSlots(facing, items, poseStack, bufferSource, packedLight, packedOverlay, seed);
        }
    }

    // Render para 4 items (tipo campfire).
    private void renderFourSlots(Direction facing, NonNullList<ItemStack> items,
                                 PoseStack poseStack, MultiBufferSource bufferSource,
                                 int packedLight, int packedOverlay, int seed) {

        Vec3[] positions = new Vec3[] {
                new Vec3(-0.25, 0, -0.25),
                new Vec3(0.25, 0, -0.25),
                new Vec3(-0.25, 0, 0.25),
                new Vec3(0.25, 0, 0.25)
        };

        int count = Math.min(items.size(), 4);

        for (int j = 0; j < count; j++) {
            ItemStack stack = items.get(j);
            if (stack.isEmpty()) continue;

            Vec3 pos = positions[j];

            poseStack.pushPose();
            poseStack.translate(0.5F + pos.x, 0.386F, 0.5F + pos.z);

            Direction slotDir = Direction.from2DDataValue((j + facing.get2DDataValue()) % 4);
            float yRot = -slotDir.toYRot();

            poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            poseStack.scale(SIZE, SIZE, SIZE);

            itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED,
                    packedLight, packedOverlay, poseStack, bufferSource,
                    null, seed + j);

            poseStack.popPose();
        }
    }

    private void renderTwoSlots(BlazierBlockEntity blockEntity, BlazierBlock.HeatLevel heat,
                                Direction facing, NonNullList<ItemStack> items,
                                PoseStack poseStack, MultiBufferSource bufferSource,
                                int packedLight, int packedOverlay, int seed) {

        float yBase = heat == BlazierBlock.HeatLevel.SMOKING ? 0.15F : 0.386F;

        Direction perp = facing.getClockWise();
        float[] offsets = { -TWO_SLOT_OFFSET, TWO_SLOT_OFFSET };

        int count = Math.min(items.size(), 2);

        for (int j = 0; j < count; j++) {
            ItemStack stack = items.get(j);
            if (stack.isEmpty()) continue;

            float ox = perp.getStepX() * offsets[j];
            float oz = perp.getStepZ() * offsets[j];

            poseStack.pushPose();
            poseStack.translate(0.5F + ox, yBase, 0.5F + oz);

            Direction slotDir = Direction.from2DDataValue((j + facing.get2DDataValue()) % 4);
            float yRot = -slotDir.toYRot();

            poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
            poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
            poseStack.scale(SIZE, SIZE, SIZE);

            itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED,
                    packedLight, packedOverlay, poseStack, bufferSource,
                    blockEntity.getLevel(), seed + j);

            poseStack.popPose();
        }
    }
}
//?}
