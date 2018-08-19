package primero;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import org.bukkit.plugin.java.JavaPlugin;


public class PrimerPlugin extends JavaPlugin{
	
	private Connection connection;
	public String host, database, username, password, table;
	public int port;
	
    // Fired when plugin is first enabled
    @Override
    public void onEnable() {
    	
    	//mysql connection
    	mysqlSetup();
    	
    	CustomCommand command = new CustomCommand();
    	
        getServer().getPluginManager().registerEvents(new Currency(), this);
        getServer().getPluginManager().registerEvents(new CustomCommand(), this);
        this.getCommand(command.cmd1).setExecutor(command);
        this.getCommand(command.cmd2).setExecutor(command);
    }
    // Fired when plugin is disabled
    @Override
    public void onDisable() {

    }

	public void mysqlSetup(){
		host = "localhost";
		port = 3306;
		database = "minecraftserver";
		username = "minecraftserver";
		password = "Gamerz";
		table = "mainmarket";
		
		try{
			
			synchronized (this){
				if(getConnection() != null && !getConnection().isClosed()){
					return;
				}
				
				Class.forName("com.mysql.jdbc.Driver");
				setConnection( DriverManager.getConnection("jdbc:mysql://" + this.host + ":" 
				+ this.port + "/" + this.database, this.username, this.password));
				
				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "MYSQL CONNECTED");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	} 
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

    
}
