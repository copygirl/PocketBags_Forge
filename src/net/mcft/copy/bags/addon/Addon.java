package net.mcft.copy.bags.addon;

import java.util.Arrays;
import java.util.List;

import net.mcft.copy.bags.PocketBags;
import net.mcft.copy.bags.api.PouchAPI;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Loader;

public abstract class Addon {
	
	public final String modId;
	
	public Addon(String modId) {
		this.modId = modId;
	}
	
	public abstract void register();
	
	protected void registerPouchItem(Item item, int damage, String icon, boolean isSeed, boolean isFood) {
		PouchAPI.register(item, damage, PocketBags.MOD_ID.toLowerCase() + ":pouch/" + modId.toLowerCase() + "/" + icon, isSeed, isFood);
	}
	protected void registerPouchItem(Item item, String icon, boolean isSeed, boolean isFood) {
		PouchAPI.register(item, PocketBags.MOD_ID.toLowerCase() + ":pouch/" + modId.toLowerCase() + "/" + icon, isSeed, isFood);
	}
	
	public static void registerAll() {
		
		List<Addon> addons = Arrays.asList(
				NaturaAddon.instance,
				ThaumicTinkererAddon.instance);
		
		for (Addon addon : addons)
			if (Loader.isModLoaded(addon.modId))
				addon.register();
		
	}
	
}
