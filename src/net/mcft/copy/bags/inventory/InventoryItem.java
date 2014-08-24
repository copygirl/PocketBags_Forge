package net.mcft.copy.bags.inventory;

import net.mcft.copy.core.inventory.InventoryStacks;
import net.mcft.copy.core.util.NbtUtils;
import net.mcft.copy.core.util.StackUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;

public class InventoryItem extends InventoryStacks {
	
	public static final String TAG_ITEMS = "Items";
	
	public final EntityPlayer player;
	public final ItemStack item;
	public final int slot;
	
	public InventoryItem(EntityPlayer player, int size) {
		super(getItems(player.getCurrentEquippedItem(), size));
		this.player = player;
		this.item = player.getCurrentEquippedItem();
		this.slot = player.inventory.currentItem;
	}
	
	@Override
	public String getInventoryName() { return item.getDisplayName(); }
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		super.setInventorySlotContents(slot, stack);
		updateItem();
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack result = super.decrStackSize(slot, amount);
		updateItem();
		return result;
	}
	
	protected void updateItem() {
		NBTTagList list = NbtUtils.writeItems(stacks.toArray(new ItemStack[stacks.size()]));
		StackUtils.set(item, list, TAG_ITEMS);
		player.inventory.setInventorySlotContents(slot, item.copy());
	}
	
	private static ItemStack[] getItems(ItemStack stack, int size) {
		ItemStack[] items = new ItemStack[size];
		NBTTagList list = (NBTTagList)StackUtils.getTag(stack, TAG_ITEMS);
		return ((list != null) ? NbtUtils.readItems(list, items) : items);
	}
	
}
