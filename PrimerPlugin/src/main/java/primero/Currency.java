package primero;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Currency implements Listener{
	
	@EventHandler
	public void onCheck(PlayerInteractEvent event) {
		Player player = (Player) event.getPlayer();
		ItemStack actualItem = player.getInventory().getItemInMainHand();
		//Here I check the inventory of the player to check how much GOLD_INGOT he's carrying
		if(actualItem.getType().equals(Material.GOLD_INGOT)) {
			int coins = 0;

			for(int i = 0; i <= player.getInventory().getContents().length; i++) {
				if (player.getInventory().getItem(i) != null) {
					if (player.getInventory().getItem(i).getType().equals(Material.GOLD_INGOT)){
						coins = coins + player.getInventory().getItem(i).getAmount();
					}
				}
			}
			player.sendMessage("You have " + coins + " pieces of gold");
		}
	}

}
