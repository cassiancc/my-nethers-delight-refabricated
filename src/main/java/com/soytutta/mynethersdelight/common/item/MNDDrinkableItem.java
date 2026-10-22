package com.soytutta.mynethersdelight.common.item;

import net.minecraft.world.item.Item;

//? if >=1.21.11 {
//import vectorwing.farmersdelight.common.item.ConsumableItem;
//?} else {
import vectorwing.farmersdelight.common.item.DrinkableItem;
//?}

//? if >=1.21.11 {
/*public abstract class MNDDrinkableItem extends ConsumableItem {
	protected MNDDrinkableItem(Properties properties, boolean hasFoodContainer, boolean usingConvertsTo) {
		super(properties, hasFoodContainer, usingConvertsTo);
	}
}
*///?} else {
public abstract class MNDDrinkableItem extends DrinkableItem {
	protected MNDDrinkableItem(Properties properties, boolean hasFoodContainer, boolean usingConvertsTo) {
		super(properties, hasFoodContainer, usingConvertsTo);
	}
}
//?}
