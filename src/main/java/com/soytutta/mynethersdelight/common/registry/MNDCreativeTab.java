package com.soytutta.mynethersdelight.common.registry;

import com.soytutta.mynethersdelight.MyNethersDelight;
import com.soytutta.mynethersdelight.common.MNDConfiguration;
import com.soytutta.mynethersdelight.common.utility.MNDTextUtils;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import vectorwing.farmersdelight.common.registry.ModItems;

import java.util.function.Consumer;

import static com.soytutta.mynethersdelight.MyNethersDelight.xplat;

public class MNDCreativeTab {
	public static final CreativeModeTab MY_NETHERS_DELIGHT_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), MyNethersDelight.id("main")), FabricItemGroup.builder().title(MNDTextUtils.getTranslation("itemGroup.main")).icon(MNDItems.NETHER_STOVE::getDefaultInstance).displayItems(MNDCreativeTab::fillTab).build());

	private static void fillTab(CreativeModeTab.ItemDisplayParameters parameters, CreativeModeTab.Output output) {
		Consumer<Item> add = (Item item) -> output.accept(new ItemStack(item));

		if (true) {
			add.accept(MNDItems.BLAZIER);
		}
		add.accept(MNDItems.NETHER_STOVE);
		if (true) {
			add.accept(MNDItems.RED_NETHER_BRICKS_CABINET);
			add.accept(MNDItems.NETHER_BRICKS_CABINET);
			add.accept(MNDItems.BLACKSTONE_BRICKS_CABINET);
		}
		add.accept(MNDItems.POWDERY_CABINET);
		add.accept(MNDItems.BULLET_PEPPER_CRATE);
		add.accept(MNDItems.LETIOS_COMPOST);
		add.accept(MNDItems.RESURGENT_SOIL);
		add.accept(MNDItems.RESURGENT_SOIL_FARMLAND);

		add.accept(MNDItems.POWDER_CANNON);
		add.accept(MNDItems.POWDERY_TORCH);
		add.accept(MNDItems.BLOCK_OF_POWDERY_CANNON);
		add.accept(MNDItems.BLOCK_OF_STRIPPED_POWDERY_CANNON);
		add.accept(MNDItems.POWDERY_PLANKS);
		add.accept(MNDItems.POWDERY_PLANKS_STAIRS);
		add.accept(MNDItems.POWDERY_PLANKS_SLAB);
		add.accept(MNDItems.POWDERY_MOSAIC);
		add.accept(MNDItems.POWDERY_MOSAIC_STAIRS);
		add.accept(MNDItems.POWDERY_MOSAIC_SLAB);

		add.accept(MNDItems.POWDERY_FENCE);
		add.accept(MNDItems.POWDERY_FENCE_GATE);

		add.accept(MNDItems.POWDERY_DOOR);
		add.accept(MNDItems.POWDERY_TRAPDOOR);
		add.accept(MNDItems.POWDERY_PRESSURE_PLATE);
		add.accept(MNDItems.POWDERY_BUTTON);
		add.accept(MNDItems.POWDERY_SIGN);
		add.accept(MNDItems.POWDERY_HANGING_SIGN);

		if (xplat().isModLoaded("farmersdelight")) {
			add.accept(ModItems.NETHERITE_KNIFE.get());
			add.accept(ModItems.GOLDEN_KNIFE.get());
		}

		add.accept(MNDItems.CRIMSON_FUNGUS_COLONY);
		add.accept(MNDItems.WARPED_FUNGUS_COLONY);

		add.accept(MNDItems.GOLDEN_TROPHY);
		add.accept(MNDItems.HOGLIN_TROPHY);
		add.accept(MNDItems.ZOGLIN_TROPHY);
		add.accept(MNDItems.SKOGLIN_TROPHY);
		add.accept(MNDItems.WAXED_HOGLIN_TROPHY);

		add.accept(MNDItems.HOGLIN_HIDE);

		add.accept(MNDItems.HOGLIN_LOIN);
		add.accept(MNDItems.HOGLIN_SAUSAGE);
		add.accept(MNDItems.ROASTED_SAUSAGE);
		add.accept(MNDItems.HOTDOG);
		add.accept(MNDItems.HOTDOG_WITH_MIXED_SALAD);
		add.accept(MNDItems.HOTDOG_WITH_NETHER_SALAD);
		add.accept(MNDItems.SAUSAGE_AND_POTATOES);
		add.accept(MNDItems.BREAKFAST_SAMPLER);
		add.accept(MNDItems.COOKED_LOIN);
		add.accept(MNDItems.NETHER_BURGER);
		add.accept(MNDItems.BLUE_TENDERLOIN_STEAK);
		add.accept(MNDItems.FRIED_HOGLIN_CHOP);
		add.accept(MNDItems.RED_LOIN_STICK);
		add.accept(MNDItems.BACON_WRAPPED_SAUSAGE_STICK);

		add.accept(MNDItems.RAW_STUFFED_HOGLIN);
		add.accept(MNDItems.ROAST_STUFFED_HOGLIN);
		add.accept(MNDItems.ROAST_EAR);
		add.accept(MNDItems.PLATE_OF_STUFFED_HOGLIN_SNOUT);
		add.accept(MNDItems.PLATE_OF_STUFFED_HOGLIN_HAM);
		add.accept(MNDItems.PLATE_OF_STUFFED_HOGLIN);

		add.accept(MNDItems.STRIDER_ROCK);
		add.accept(MNDItems.STRIDER_EGG);
		add.accept(MNDItems.GOLDEN_EGG);
		add.accept(MNDItems.ENCHANTED_GOLDEN_EGG);
		add.accept(MNDItems.BOILED_EGG);
		add.accept(MNDItems.DEVILED_EGG);
		add.accept(MNDItems.SCOTCH_EGGS);
		add.accept(MNDItems.EGG_SOUP);
		add.accept(MNDItems.STRIDER_SLICE);
		add.accept(MNDItems.MINCED_STRIDER);
		add.accept(MNDItems.BLEEDING_TARTAR);
		add.accept(MNDItems.STRIDER_WITH_GRILLED_FUNGUS);
		add.accept(MNDItems.STRIDER_STEW);
		add.accept(MNDItems.CRIMSON_STROGANOFF);
		add.accept(MNDItems.STRIDERLOAF_BLOCK);
		add.accept(MNDItems.COLD_STRIDERLOAF_BLOCK);
		add.accept(MNDItems.STRIDERLOAF);
		add.accept(MNDItems.COLD_STRIDERLOAF);

		add.accept(MNDItems.TEAR_POPSICLE);
		add.accept(MNDItems.GHASTA);
		add.accept(MNDItems.TWISTED_GHASTA);
		add.accept(MNDItems.SPICY_NOODLE_SOUP);
		add.accept(MNDItems.FRIES_GHASTA);
		add.accept(MNDItems.SPICY_COTTON);
		add.accept(MNDItems.GHASMATI);
		add.accept(MNDItems.GHAST_SALAD);
		add.accept(MNDItems.DRIED_GHAST_WITH_MILK);
		add.accept(MNDItems.SIZZLING_PUDDING);
		add.accept(MNDItems.GHASTA_WITH_CREAM_BLOCK);
		add.accept(MNDItems.GHASTA_WITH_CREAM);
		add.accept(MNDItems.GIANT_TAKOYAKI);
		add.accept(MNDItems.GHAST_DOUGH);
		add.accept(MNDItems.GHAST_SOURDOUGH);
		add.accept(MNDItems.BREAD_LOAF_BLOCK);
		add.accept(MNDItems.SLICES_OF_BREAD);
		add.accept(MNDItems.TOASTS);

		add.accept(MNDItems.BULLET_PEPPER);
		add.accept(MNDItems.PEPPER_POWDER);
		add.accept(MNDItems.STUFFED_PEPPER);
		add.accept(MNDItems.SPICY_SKEWER);
		add.accept(MNDItems.CHILIDOG);
		add.accept(MNDItems.SPICY_HOGLIN_STEW);
		add.accept(MNDItems.HOT_WINGS);
		add.accept(MNDItems.HOT_WINGS_BUCKET);
		add.accept(MNDItems.SPICY_CURRY);

		add.accept(MNDItems.ROCK_SOUP);
		add.accept(MNDItems.BURNT_ROLL);
		add.accept(MNDItems.MAGMA_CAKE);
		add.accept(MNDItems.MAGMA_CAKE_SLICE);

		add.accept(MNDItems.HOT_CREAM);
		add.accept(MNDItems.HOT_CREAM_CONE);
	}

	public static void initialize() {

	}
}
