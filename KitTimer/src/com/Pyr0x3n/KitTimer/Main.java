package com.Pyr0x3n.KitTimer;

import java.sql.SQLException;
import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{

	public boolean logDB = true;
	public MySQLManager mySQL;
	
	private String mysql_host;
	private String mysql_port;
	private String mysql_database;
	private String mysql_username;
	private String mysql_password;
	private String mysql_table;
	
	@Override
	public void onEnable(){
		saveDefaultConfig();	
		mySQLConf();
		getCommand("RemoveKits").setExecutor(new RemoveKits(this));
		mySQL= new MySQLManager(this, mysql_host, mysql_port, mysql_database, mysql_username, mysql_password, mysql_table);
		try {
			this.mySQL.setupDB();
		} catch (SQLException e) {
			this.logDB = false;
			this.log("Database Connection problems ", Level.WARNING);
			this.log("Error" + e, Level.WARNING);
		}
		addDateRecordField();
		removeOlderKit();
	}
	
	private void mySQLConf(){
		this.mysql_host = getConfig().getString("mysql_host");
		this.mysql_port = getConfig().getString("mysql_port");
		this.mysql_database = getConfig().getString("mysql_database");
		this.mysql_username = getConfig().getString("mysql_username");
		this.mysql_password = getConfig().getString("mysql_password");
		this.mysql_table = getConfig().getString("mysql_table");
	}
	
	private void removeOlderKit(){	
		int days = getConfig().getInt("numberOfDays");
		if(this.logDB){	
			try{
				this.mySQL.removeKit(days);
			}catch(SQLException exept){
				this.log("Could not delete kits", Level.WARNING);
				this.log("Error" + exept, Level.WARNING);
			}
		}
	}
	
	
	private void addDateRecordField(){
		if(this.logDB){	
			try{
				this.mySQL.addDateField();;
			}catch(SQLException exept){
				this.log("Date field allready setup", Level.INFO);
			}
		}
	}
	
	@Override
	public void onDisable() {
		this.mySQL.closeDB();
	}
	
	public void log(String s, Level l){
		getLogger().log(l, s);
	}

}