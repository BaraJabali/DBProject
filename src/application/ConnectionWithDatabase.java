package application;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ConnectionWithDatabase {
	private Connection connection;
	private String dataBaseURL;
	private String dataBaseUsername;
	private String dataBasePassword;
	private String dataBaseName;
	private String URL, port;

	ConnectionWithDatabase(String URL, String port, String dataBaseName, String dataBaseUsername, String dataBasePassword) {
		this.URL = URL;
		this.port = port;
		this.dataBaseName = dataBaseName;
		this.dataBaseUsername = dataBaseUsername;
		this.dataBasePassword = dataBasePassword;
	}

	public Connection makeConnections() throws ClassNotFoundException, SQLException {
		dataBaseURL = "jdbc:mysql://" + URL + ":" + port + "/" + dataBaseName + "?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=ConvertToNull&serverTimezone=GMT";
		Properties p = new Properties();
		p.setProperty("user", dataBaseUsername);
		p.setProperty("password", dataBasePassword);
		p.setProperty("useSSL", "false");
		p.setProperty("autoReconnect", "true");
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(dataBaseURL, p);
		return connection;
	}
	
	
	
}