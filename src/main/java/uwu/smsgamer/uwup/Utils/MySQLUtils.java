package uwu.smsgamer.uwup.Utils;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import uwu.smsgamer.uwup.UwUPunishments;

import java.sql.*;

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

    // Update players.yml

    public static void updatePlayers(String name, String player){
        if(!UwUPunishments.isSql)
            return;
        try {
            PreparedStatement ps0 = MySQLUtils.getConnection().prepareStatement(
                    "SELECT player, vl, b1 FROM "+tablePrefix+name+" WHERE player = ?");
            ps0.setString(1, player);
            ResultSet rs0 = ps0.executeQuery();
            boolean b1 = false;
            if (rs0.next()) {
                b1 = rs0.getBoolean("b1");
            }
            if(b1){
                PreparedStatement ps = MySQLUtils.getConnection().prepareStatement("SELECT vl FROM "+tablePrefix+name+" WHERE player = ?");
                ps.setString(1, player);
                ResultSet rs = ps.executeQuery();
                int vl = 0;
                if (rs.next()) {
                    vl = rs.getInt("vl");
                }
                ConfigUtils.setVl(player, name, true, vl);
            }
        }catch (SQLException ignored){}
    }

    // Create Table
    public static void makeTable(String name){
        if(!UwUPunishments.isSql)
            return;
        try{
            PreparedStatement ps = MySQLUtils.getConnection().prepareStatement(
                    "CREATE TABLE IF NOT EXISTS "+tablePrefix+name+" (player VARCHAR(100), vl INT(100), b1 BOOLEAN)");
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    // Insert in table
    public static void insertToTable(String name, String player, int vl){
        if(!UwUPunishments.isSql)
            return;
        try {
            PreparedStatement ps0 = MySQLUtils.getConnection().prepareStatement("DELETE FROM "+tablePrefix+name+" WHERE player = ?");
            ps0.setString(1, player);
            ps0.executeUpdate();

            PreparedStatement ps1 = MySQLUtils.getConnection().prepareStatement
                    ("INSERT IGNORE INTO "+tablePrefix+name+" (player, vl, b1) VALUES (?,?,?)");
            ps1.setString(1, player);
            ps1.setInt(2, vl);
            ps1.setBoolean(3, true);
            ps1.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}