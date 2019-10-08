package uwu.smsgamer.uwup.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import uwu.smsgamer.uwup.UwUPunishments;

public class MySQLUtils {

    public static String host;
    public static int port;
    public static String database;
    public static String username;
    public static String password;
    public static String tablePrefix;
    public static Connection con;

    static ConsoleCommandSender console = Bukkit.getConsoleSender();

    // connect
    public static void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                console.sendMessage("\247c[\2476Minepedia-System\247c] \247bMySQL-Verbindung wurde aufgebaut!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // disconnect
    public static void disconnect() {
        if (isConnected()) {
            try {
                con.close();
                console.sendMessage("\247c[\2476Minepedia-System\247c]\247bMySQL-Verbindung wurde geschlossen!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // isConnected
    public static boolean isConnected() {
        return (con != null);
    }

    // getConnection
    public static Connection getConnection() {
        return con;
    }

    // Create Table
    public static void makeTable(String name, String player, int vl){
        try{
            PreparedStatement ps = MySQLUtils.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS "+name+" (Name VARCHAR(100),UUID VARCHAR(100),Coins INT(100),PRIMARY KEY (Name))");
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}