package net.mcft.copy.bags.addon;

import java.util.Arrays;
import java.util.List;

import net.mcft.copy.bags.PocketBags;
import net.mcft.copy.bags.api.PouchAPI;
import net.mcft.copy.core.util.StackUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

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
	protected void registerPouchItem(String name, int damage, String icon, boolean isSeed, boolean isFood) {
		registerPouchItem(GameRegistry.findItem(modId, name), damage, icon, isSeed, isFood);
	}
	
	protected void registerPouchItem(ItemStack stack, String icon, boolean isSeed, boolean isFood) {
		int damage = StackUtils.getDamage(stack);
		if (damage != OreDictionary.WILDCARD_VALUE)
			registerPouchItem(stack.getItem(), damage, icon, isSeed, isFood);
		else registerPouchItem(stack.getItem(), icon, isSeed, isFood);
	}
	protected void registerPouchItem(String name, String icon, boolean isSeed, boolean isFood) {
		registerPouchItem(GameRegistry.findItemStack(modId, name, 0), icon, isSeed, isFood);
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
