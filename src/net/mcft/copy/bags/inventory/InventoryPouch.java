package net.mcft.copy.bags.inventory;

import net.mcft.copy.bags.item.ItemPouch;
import net.mcft.copy.core.util.StackUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class InventoryPouch extends InventoryItem {
	
	public InventoryPouch(EntityPlayer player) {
		super(player, ItemPouch.SLOTS);
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		if (stack == null) return true;
		ItemStack presentStack = getItem(slot);
		return ((presentStack != null)
				? StackUtils.equals(stack, presentStack, false, false)
				: ItemPouch.isPouchItem(stack));
	}
	
	private ItemStack getItem(int except) {
		ItemStack stack;
		for (int slot = 0; slot < getSizeInventory(); slot++)
			if (!(slot == except) && ((stack = getStackInSlot(slot)) != null))
				return stack;
		return null;
	}
	
}
