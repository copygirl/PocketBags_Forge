package net.mcft.copy.bags.api;

import java.util.ArrayList;
import java.util.List;

import net.mcft.copy.core.util.StackUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class PouchAPI {
	
	private static final List<Entry> entries = new ArrayList<Entry>();
	
	private PouchAPI() {  }
	
	
	/** Registers an item with specific damage to go into pouches. <br>
	 *  If isSeed is true, the pouch can be used to plant an area. <br>
	 *  If isFood is true, the pouch can be used to feed animals within an area. */
	public static void register(Item item, int damage, String icon,
	                            boolean isSeed, boolean isFood) {
		if (item == null) throw new IllegalArgumentException("item is null");
		entries.add(new Entry(item, damage, icon, isSeed, isFood));
	}
	/** Registers an item to go into pouches. <br>
	 *  If isSeed is true, the pouch can be used to plant an area. <br>
	 *  If isFood is true, the pouch can be used to feed animals within an area. */
	public static void register(Item item, String icon,
	                            boolean isSeed, boolean isFood) {
		register(item, -1, icon, isSeed, isFood);
	}
	
	/** Gets a pouch entry for this item stack, or null if none match. */
	public static Entry getEntry(ItemStack stack) {
		if (stack != null)
			for (Entry entry : entries)
				if (entry.matches(stack))
					return entry;
		return null;
	}
	
	/** Returns all pouch entries. */
	public static Iterable<Entry> getEntries() { return entries; }
	
	
	public static class Entry {
		
		public final Item item;
		public final int damage;
		public final String icon;
		public final boolean isSeed;
		public final boolean isFood;
		
		private Entry(Item item, int damage, String icon,
		              boolean isSeed, boolean isFood) {
			this.item = item;
			this.damage = damage;
			this.icon = icon;
			this.isSeed = isSeed;
			this.isFood = isFood;
		}
		
		public boolean matches(ItemStack stack) {
			return ((stack.getItem() == item) &&
			        ((damage == -1) || (damage == StackUtils.getDamage(stack))));
		}
		
	}
	
}
