package net.mcft.copy.bags.addon;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class NaturaAddon extends Addon {
	
	public static final NaturaAddon instance = new NaturaAddon();
	
	private NaturaAddon() { super("Natura"); }
	
	@Override
	public void register() {
		
		Item seedBase = GameRegistry.findItem("Natura", "barleySeed");
		Item dropBase = GameRegistry.findItem("Natura", "barleyFood");
		
		registerPouchItem(seedBase, 0, "barley_seeds", true, false);
		registerPouchItem(dropBase, 0, "barley", false, true);
		
		registerPouchItem(seedBase, 1, "cotton_seeds", true, false);
		registerPouchItem(dropBase, 3, "cotton", false, false);
		
	}
	
}
