package rama.ubcminas.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static rama.ubcminas.UBCoreMinas.plugin;
import static rama.ubcminas.UBCoreMinas.sendLog;

public class Dragon implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String dragonPerm = plugin.getConfig().getString("permissions.comando-dragon");
        if(!(sender instanceof Player)){
            sendLog("Por qué intentarías usar ese comando desde la consola?",null,3);
        }else{
            if(sender.hasPermission(dragonPerm)){
                rama.ubcminas.CommandExecutor CE = new rama.ubcminas.CommandExecutor(plugin);
                CE.executeCommand((Player) sender,"dragon", 0);
                if(plugin.debugMode){
                    sendLog("Enviando orden para ejecutar comando de dragon a "+ sender.getName(), null, 2);
                }
            }else{
                sendLog("&cNo puedes hacer eso ahora! ¿Has terminado el tutorial?", (Player) sender, 4);
            }
        }
        return false;
    }
}
