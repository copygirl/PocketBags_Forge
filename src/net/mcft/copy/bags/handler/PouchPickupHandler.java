package net.mcft.copy.bags.handler;

import net.mcft.copy.bags.api.PouchAPI;
import net.mcft.copy.bags.api.PouchAPI.Entry;
import net.mcft.copy.bags.inventory.InventoryPouch;
import net.mcft.copy.bags.item.ItemPouch;
import net.mcft.copy.core.util.InventoryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PouchPickupHandler {
	
	@SubscribeEvent
	public void onPickup(EntityItemPickupEvent event) {
		EntityPlayer player = event.entityPlayer;
		ItemStack item = event.item.getEntityItem();
		
		for (int i = 0; i < 9; i++) {
			ItemStack stack = player.inventory.getStackInSlot(i);
			if ((stack != null) && (stack.getItem() instanceof ItemPouch)) {
				InventoryPouch inv = InventoryPouch.getOrCreate(player, i);
				ItemStack type = inv.getItem();
				Entry entry = PouchAPI.getEntry(type);
				if ((entry == null) || !entry.matches(item)) continue;
				
				ItemStack result = InventoryUtils.insertStack(inv, item, false);
				if (result != null) item.stackSize = result.stackSize;
				else { item.stackSize = 0; return; }
			}
		}
	}
	
}
