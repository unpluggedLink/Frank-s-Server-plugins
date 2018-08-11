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

	public String cmd1 = "market";
	CustomInventory market = new CustomInventory();
	
	@EventHandler
	public void onPlayerDragsInventory(InventoryDragEvent event) {
		if(event.getView().getTopInventory().getName().equals("Gamerz inn")){
			event.setCancelled(true);
        }
	}
	
	@EventHandler
	public void onPlayerClicksInventory(InventoryClickEvent event) {
		if(event.getView().getTopInventory().getName().equals("Gamerz inn")){
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
