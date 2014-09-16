package net.mcft.copy.bags.addon;

import net.mcft.copy.bags.PocketBags;
import net.mcft.copy.bags.api.PouchAPI;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class Addon
{
	public static void register(){
		
		if(Loader.isModLoaded("Natura")){
			
			Item seedBase = GameRegistry.findItem("Natura", "barleySeed");
			Item dropBase = GameRegistry.findItem("Natura", "barleyFood");
			
			registerPouchItem(seedBase, 0, "barley_seeds", true, false);
			registerPouchItem(dropBase, 0, "barley", false, true);
			
			registerPouchItem(seedBase, 1, "cotton_seeds", true, false);
			registerPouchItem(dropBase, 3, "cotton", false, false);
		}
		
		if(Loader.isModLoaded("Thaumic Tinkerer")){
			
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
	
	private static void registerPouchItem(Item item, int damage, String icon, boolean isSeed, boolean isFood) {
		PouchAPI.register(item, damage, PocketBags.MOD_ID.toLowerCase() + ":pouch/" + icon, isSeed, isFood);
	}
	private static void registerPouchItem(Item item, String icon, boolean isSeed, boolean isFood) {
		PouchAPI.register(item, PocketBags.MOD_ID.toLowerCase() + ":pouch/" + icon, isSeed, isFood);
	}
}
