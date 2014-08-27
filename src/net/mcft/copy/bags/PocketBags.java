package net.mcft.copy.bags;

import net.mcft.copy.bags.content.BagItems;
import net.mcft.copy.bags.content.BagRecipes;
import net.mcft.copy.bags.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = PocketBags.MOD_ID, version = "${version}",
     useMetadata = false, dependencies = "required-after:copycore")
public class PocketBags {
	
	public static final String MOD_ID = "PocketBags";
	
	@SidedProxy(clientSide = "net.mcft.copy.bags.proxy.ClientProxy",
	            serverSide = "net.mcft.copy.bags.proxy.CommonProxy")
	private static CommonProxy proxy;
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		BagItems.register();
		BagRecipes.register();
		
		proxy.init();
		
	}
	
}
