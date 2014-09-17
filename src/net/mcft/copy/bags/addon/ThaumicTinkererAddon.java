package net.mcft.copy.bags.addon;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class ThaumicTinkererAddon extends Addon {
	
	public static final ThaumicTinkererAddon instance = new ThaumicTinkererAddon();
	
	private ThaumicTinkererAddon() { super("ThaumicTinkerer"); }
	
	@Override
	public void register() {
		
		Item infusedSeed = GameRegistry.findItem("ThaumicTinkerer", "infusedSeeds");
		Item infusedGrain = GameRegistry.findItem("ThaumicTinkerer", "infusedGrain");
		
		registerPouchItem(infusedSeed, 0, "aer_seeds", true, false);
		registerPouchItem(infusedSeed, 1, "ignis_seeds", true, false);
		registerPouchItem(infusedSeed, 2, "terra_seeds", true, false);
		registerPouchItem(infusedSeed, 3, "aqua_seeds", true, false);
		
		registerPouchItem(infusedGrain, 0, "aer_grain", true, false);
		registerPouchItem(infusedGrain, 1, "ignis_grain", true, false);
		registerPouchItem(infusedGrain, 2, "terra_grain", true, false);
		registerPouchItem(infusedGrain, 3, "aqua_grain", true, false);
		
	}
	
}
