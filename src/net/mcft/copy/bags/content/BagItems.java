package net.mcft.copy.bags.content;

import net.mcft.copy.bags.item.ItemSeedBag;
import net.mcft.copy.core.util.RegistryUtils;

public final class BagItems {
	
	public static ItemSeedBag seedBag;
	
	private BagItems() {  }
	
	public static void register() {
		
		seedBag = RegistryUtils.registerIfEnabled(new ItemSeedBag());
		
	}
	
}
