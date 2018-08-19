package primero;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.plugin.Plugin;

public class CustomCommand implements CommandExecutor, Listener{

	public String cmd1 = "market";
	public String cmd2 = "price";
	CustomInventory market = new CustomInventory();
	boolean selectingItem = false;
	Connection con = PrimerPlugin.getPlugin(PrimerPlugin.class).getConnection();
	Plugin objplugin = PrimerPlugin.getPlugin(PrimerPlugin.class);
	private String table;

	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		Player senderPlayer = (Player) sender;
		
		if ( command != null) {
			if (command.getName().equals(cmd1)) {
				market.openMarket(senderPlayer);
			}else if (command.getName().equals(cmd2)) {
				int itemId = Integer.parseInt( args[1] );
				float price =  Float.parseFloat( args[0] ) ;
				try {
					updatePrice( price, itemId);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}
	
	public void updatePrice(float price, int id) {

		table = PrimerPlugin.getPlugin(PrimerPlugin.class).table;

		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Editando el item " + id );
		PreparedStatement updateItemPrice;
		try {
			updateItemPrice = con.prepareStatement("UPDATE mainmarket SET price=? WHERE id=?");
			updateItemPrice.setFloat(1, price);
			updateItemPrice.setInt(2, id);
			updateItemPrice.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@EventHandler
	public void onPlayerDragsInventory(InventoryDragEvent event) {

		if(event.getView().getTopInventory().getName().equals("Market Menu")){
			event.setCancelled(true);
        }
	}
	
	@EventHandler
	public void onPlayerClicksInventory(InventoryClickEvent event) {

		String clickedItemName = event.getCurrentItem().getItemMeta().getDisplayName();
		String clickedItemType = event.getCurrentItem().getType().toString();
		int clickedItemQuantity = event.getCurrentItem().getAmount();
		float defaultprice = 1000;
		Player activePlayer = (Player) event.getWhoClicked();
		String table = PrimerPlugin.getPlugin(PrimerPlugin.class).table;
		String inventoryClicked = event.getInventory().getName();
		
		if (selectingItem == true && clickedItemName != "SEND ITEM") {
		//when clicked on SEND ITEM you activated SELECTING ITEM
			activePlayer.sendMessage( "added "+ clickedItemQuantity + " "+ event.getCurrentItem().getType().toString() + " to market");
			// debugging line
			try {
				PreparedStatement addItemToMarket = con.prepareStatement("INSERT INTO " + table + " (NAME, OWNER, QUANTITY, PRICE) VALUES (?,?,?,?)");
				addItemToMarket.setString(1, clickedItemType);
				addItemToMarket.setString(2, activePlayer.getDisplayName());
				addItemToMarket.setInt(3, clickedItemQuantity);
				addItemToMarket.setFloat(4, defaultprice);
				addItemToMarket.executeUpdate();
				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "adding to market database...");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			selectingItem = false;
				
		}
		
		if( clickedItemName == "SEND ITEM" && selectingItem == false ){	
		//Triggers when player clicks on SEND ITEM button
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

}
