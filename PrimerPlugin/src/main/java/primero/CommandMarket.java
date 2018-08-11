package primero;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHello implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command command, String label, String[] arguments) {
		
			if(sender instanceof Player) {
				
				Player senderPlayer = (Player) sender;
				senderPlayer.sendMessage( "Hello World!" );
				
				
				
			}
		return false;
	}

}
