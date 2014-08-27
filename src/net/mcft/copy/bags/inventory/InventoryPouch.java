package net.mcft.copy.bags.inventory;

import net.mcft.copy.bags.api.PouchAPI;
import net.mcft.copy.bags.item.ItemPouch;
import net.mcft.copy.core.container.ContainerBase;
import net.mcft.copy.core.util.StackUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryPouch extends InventoryItem {
	
	public InventoryPouch(EntityPlayer player) {
		super(player, ItemPouch.SLOTS);
	}
	public InventoryPouch(ItemStack item) {
		super(item, ItemPouch.SLOTS);
	}
	
	/** Returns the player's current pouch inventory if
	 *  the slot is the same or a new one otherwise. */
	public static InventoryPouch getOrCreate(EntityPlayer player, int slot) {
		Container container = ((EntityPlayerMP)player).openContainer;
		if (container instanceof ContainerBase) {
			IInventory inventory = ((ContainerBase)container).inventory;
			if (inventory instanceof InventoryPouch) {
				InventoryPouch pouch = (InventoryPouch)inventory;
				if (pouch.slot == slot) return pouch;
			}
		}
		return new InventoryPouch(player.inventory.getStackInSlot(slot));
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		if (stack == null) return true;
		ItemStack presentStack = getItem(slot);
		return ((presentStack != null)
				? StackUtils.equals(stack, presentStack, false, false)
				: (PouchAPI.getEntry(stack) != null));
	}
	
	private ItemStack getItem(int except) {
		ItemStack stack;
		for (int slot = 0; slot < getSizeInventory(); slot++)
			if (!(slot == except) && ((stack = getStackInSlot(slot)) != null))
				return stack;
		return null;
	}
	public ItemStack getItem() { return getItem(-1); }
	
	public ItemStack getItems() {
		ItemStack result = null;
		ItemStack stack;
		for (int slot = 0; slot < getSizeInventory(); slot++)
			if ((stack = getStackInSlot(slot)) != null) {
				if (result == null) {
					result = stack.copy();
					result.setTagCompound(null);
				} else result.stackSize += stack.stackSize;
			}
		return result;
	}
	
}
