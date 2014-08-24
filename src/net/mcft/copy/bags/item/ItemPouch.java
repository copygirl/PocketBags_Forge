package net.mcft.copy.bags.item;

import net.mcft.copy.bags.container.ContainerPouch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class ItemPouch extends ItemBag {
	
	public static final int SLOTS = 9;
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!player.worldObj.isRemote)
			ContainerPouch.create(player).open();
		return stack;
	}
	
	public static boolean isPouchItem(ItemStack stack) {
		return true;
	}
	
}
