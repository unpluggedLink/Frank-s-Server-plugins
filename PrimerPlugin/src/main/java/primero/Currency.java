package primero;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Currency implements Listener{
	
	@EventHandler
	public void onCheckCoins(PlayerInteractEvent event) {
		
		Player player = (Player) event.getPlayer();
		Material itemType = player.getInventory().getItemInMainHand().getType();
		
		if (isCurrency(itemType)) {
			
			float nuggGoldValue = 1;
			float ingotGoldValue = 9;
			float nuggIronValue = (float) 0.1;
			float ingotIronValue = (float) 0.9;
			
			int nuggetCoinsGold = howMuch(player, Material.GOLD_NUGGET);
			int nuggetCoinsIron = howMuch(player, Material.IRON_NUGGET);
			int ingotsGold = howMuch(player, Material.GOLD_INGOT);
			int ingotsIron = howMuch(player, Material.IRON_INGOT);
			
			float money = (nuggetCoinsGold * nuggGoldValue) + (nuggetCoinsIron * nuggIronValue) + (ingotsGold * ingotGoldValue) + (ingotsIron * ingotIronValue);
			
			player.sendMessage("You have " + money + " coinns");
		}
			
		
	}
	
	//With this function you can check how much material a player currently has but it most have some currency on his hands first
	public int howMuch(Player player, Material material) {

		int coins = 0;
		
		//here we iterate over every slot of the player inventory
		for(int i = 0; i <= player.getInventory().getContents().length; i++) {
				
			if (player.getInventory().getItem(i) != null) {
					
				//We detect if the current inventory slot has gold ingot and if true the amount of the stack 
				if (player.getInventory().getItem(i).getType().equals(material)){
						
					//we add 1 coin for every ingot
					coins = coins + player.getInventory().getItem(i).getAmount();
				}
			}
		}
			return coins;
	}
	
	public boolean isCurrency(Material material){
	//Checks if the material send is currency or not
		
		boolean isMoney = false;
		Material[] currency = { Material.GOLD_INGOT, Material.GOLD_NUGGET, Material.IRON_INGOT, Material.IRON_NUGGET };
		
		if(material != null) {
		
			for (Material moneytype : currency) {
			
				if ( material.equals(moneytype) ) {
		    	
					isMoney = true;
					break;
		    	
				}else {
		    	
					isMoney = false;
					
				}
			}
		}
	
		return isMoney;
	}

}
