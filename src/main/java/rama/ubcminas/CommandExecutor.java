package rama.ubcminas;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

import static rama.ubcminas.UBCoreMinas.sendLog;

public class CommandExecutor {

    private UBCoreMinas plugin;

    public CommandExecutor(UBCoreMinas plugin){
        this.plugin = plugin;
    }

    public void executeCommand(Player p, String s, int i){
        Boolean debugMode = plugin.getConfig().getBoolean("debug-mode");
        if(!plugin.getConfig().isSet("commands."+s)) {
            sendLog("No se pudo encontrar la lista de comandos para "+s,null, 3);
            return;
        }
        FileConfiguration Database = plugin.getDatabase();
        Boolean disconnected_in_world = Database.getBoolean(p.getName() + ".world");
        Boolean disconnected_in_worldNether = Database.getBoolean(p.getName() + ".world_nether");
        if(i==1){
            //Cancel the event if the player disconnected in this world
            if(s.equals("minas") && disconnected_in_world){
                if(debugMode){
                    sendLog("Cancelling command executor event because player disconnected in minas world", null, 2);
                }
                return;
            }
            if(s.equals("nether") && disconnected_in_worldNether){
                if(debugMode){
                    sendLog("Cancelling command executor event because player disconnected in nether world", null, 2);
                }
                return;
            }
        }

            List<String> commandsList = plugin.getConfig().getStringList("commands." + s);
            for(String cmd : commandsList){
                String[] commandParts = cmd.split(";");
                String commandType = commandParts[0];
                String command = commandParts[1].replaceAll("%player%", p.getName());
                if(commandType.equals("[CONSOLE]")){
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command);
                    if(plugin.debugMode){
                        sendLog("Executing console command "+command,null,2);
                    }
                }else if(commandType.equals("[PLAYER]")){
                    Bukkit.dispatchCommand(p,command);
                    if(plugin.debugMode){
                        sendLog("Executing player command "+command,null,2);
                    }
                }else{
                    sendLog("No se pudo identificar el tipo de comando para "+cmd,null, 3);
                }
            }

    }

}
