package net.mcft.copy.bags.addon;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class ThaumicTinkererAddon extends Addon {
	
	private static final String[] types = { "aer", "ignis", "terra", "aqua" };
	
	public static final ThaumicTinkererAddon instance = new ThaumicTinkererAddon();
	
	private ThaumicTinkererAddon() { super("ThaumicTinkerer"); }
	
	@Override
	public void register() {
		
		Item infusedSeed = GameRegistry.findItem("ThaumicTinkerer", "infusedSeeds");
		Item infusedGrain = GameRegistry.findItem("ThaumicTinkerer", "infusedGrain");
		
		for (int i = 0; i < types.length; i++) {
			registerPouchItem(infusedSeed, i, types[i] + "_seeds", true, false);
			registerPouchItem(infusedGrain, i, types[i] + "_grain", false, false);
		}
		
	}
	
}
