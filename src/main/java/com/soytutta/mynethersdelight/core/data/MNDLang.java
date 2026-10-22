package com.soytutta.mynethersdelight.core.data;

import com.soytutta.mynethersdelight.MyNethersDelight;
import com.soytutta.mynethersdelight.common.registry.MNDBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class MNDLang extends FabricLanguageProvider {

	private final Set<String> addedKeys = new HashSet<>();

	public MNDLang(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, "en_us", registriesFuture);
	}

	@Override
	public void generateTranslations(HolderLookup.Provider wrapperLookup, TranslationBuilder translationBuilder) {
		Set<Block> blocks = BuiltInRegistries.BLOCK.stream()
				.filter(b -> MyNethersDelight.MOD_ID.equals(BuiltInRegistries.BLOCK.getKey(b).getNamespace()))
				.collect(Collectors.toSet());

		Set<Item> items = BuiltInRegistries.ITEM.stream()
				.filter(i -> MyNethersDelight.MOD_ID.equals(BuiltInRegistries.ITEM.getKey(i).getNamespace()))
				.collect(Collectors.toSet());

		blocks.removeIf(block -> Set.of(
				MNDBlocks.LETIOS_COMPOST,
				MNDBlocks.BLOCK_OF_POWDERY_CANNON,
				MNDBlocks.BLOCK_OF_STRIPPED_POWDERY_CANNON,
				MNDBlocks.WALL_POWDERY_TORCH,
				MNDBlocks.POWDERY_WALL_SIGN,
				MNDBlocks.POWDERY_WALL_HANGING_SIGN
		).contains(block));

		blocks.forEach((b) -> {
			String descriptionId = b.getDescriptionId();
			if (addedKeys.add(descriptionId)) {
				String name = descriptionId.replaceFirst("block.mynethersdelight.", "");
				name = toTitleCase(this.correctBlockItemName(name), "_").replace("Of", "of");
				name = toTitleCase(this.correctBlockItemName(name), "_").replace(" Block", "");
				translationBuilder.add(descriptionId, name);
			}
		});

		items.removeIf((i) -> i instanceof BlockItem);
		items.forEach((i) -> {
			String descriptionId = i.getDescriptionId();
			if (addedKeys.add(descriptionId)) {
				String name = descriptionId.replaceFirst("item.mynethersdelight.", "");
				name = toTitleCase(this.correctBlockItemName(name), "_").replace("A", "a");
				name = toTitleCase(this.correctBlockItemName(name), "_").replace("On", "on");
				name = toTitleCase(this.correctBlockItemName(name), "_").replace("Of", "of");
				name = toTitleCase(this.correctBlockItemName(name), "_").replace("And", "and");
				name = toTitleCase(this.correctBlockItemName(name), "_").replace("With", "with");
				translationBuilder.add(descriptionId, name);
			}
		});

		addCustomTranslations(translationBuilder);
	}

	private void addCustomTranslations(TranslationBuilder translationBuilder) {
		safeAdd(translationBuilder, "block.mynethersdelight.letios_compost", "Leteos Compost");
		safeAdd(translationBuilder, "block.mynethersdelight.powdery_block", "Block of Powdery Cannon");
		safeAdd(translationBuilder, "block.mynethersdelight.stripped_powdery_block", "Block of Stripped Powdery Cannon");

		safeAdd(translationBuilder, "mynethersdelight.itemGroup.main", "My Nether's Delight");

		safeAdd(translationBuilder, "effect.mynethersdelight.b_pungent", "Caustic Pungency");
		safeAdd(translationBuilder, "effect.mynethersdelight.b_pungent.desc", "Induces combustion upon heat exposure; flames are automatically doused when at a safe distance.");

		safeAdd(translationBuilder, "effect.mynethersdelight.g_pungent", "Invigorating Pungency");
		safeAdd(translationBuilder, "effect.mynethersdelight.g_pungent.desc", "Provides natural regeneration while exposed to heat.");

		safeAdd(translationBuilder, "mynethersdelight.jei.forgoting", "Forgetting");
		safeAdd(translationBuilder, "mynethersdelight.jei.forgoting.nether", "Will only forget in the nether");
		safeAdd(translationBuilder, "mynethersdelight.jei.forgoting.accelerators", "Sped up by adjacent activators (see below)");
		safeAdd(translationBuilder, "mynethersdelight.jei.forgoting.light", "Sped up by adjacent flames (see below)");
		safeAdd(translationBuilder, "mynethersdelight.jei.forgoting.fluid", "Sped up by adjacent lava");

		safeAdd(translationBuilder, "block.mynethersdelight.blazier.too_hot", "The fire is too intense.");
		safeAdd(translationBuilder, "block.mynethersdelight.blazier.too_cold", "The fire isn't burning hot enough.");
		safeAdd(translationBuilder, "tooltip.mynethersdelight.blazier.heat", "Heat: %s");
		safeAdd(translationBuilder, "tooltip.mynethersdelight.blazier.heat.smelting", "White-hot");
		safeAdd(translationBuilder, "tooltip.mynethersdelight.blazier.heat.baking", "Blazing");
		safeAdd(translationBuilder, "tooltip.mynethersdelight.blazier.heat.campfire", "Live flame");
		safeAdd(translationBuilder, "tooltip.mynethersdelight.blazier.heat.smoking", "Embers");
		safeAdd(translationBuilder, "tooltip.mynethersdelight.blazier.heat.extinguished", "Blazier Base");

		safeAdd(translationBuilder, "mynethersdelight.block.feast.space_required", "You need more space to serve this.");
		safeAdd(translationBuilder, "mynethersdelight.block.feast.use_knife", "You need a Knife to cut this.");
		safeAdd(translationBuilder, "mynethersdelight.block.feast.use_container", "You need a %s to serve this.");

		safeAdd(translationBuilder, "mynethersdelight.configuration.title", "My Nether's Delight — Settings");
		safeAdd(translationBuilder, "mynethersdelight.configuration.section.mynethersdelight.common.toml", "Common settings");
		safeAdd(translationBuilder, "mynethersdelight.configuration.section.mynethersdelight.common.toml.title", "Common settings");
		safeAdd(translationBuilder, "mynethersdelight.configuration.settings", "General");
		safeAdd(translationBuilder, "mynethersdelight.configuration.settings.button", "General");
		safeAdd(translationBuilder, "mynethersdelight.configuration.settings.tooltip", "Configure general gameplay settings.");
		safeAdd(translationBuilder, "mynethersdelight.configuration.enablePiglinFoodTrades", "Enable Piglin Food Trades");
		safeAdd(translationBuilder, "mynethersdelight.configuration.enablePiglinFoodTrades.tooltip", "Allows Piglins to barter food items and Strider Rocks from this mod. Special hunting-related trades are not affected.");
		safeAdd(translationBuilder, "mynethersdelight.configuration.piglinFoodTradeChance", "Piglin Food Trade Chance");
		safeAdd(translationBuilder, "mynethersdelight.configuration.piglinFoodTradeChance.tooltip", "The chance that a Piglin barter gives food or Strider Rocks from this mod. Uses a decimal percentage: 0.25 means 25%.");
		safeAdd(translationBuilder, "mynethersdelight.configuration.enableFrogMagmaCakeBehavior", "Enable Frog Magma Cake Behavior");
		safeAdd(translationBuilder, "mynethersdelight.configuration.enableFrogMagmaCakeBehavior.tooltip", "Allows Frogs to seek out Magma Cake and Magma Cake Slices, and lets players feed slices directly to a Frog.");
		safeAdd(translationBuilder, "mynethersdelight.configuration.farming", "Farming");
		safeAdd(translationBuilder, "mynethersdelight.configuration.farming.button", "Farming");
		safeAdd(translationBuilder, "mynethersdelight.configuration.farming.tooltip", "Configure Resurgent Soil and Resurgent Soil Farmland behavior.");
		safeAdd(translationBuilder, "mynethersdelight.configuration.enableResurgentSoilPropagation", "Enable Resurgent Soil Propagation");
		safeAdd(translationBuilder, "mynethersdelight.configuration.enableResurgentSoilPropagation.tooltip", "Allows Resurgent Soil and Resurgent Soil Farmland to propagate nearby plants.");
		safeAdd(translationBuilder, "mynethersdelight.configuration.resurgentSoilTickMultiplier", "Resurgent Soil Tick Multiplier");
		safeAdd(translationBuilder, "mynethersdelight.configuration.resurgentSoilTickMultiplier.tooltip", "Multiplies the number of growth, transformation and propagation attempts made by Resurgent Soil and Resurgent Soil Farmland. Farmland hydration and drying are not affected.");
		safeAdd(translationBuilder, "mynethersdelight.configuration.resurgentSoilGrowthRange", "Resurgent Soil Growth Range");
		safeAdd(translationBuilder, "mynethersdelight.configuration.resurgentSoilGrowthRange.tooltip", "The maximum vertical distance that Resurgent Soil and Resurgent Soil Farmland follow connected plants while applying growth.");
		safeAdd(translationBuilder, "mynethersdelight.configuration.resurgentFarmlandHeatSearchRadius", "Farmland Heat Search Radius");
		safeAdd(translationBuilder, "mynethersdelight.configuration.resurgentFarmlandHeatSearchRadius.tooltip", "The horizontal radius used to search for valid heat sources. Vertical range is half this value. Set to 0 to disable heat hydration.");
		safeAdd(translationBuilder, "mynethersdelight.configuration.crafting", "Crafting");
		safeAdd(translationBuilder, "mynethersdelight.configuration.crafting.button", "Crafting");
		safeAdd(translationBuilder, "mynethersdelight.configuration.crafting.tooltip", "Configure craftable features and Blazier behavior.");
		safeAdd(translationBuilder, "mynethersdelight.configuration.enableBlazier", "Enable Blazier");
		safeAdd(translationBuilder, "mynethersdelight.configuration.enableBlazier.tooltip", "Master switch for the Blazier. Disabling hides its creative-tab item and recipes, and stops its behavior.");
		safeAdd(translationBuilder, "mynethersdelight.configuration.blazierCookingTimeMultiplier", "Blazier Cooking Time Multiplier");
		safeAdd(translationBuilder, "mynethersdelight.configuration.blazierCookingTimeMultiplier.tooltip", "Multiplies cooking time in every Blazier mode while preserving their original 6 / 3 / 1 / 6 time proportions. Higher values take longer.");
		safeAdd(translationBuilder, "mynethersdelight.configuration.enableStoneCabinets", "Enable Stone Cabinets");
		safeAdd(translationBuilder, "mynethersdelight.configuration.enableStoneCabinets.tooltip", "Enables Nether Brick, Red Nether Brick and Blackstone Brick Cabinets. Disabling hides their creative-tab items and recipes.");
		safeAdd(translationBuilder, "mynethersdelight.configuration.world", "World");
		safeAdd(translationBuilder, "mynethersdelight.configuration.world.button", "World");
		safeAdd(translationBuilder, "mynethersdelight.configuration.world.tooltip", "Configure world generation.");
		safeAdd(translationBuilder, "mynethersdelight.configuration.generatePowderyCane", "Generate Powdery Cane");
		safeAdd(translationBuilder, "mynethersdelight.configuration.generatePowderyCane.tooltip", "Controls whether Powdery Cane patches generate naturally in Crimson Forests.");

		safeAdd(translationBuilder, "tooltip.farmersdelight.strider_egg", "Nourished by 1 Harmful Effect");
		safeAdd(translationBuilder, "tooltip.farmersdelight.golden_egg", "Nourished by all harmful effects");

		safeAdd(translationBuilder, "tooltip.farmersdelight.enchanted_golden_egg", "Nourished by all harmful effects");

		safeAdd(translationBuilder, "tooltip.farmersdelight.hot_cream", "Burning Effects");

		safeAdd(translationBuilder, "farmersdelight.tooltip.strider_feed.when_feeding", "When fed to a Strider:");
		safeAdd(translationBuilder, "tooltip.farmersdelight.magma_cake_slice", "Spicy frog Snack");

		safeAdd(translationBuilder, "mynethersdelight.jei.info.strider_egg", "A peculiar find, suggesting Striders may be ovoviviparous.\n\nObtainable only through careful hunting.\nhard shell, it can only be eaten starry...");

		safeAdd(translationBuilder, "mynethersdelight.jei.info.hoglin_hide", "A great source of leather which also serves as a nice hunting trophy.\n\nTo avoid damaging the hide, using something lighter than a Sword...");

		safeAdd(translationBuilder, "mynethersdelight.jei.info.mushroom_colony", "Some Mushrooms form colonies when they are on top of Rich Soil or Resurgent Soil.");
		safeAdd(translationBuilder, "mynethersdelight.jei.info.fungus_colony", "Some Nethers Fungus form colonies when they are on top of Resurgent Soil.");

		safeAdd(translationBuilder, "mynethersdelight.jei.info.wild_powdery", "An invasive crop found in Crimson Forests. Its Berries are explosive on Contact, Try Cutting the red stem...");

		safeAdd(translationBuilder, "enchantment.mynethersdelight.poaching", "Curse of Poaching");
		safeAdd(translationBuilder, "enchantment.mynethersdelight.poaching.desc", "Responsible hunting can provide you with extra ingredients!!!\nBut be careful about choosing your target...\n\nThe weapon will be penalty for each hunting attempt");

		safeAdd(translationBuilder, "item.mynethersdelight.bullet_pepper", "Bullet Pepper");

		safeAdd(translationBuilder, "item.minersdelight.strider_stew_cup", "Strider Stew Cup");
		safeAdd(translationBuilder, "item.minersdelight.spicy_noodle_soup_cup", "Spicy Noodle Soup");
		safeAdd(translationBuilder, "item.minersdelight.spicy_hoglin_stew_cup", "Spicy Hoglin Stew Cup");
		safeAdd(translationBuilder, "item.minersdelight.rock_soup_cup", "Rock Soup Cup");
		safeAdd(translationBuilder, "item.minersdelight.egg_soup_cup", "Egg Soup Cup");
	}

	private void safeAdd(TranslationBuilder builder, String key, String value) {
		if (addedKeys.add(key)) {
			builder.add(key, value);
		}
	}

	public static String toTitleCase(String givenString, String regex) {
		String[] var4 = givenString.split(regex);
		StringBuilder stringBuilder = new StringBuilder();

		for (String string : var4) {
			stringBuilder.append(Character.toUpperCase(string.charAt(0))).append(string.substring(1)).append(regex);
		}

		return stringBuilder.toString().trim().replaceAll(regex, " ").substring(0, stringBuilder.length() - 1);
	}

	public String correctBlockItemName(String name) {
		if (!name.endsWith("_bricks") && name.contains("bricks")) {
			name = name.replaceFirst("bricks", "brick");
		}

		if ((name.contains("_fence") || name.contains("_button")) && name.contains("planks")) {
			name = name.replaceFirst("_planks", "");
		}

		return name;
	}
}
