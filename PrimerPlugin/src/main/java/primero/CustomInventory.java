package primero;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;


@SuppressWarnings("unused")
public class CustomInventory implements Listener {

	private Plugin plugin = PrimerPlugin.getPlugin(PrimerPlugin.class);
	public Inventory market;
	
	public CustomInventory() {
		market = plugin.getServer().createInventory(null, 9, "Market Menu");
		createDisplay(Material.STORAGE_MINECART, market, 0, "SEND ITEM", "Send Item to the Market.");
		createDisplay(Material.COMPASS, market, 1, "EXPLORE MARKET", "Explore the market ");
		createDisplay(Material.CHEST, market, 2, "MY ITEMS", "Check items in the market");
	}
	
	public Inventory getInventory(){
		return market;
	}
	public static void createDisplay(Material material, Inventory inv, int Slot, String name, String lore) {
		//Creates an item to put in the inventory interface
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		ArrayList<String> Lore = new ArrayList<String>();
		Lore.add(lore);
		meta.setLore(Lore);
		item.setItemMeta(meta);
		 
		inv.setItem(Slot, item); 
		 
	}
	
	
	public void openMarket(Player player) {
			
			player.openInventory(market);
		
	}
	

	
}
