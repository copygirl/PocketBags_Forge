package net.mcft.copy.bags.item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mcft.copy.bags.PocketBags;
import net.mcft.copy.bags.api.PouchAPI;
import net.mcft.copy.bags.api.PouchAPI.Entry;
import net.mcft.copy.bags.container.ContainerPouch;
import net.mcft.copy.bags.inventory.InventoryPouch;
import net.mcft.copy.core.base.ItemBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ItemPouch extends ItemBase {
	
	private static int[][] useOrder = {
		{ 0, 0 }, { -1,  0 }, { +1,  0 }, {  0, -1 }, {  0, +1 },
		          { -1, -1 }, { +1, -1 }, { -1, -1 }, { -1, +1 },
		          { -1, +1 }, { +1, +1 }, { +1, -1 }, { +1, +1 } };
	
	public static final int SLOTS = 9;
	
	private Map<Entry, IIcon> icons = new HashMap<Entry, IIcon>();
	
	public ItemPouch() {
		setTextureName(PocketBags.MOD_ID + ":pouch/empty");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister) {
		super.registerIcons(iconRegister);
		for (Entry entry : PouchAPI.getEntries())
			icons.put(entry, iconRegister.registerIcon(entry.icon));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconIndex(ItemStack stack) {
		IIcon icon = icons.get(PouchAPI.getEntry(new InventoryPouch(stack).getItem()));
		return ((icon != null) ? icon : super.getIconIndex(stack));
	}
	@Override
	public IIcon getIcon(ItemStack stack, int pass) { return getIconIndex(stack); }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean advancedTooltips) {
		ItemStack items = new InventoryPouch(stack).getItems();
		if (items != null) list.add(items.stackSize + "x " + items.getDisplayName());
	}
	
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return (new InventoryPouch(stack).getItem() != null);
	}
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		ItemStack items = new InventoryPouch(stack).getItems();
		return (1.0 - (double)items.stackSize / (items.getMaxStackSize() * SLOTS));
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (!player.worldObj.isRemote && player.isSneaking())
			ContainerPouch.create(player).open();
		return stack;
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player,
	                         World world, int x, int y, int z, int side,
	                         float hitX, float hitY, float hitZ) {
		if (player.worldObj.isRemote || player.isSneaking())
			return false;
		
		InventoryPouch pouch = InventoryPouch.getOrCreate(player, player.inventory.currentItem);
		ItemStack item = getNextItem(pouch, null);
		if (!PouchAPI.getEntry(item).isSeed)
			return false;
		boolean used = false;
		
		for (int[] xz : useOrder) {
			if ((item = getNextItem(pouch, item)) == null) break;
			if (item.tryPlaceItemIntoWorld(player, world, x + xz[0], y, z + xz[1], side, hitX, hitY, hitZ)) {
				item = setLastPouchItem(pouch, item);
				used = true;
			}
		}
		return used;
	}
	
	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target) {
		if (player.worldObj.isRemote || player.isSneaking() ||
		    !(target instanceof EntityAnimal))
			return false;
		
		InventoryPouch pouch = InventoryPouch.getOrCreate(player, player.inventory.currentItem);
		ItemStack item = getNextItem(pouch, null);
		if (!PouchAPI.getEntry(item).isFood ||
		    !((EntityAnimal)target).isBreedingItem(item))
			return false;
		boolean used = false;
		
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(
				target.posX - 2, target.posY - 1, target.posZ - 2,
				target.posX + 2, target.posY + 1, target.posZ + 2);
		List<EntityAnimal> entities = target.worldObj.getEntitiesWithinAABB(target.getClass(), aabb);
		
		for (EntityAnimal entity : entities) {
			if ((item = getNextItem(pouch, item)) == null) break;
			if ((entity.getGrowingAge() == 0) && !entity.isInLove() &&
			    entity.isBreedingItem(item)) {
				entity.func_146082_f(player);
				item.stackSize--;
				item = setLastPouchItem(pouch, item);
				used = true;
			}
		}
		return used;
	}
	
	private int lastPouchItem = -1;
	private ItemStack getNextItem(InventoryPouch pouch, ItemStack current) {
		if (current == null)
			for (lastPouchItem = SLOTS - 1; lastPouchItem >= 0; lastPouchItem--)
				if ((current = pouch.getStackInSlot(lastPouchItem)) != null)
					break;
		return current;
	}
	private ItemStack setLastPouchItem(InventoryPouch pouch, ItemStack stack) {
		if (stack.stackSize == 0) stack = null;
		pouch.setInventorySlotContents(lastPouchItem, stack);
		return stack;
	}
	
}
