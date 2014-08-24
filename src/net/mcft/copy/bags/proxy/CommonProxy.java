package net.mcft.copy.bags.proxy;

import net.mcft.copy.bags.container.ContainerPouch;
import net.mcft.copy.core.container.ContainerRegistry;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;

public class CommonProxy {
	
	public void init() {
		
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().bus().register(this);
		
		ContainerRegistry.register(ContainerPouch.class);
		
	}
	
}
