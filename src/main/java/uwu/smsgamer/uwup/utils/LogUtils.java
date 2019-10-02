package uwu.smsgamer.uwup.Utils;

import uwu.smsgamer.uwup.UwUPunishments;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogUtils {
    /**
     * Logs a message to Punishment.log file.
     *
     * @param message Message you want to log to file.
     */
    public static void logToFile(String message) {

        try {
            File dataFolder = UwUPunishments.instance.getDataFolder();

            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            File saveTo = new File(UwUPunishments.instance.getDataFolder(), "Punishment.log");
            if (!saveTo.exists()) {
                saveTo.createNewFile();
            }

            FileWriter fw = new FileWriter(saveTo, true);

            PrintWriter pw = new PrintWriter(fw);

            pw.println(message);

            pw.flush();

            pw.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
