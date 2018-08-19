package primero;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class CustomCommand implements CommandExecutor, Listener{

	boolean selectingItem = false;
	public String cmd1 = "market";
	CustomInventory market = new CustomInventory();
	
	@EventHandler
	public void onPlayerDragsInventory(InventoryDragEvent event) {

		if(event.getView().getTopInventory().getName().equals("Market Menu")){
			event.setCancelled(true);
        }
	}
	
	@EventHandler
	public void onPlayerClicksInventory(InventoryClickEvent event) {

		String clickedItemName = event.getCurrentItem().getItemMeta().getDisplayName();
		Player activePlayer = (Player) event.getWhoClicked();
		String inventoryClicked = event.getInventory().getName();
		
		if (selectingItem == true && event.getCurrentItem() != null && clickedItemName != "SEND ITEM") {
			activePlayer.sendMessage( event.getCurrentItem().getType().toString() );
			selectingItem = false;
			
		}
		
		if( clickedItemName == "SEND ITEM" && selectingItem == false ){
			
			selectingItem = true;
			event.setCancelled(true);

		}else if( clickedItemName == "EXPLORE MARKET" && selectingItem == false) {

			event.setCancelled(true);

		}else if( clickedItemName == "MY ITEMS" && selectingItem == false) {

			event.setCancelled(true);

		}
		
		if( event.getView().getTopInventory().getName().equals("Market Menu") ){

			event.setCancelled(true);
        }

	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player senderPlayer = (Player) sender;
		
		if ( command != null) {
			if (command.getName().equals(cmd1)) {
				market.openMarket(senderPlayer);
			}
		}
		
		return false;
	}

}
