package net.mcft.copy.bags.content;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public final class BagRecipes {
	
	private BagRecipes() {  }
	
	public static void register() {
		
		GameRegistry.addShapedRecipe(new ItemStack(BagItems.pouch),
				"sos",
				"o#o",
				"ooo", 'o', Blocks.wool,
				       's', Items.string,
				       '#', Items.leather);
		
	}
	
}
