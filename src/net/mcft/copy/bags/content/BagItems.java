package net.mcft.copy.bags.content;

import net.mcft.copy.bags.item.ItemBag;
import net.mcft.copy.bags.item.ItemPouch;

public final class BagItems {
	
	public static ItemBag bag = new ItemBag();
	public static ItemPouch pouch = new ItemPouch();
	
	private BagItems() {  }
	
	public static void register() {
		bag.register();
		pouch.register();
	}
	
}
