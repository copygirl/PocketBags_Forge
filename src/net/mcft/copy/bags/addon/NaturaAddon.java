package net.mcft.copy.bags.addon;

public class NaturaAddon extends Addon {
	
	public static final NaturaAddon instance = new NaturaAddon();
	
	private NaturaAddon() { super("Natura"); }
	
	@Override
	public void register() {
		
		registerPouchItem("seedBarley", "barley_seeds", true, false);
		registerPouchItem("barleyFood", 0, "barley", false, true);
		
		registerPouchItem("seedCotton", "cotton_seeds", true, false);
		registerPouchItem("barleyFood", 3, "cotton", false, false);
		
	}
	
}
