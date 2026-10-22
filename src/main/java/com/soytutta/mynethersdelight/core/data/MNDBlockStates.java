package com.soytutta.mynethersdelight.core.data;

import com.soytutta.mynethersdelight.MyNethersDelight;
import com.soytutta.mynethersdelight.common.registry.MNDBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.FeastBlock;
import vectorwing.farmersdelight.common.block.MushroomColonyBlock;

//? if >=1.21.11 {
/*import net.minecraft.data.models.MultiVariant;
import net.minecraft.data.models.model.ItemModelUtils;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.renderer.block.model.VariantMutator;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
*///? } else {
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
//?}
//? if >=26.1.2 {
//import net.minecraft.client.resources.model.sprite.Material;
//?}

public class MNDBlockStates extends FabricModelProvider {

	private BlockModelGenerators generator;

	public MNDBlockStates(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators generator) {
		this.generator = generator;

		this.cubeRandomRotation(MNDBlocks.RESURGENT_SOIL, "");
		this.stageBlock(MNDBlocks.CRIMSON_FUNGUS_COLONY, MushroomColonyBlock.COLONY_AGE);
		this.stageBlock(MNDBlocks.WARPED_FUNGUS_COLONY, MushroomColonyBlock.COLONY_AGE);
		this.cabinetBlock(MNDBlocks.NETHER_BRICKS_CABINET, "nether_bricks");

		this.cabinetBlock(MNDBlocks.RED_NETHER_BRICKS_CABINET, "red_nether_bricks");


		// POWDERY
		generator.woodProvider(MNDBlocks.BLOCK_OF_POWDERY_CANNON).logUVLocked(MNDBlocks.BLOCK_OF_POWDERY_CANNON);
		generator.woodProvider(MNDBlocks.BLOCK_OF_STRIPPED_POWDERY_CANNON).logUVLocked(MNDBlocks.BLOCK_OF_STRIPPED_POWDERY_CANNON);

		this.crateBlock(MNDBlocks.BULLET_PEPPER_CRATE, "bullet_pepper");
		this.cabinetBlock(MNDBlocks.POWDERY_CABINET, "powdery");

		BlockFamily.Builder powdery = new BlockFamily.Builder(MNDBlocks.POWDERY_PLANKS);
		powdery.stairs(MNDBlocks.POWDERY_PLANKS_STAIRS);
		powdery.slab(MNDBlocks.POWDERY_PLANKS_SLAB);
		powdery.door(MNDBlocks.POWDERY_DOOR);
		powdery.trapdoor(MNDBlocks.POWDERY_TRAPDOOR);
		powdery.button(MNDBlocks.POWDERY_BUTTON);
		powdery.pressurePlate(MNDBlocks.POWDERY_PRESSURE_PLATE);
		powdery.sign(MNDBlocks.POWDERY_SIGN, MNDBlocks.POWDERY_WALL_SIGN);
		//? if >=26.2 {
		//powdery.customHangingSign(MNDBlocks.POWDERY_HANGING_SIGN, MNDBlocks.POWDERY_WALL_HANGING_SIGN);
		//?}
		generator.family(MNDBlocks.POWDERY_PLANKS).generateFor(powdery.getFamily());

		//? if <26.2 {
		generator.createHangingSign(MNDBlocks.POWDERY_PLANKS, MNDBlocks.POWDERY_HANGING_SIGN, MNDBlocks.POWDERY_WALL_HANGING_SIGN);
		//?}

		BlockFamily.Builder mosaic = new BlockFamily.Builder(MNDBlocks.POWDERY_MOSAIC);
		mosaic.stairs(MNDBlocks.POWDERY_MOSAIC_STAIRS);
		mosaic.slab(MNDBlocks.POWDERY_MOSAIC_SLAB);
		generator.family(MNDBlocks.POWDERY_MOSAIC).generateFor(mosaic.getFamily());

		// FEAST
		this.feastBlock((FeastBlock) MNDBlocks.GHASTA_WITH_CREAM_BLOCK);
		this.feastBlock((FeastBlock) MNDBlocks.STRIDERLOAF_BLOCK);
		this.feastBlock((FeastBlock) MNDBlocks.COLD_STRIDERLOAF_BLOCK);

	}

	@Override
	public void generateItemModels(ItemModelGenerators generator) {
	}

