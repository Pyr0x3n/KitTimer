package com.Pyr0x3n.KitTimer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import code.husky.mysql.MySQL;

public class MySQLManager {
	private Main plugin;
	private String mysql_host;
	private String mysql_port;
	private String mysql_database;
	private String mysql_username;
	private String mysql_password;
	private String mysql_table;
	private MySQL db;
	

	public MySQLManager(Main plugin, String mysql_host, 
			String mysql_port, String mysql_database, 
			String mysql_username, String mysql_password, String mysql_table ){
		this.plugin = plugin;
		this.mysql_host = mysql_host;
		this.mysql_port = mysql_port;
		this.mysql_database = mysql_database;
		this.mysql_username = mysql_username;
		this.mysql_password = mysql_password;
		this.mysql_table = mysql_table;
		
	}

	public void setupDB() throws SQLException{
		this.db = new MySQL(this.plugin, mysql_host, mysql_port, mysql_database, mysql_username, mysql_password);
		this.db.openConnection();
	}
	
	public void addDateField() throws SQLException{
		if (!this.db.checkConnection())
			this.db.openConnection();	
		Statement statement = this.db.getConnection().createStatement();
		statement.executeUpdate("ALTER TABLE `" + mysql_table + "` ADD COLUMN `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;");
		statement.close();
	}

	public void closeDB(){
		this.db.closeConnection();
	}
	
	public void removeKit(int days) throws SQLException{
		if (!this.db.checkConnection())
			this.db.openConnection();
		Statement statement = this.db.getConnection().createStatement();
		statement.executeUpdate("DELETE FROM `" + mysql_table + "` WHERE `Date` < (DATE_SUB(NOW(), INTERVAL "+ days +" DAY));");
		statement.close();
	}
	
	
	public boolean playerInDatabase(String playerUUID) throws SQLException{
		if (!this.db.checkConnection())
			this.db.openConnection();
		Statement statement = this.db.getConnection().createStatement();
		ResultSet rs = statement.executeQuery("SELECT * FROM `" + mysql_table +"` WHERE `uuid` LIKE '" + playerUUID + "';");
		if(!rs.next()){
			statement.close();
			return false;
		}else{
		statement.close();
		return true;
		}
	}
	
	
	
	public void resetPlayerKits(String playerUUID) throws  SQLException{
		if (!this.db.checkConnection())
			this.db.openConnection();
		Statement statement = this.db.getConnection().createStatement();
		//TODO kolla om det fungerar
		statement.executeUpdate("DELETE FROM `" + mysql_table + "` WHERE `uuid` LIKE '" + playerUUID + "';");
		statement.close();	
	}
}
