package primero;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;


public class CustomInventory implements Listener {

	private Plugin plugin = PrimerPlugin.getPlugin(PrimerPlugin.class);
	public Inventory market;
	
	public CustomInventory() {
		market = plugin.getServer().createInventory(null, 9, "Gamerz inn");
	}
	
	public Inventory getInventory(){
		return market;
	}
	public void openMarket(Player player) {
			
			player.openInventory(market);
		
	}
	

	
}
