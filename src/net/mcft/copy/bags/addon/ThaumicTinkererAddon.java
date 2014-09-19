package net.mcft.copy.bags.addon;

public class ThaumicTinkererAddon extends Addon {
	
	private static final String[] types = { "aer", "ignis", "terra", "aqua" };
	
	public static final ThaumicTinkererAddon instance = new ThaumicTinkererAddon();
	
	private ThaumicTinkererAddon() { super("ThaumicTinkerer"); }
	
	@Override
	public void register() {
		
		for (int i = 0; i < types.length; i++) {
			registerPouchItem("infusedSeeds", i, types[i] + "_seeds", true, false);
			registerPouchItem("infusedGrain", i, types[i] + "_grain", false, false);
		}
		
	}
	
}