	private String blockName(Block block) {
		return BuiltInRegistries.BLOCK.getKey(block).getPath();
	}

	public ResourceLocation resourceBlock(String path) {
		return MyNethersDelight.id("block/" + path);
	}

	//? if >=26.1.2 {
	/*public Material material(String path) {
		return new Material(this.resourceBlock(path));
	}
	*///?} else {
	public ResourceLocation material(String path) {
		return this.resourceBlock(path);
	}
	//?}

	public void crateBlock(Block block, String cropName) {
		TextureMapping mapping = new TextureMapping()
				.put(TextureSlot.SIDE, this.material(cropName + "_crate_side"))
				.put(TextureSlot.BOTTOM, this.material(cropName + "_crate_bottom"))
				.put(TextureSlot.TOP, this.material(cropName + "_crate_top"));
		ResourceLocation model = ModelTemplates.CUBE_BOTTOM_TOP.create(block, mapping, this.generator.modelOutput);
		//? if >=1.21.11 {
		//this.generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, plainVariant(model)));
		//?} else {
		this.generator.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(block, model));
		//?}
	}

	public void cubeRandomRotation(Block block, String suffix) {
		String modelName = this.blockName(block) + (suffix.isEmpty() ? "" : "_" + suffix);
		ResourceLocation model = ModelTemplates.CUBE_ALL.createWithSuffix(block, suffix.isEmpty() ? "" : "_" + suffix,
				TextureMapping.cube(this.material(modelName)), this.generator.modelOutput);
		//? if >=1.21.11 {
		//this.generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, rotatedVariants(model)));
		//?} else {
		this.generator.blockStateOutput.accept(BlockModelGenerators.createRotatedVariant(block, model));
		//?}
	}

	public void stageBlock(Block block, IntegerProperty ageProperty) {
		//? if >=1.21.11 {
		/*this.generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
				.with(PropertyDispatch.initial(ageProperty).generate(age -> this.stageVariant(block, age))));
		*///?} else {
		PropertyDispatch dispatch = PropertyDispatch.property(ageProperty).generate(age -> {
			String stageName = this.blockName(block) + "_stage" + age;
			ResourceLocation model = ModelTemplates.CROSS.createWithSuffix(block, "_stage" + age,
					TextureMapping.cross(this.resourceBlock(stageName)), this.generator.modelOutput);
			return Variant.variant().with(VariantProperties.MODEL, model);
		});
		this.generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(dispatch));
		//?}
		int lastStage = ageProperty.getPossibleValues().stream().mapToInt(Integer::intValue).max().orElse(0);
		this.flatItemModel(block, TextureMapping.layer0(this.material(this.blockName(block) + "_stage" + lastStage)));
	}

	public void cabinetBlock(Block block, String woodType) {
		ResourceLocation closed = ModelTemplates.CUBE_ORIENTABLE.create(block, new TextureMapping()
				.put(TextureSlot.SIDE, this.material(woodType + "_cabinet_side"))
				.put(TextureSlot.FRONT, this.material(woodType + "_cabinet_front"))
				.put(TextureSlot.TOP, this.material(woodType + "_cabinet_top")), this.generator.modelOutput);
		ResourceLocation open = ModelTemplates.CUBE_ORIENTABLE.createWithSuffix(block, "_open", new TextureMapping()
				.put(TextureSlot.SIDE, this.material(woodType + "_cabinet_side"))
				.put(TextureSlot.FRONT, this.material(woodType + "_cabinet_front_open"))
				.put(TextureSlot.TOP, this.material(woodType + "_cabinet_top")), this.generator.modelOutput);
		//? if >=1.21.11 {
		/*this.generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
				.with(PropertyDispatch.initial(CabinetBlock.OPEN, BlockStateProperties.HORIZONTAL_FACING)
						.generate((openState, facing) -> this.cabinetVariant(openState ? open : closed, facing))));
		*///?} else {
		this.generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
				.with(PropertyDispatch.properties(CabinetBlock.OPEN, BlockStateProperties.HORIZONTAL_FACING)
						.generate((openState, facing) -> Variant.variant()
								.with(VariantProperties.MODEL, openState ? open : closed)
								.with(VariantProperties.Y_ROT, rotation(((int) facing.toYRot() + 180) % 360)))));
		//?}
	}

	public void feastBlock(FeastBlock block) {
		IntegerProperty servingsProperty = block.getServingsProperty();
		//? if >=1.21.11 {
		/*this.generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block)
				.with(PropertyDispatch.initial(servingsProperty, FeastBlock.FACING)
						.generate((servings, facing) -> this.feastVariant(block, servings, facing))));
		*///?} else {
		this.generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block)
				.with(PropertyDispatch.properties(servingsProperty, FeastBlock.FACING)
						.generate((servings, facing) -> {
							String suffix = "_stage" + (block.getMaxServings() - servings);
							if (servings == 0) {
								suffix = block.hasLeftovers ? "_leftover" : "_stage" + (servingsProperty.getPossibleValues().toArray().length - 2);
							}
							return Variant.variant()
									.with(VariantProperties.MODEL, this.resourceBlock(this.blockName(block) + suffix))
									.with(VariantProperties.Y_ROT, rotation(((int) facing.toYRot() + 180) % 360));
						})));
		//?}
		this.delegateItemModel(block.asItem(), this.resourceBlock(this.blockName(block) + "_stage0"));
	}

	//? if >=1.21.11 {
	/*private MultiVariant stageVariant(Block block, int age) {
		String stageName = this.blockName(block) + "_stage" + age;
		ResourceLocation model = ModelTemplates.CROSS.createWithSuffix(block, "_stage" + age,
				TextureMapping.cross(this.material(stageName)), this.generator.modelOutput);
		return plainVariant(model);
	}

	private MultiVariant cabinetVariant(ResourceLocation model, net.minecraft.core.Direction facing) {
		return plainVariant(model).with(variant -> variant.with(rotation(((int) facing.toYRot() + 180) % 360)));
	}

	private MultiVariant feastVariant(FeastBlock block, int servings, net.minecraft.core.Direction facing) {
		String suffix = "_stage" + (block.getMaxServings() - servings);
		if (servings == 0) {
			suffix = block.hasLeftovers ? "_leftover" : "_stage" + (block.getServingsProperty().getPossibleValues().toArray().length - 2);
		}
		ResourceLocation model = this.resourceBlock(this.blockName(block) + suffix);
		return plainVariant(model).with(variant -> variant.with(rotation(((int) facing.toYRot() + 180) % 360)));
	}

	private static MultiVariant plainVariant(ResourceLocation model) {
		return new MultiVariant(WeightedList.of(new Variant(model)));
	}

	private static MultiVariant rotatedVariants(ResourceLocation model) {
		Variant variant = new Variant(model);
		return new MultiVariant(WeightedList.of(
				new Weighted<>(variant, 1),
				new Weighted<>(variant.with(BlockModelGenerators.Y_ROT_90), 1),
				new Weighted<>(variant.with(BlockModelGenerators.Y_ROT_180), 1),
				new Weighted<>(variant.with(BlockModelGenerators.Y_ROT_270), 1)));
	}

	private static VariantMutator rotation(int degrees) {
		return switch (degrees % 360) {
			case 90 -> BlockModelGenerators.Y_ROT_90;
			case 180 -> BlockModelGenerators.Y_ROT_180;
			case 270 -> BlockModelGenerators.Y_ROT_270;
			default -> BlockModelGenerators.NOP;
		};
	}
	*///? }

	private void delegateItemModel(Item item, ResourceLocation model) {
		//? if >=1.21.11 {
		//this.generator.itemModelOutput.accept(item, ItemModelUtils.plainModel(model));
		//?} else {
		this.generator.delegateItemModel(item, model);
		//?}
	}

	private void flatItemModel(Block block, TextureMapping mapping) {
		Item item = block.asItem();
		if (item != Items.AIR) {
			//? if >=1.21.11 {
			/*ResourceLocation location = ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(item), mapping, this.generator.modelOutput);
			this.generator.itemModelOutput.accept(item, ItemModelUtils.plainModel(location));
			*///?} else {
			ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(item), mapping, this.generator.modelOutput);
			//?}
		}
	}

	//? if <1.21.11 {
	private static VariantProperties.Rotation rotation(int degrees) {
		return switch (degrees % 360) {
			case 90 -> VariantProperties.Rotation.R90;
			case 180 -> VariantProperties.Rotation.R180;
			case 270 -> VariantProperties.Rotation.R270;
			default -> VariantProperties.Rotation.R0;
		};
	}
	//?}
}
