package primero;

import org.bukkit.plugin.java.JavaPlugin;

public class PrimerPlugin extends JavaPlugin{
	
    // Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	
    	CustomCommand command = new CustomCommand();
    	
        getServer().getPluginManager().registerEvents(new Currency(), this);
        getServer().getPluginManager().registerEvents(new CustomCommand(), this);
        this.getCommand(command.cmd1).setExecutor(command);
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
}
