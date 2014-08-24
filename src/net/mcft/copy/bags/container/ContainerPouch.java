package net.mcft.copy.bags.container;

import net.mcft.copy.bags.inventory.InventoryPouch;
import net.mcft.copy.core.container.ContainerBase;
import net.mcft.copy.core.inventory.slot.SlotBase;
import net.mcft.copy.core.inventory.slot.SlotProtected;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerPouch extends ContainerBase {
	
	public static final String TAG_PROTECTED = "protected";
	
	private final int protectedHotbarSlot;
	
	protected ContainerPouch(EntityPlayer player, int protectedHotbarSlot) {
		super(player, new InventoryPouch(player));
		this.protectedHotbarSlot = protectedHotbarSlot;
	}
	
	@SideOnly(Side.CLIENT)
	public ContainerPouch(EntityPlayer player, NBTTagCompound data) {
		this(player, data.getByte(TAG_PROTECTED));
	}
	
	public static ContainerPouch create(EntityPlayer player) {
		ContainerPouch container = new ContainerPouch(player, player.inventory.currentItem);
		container.initialize();
		return container;
	}
	
	@Override
	protected void setupPlayerSlots() {
		super.setupPlayerSlots();
		// Replace normal slot with protected one.
		SlotBase oldSlot = playerHotbarSlots.slots.get(protectedHotbarSlot);
		SlotBase newSlot = new SlotProtected(oldSlot.inventory, oldSlot.getSlotIndex(),
		                                     oldSlot.xDisplayPosition, oldSlot.yDisplayPosition);
		newSlot.slotNumber = oldSlot.slotNumber;
		playerHotbarSlots.slots.set(protectedHotbarSlot, newSlot);
		inventorySlots.set(newSlot.slotNumber, newSlot);
	}
	
	@Override
	public void writeToCompound(NBTTagCompound data) {
		super.writeToCompound(data);
		data.setByte(TAG_PROTECTED, (byte)protectedHotbarSlot);
	}
	
}
