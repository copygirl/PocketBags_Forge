package net.mcft.copy.bags.content;

import net.mcft.copy.bags.PocketBags;
import net.mcft.copy.bags.api.PouchAPI;
import net.mcft.copy.bags.item.ItemPouch;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public final class BagItems {
	
	public static ItemPouch pouch = new ItemPouch();
	
	private BagItems() {  }
	
	public static void register() {
		
		pouch.register();
		
		registerPouchItems();
		
	}
	
	public static void registerPouchItems() {
		
		registerPouchItem(Items.wheat_seeds, "wheat_seeds", true, true);
		registerPouchItem(Items.wheat, "wheat", false, true);
		
		registerPouchItem(Items.melon_seeds, "melon_seeds", true, true);
		registerPouchItem(Items.melon, "melon", false, true);
		
		registerPouchItem(Items.pumpkin_seeds, "pumpkin_seeds", true, true);
		
		registerPouchItem(Items.potato, "potato", true, false);
		registerPouchItem(Items.carrot, "carrot", true, true);
		
		registerPouchItem(Items.apple, "apple", false, false);
		registerPouchItem(Items.reeds, "reeds", true, false);
		
		registerPouchItem(Items.dye, 15, "bonemeal", true, false);
		registerPouchItem(Items.dye, 3, "cocoa_beans", true, false);
		
	}
	private static void registerPouchItem(Item item, int damage, String icon, boolean isSeed, boolean isFood) {
		PouchAPI.register(item, damage, PocketBags.MOD_ID.toLowerCase() + ":pouch/" + icon, isSeed, isFood);
	}
	private static void registerPouchItem(Item item, String icon, boolean isSeed, boolean isFood) {
		PouchAPI.register(item, PocketBags.MOD_ID.toLowerCase() + ":pouch/" + icon, isSeed, isFood);
	}
	
}
