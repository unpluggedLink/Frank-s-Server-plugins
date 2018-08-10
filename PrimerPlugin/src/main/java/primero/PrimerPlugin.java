package primero;

import org.bukkit.plugin.java.JavaPlugin;

public class PrimerPlugin extends JavaPlugin{
	
    // Fired when plugin is first enabled
    @Override
    public void onEnable() {
        this.getCommand("hello").setExecutor(new CommandHello());
        getServer().getPluginManager().registerEvents(new Currency(), this);
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }
}
