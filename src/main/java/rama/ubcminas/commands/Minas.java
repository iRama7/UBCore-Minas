package rama.ubcminas.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static rama.ubcminas.UBCoreMinas.plugin;
import static rama.ubcminas.UBCoreMinas.sendLog;

public class Minas implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String minasPerm = plugin.getConfig().getString("permissions.comando-minas");
        if(!(sender instanceof Player)){
            sendLog("Por qué intentarías usar ese comando desde la consola?",null,3);
        }else{
            if(sender.hasPermission(minasPerm)){
                rama.ubcminas.CommandExecutor CE = new rama.ubcminas.CommandExecutor(plugin);
                CE.executeCommand((Player) sender,"minas", 0);
                if(plugin.debugMode){
                    sendLog("Enviando orden para ejecutar comando de minas a "+ sender.getName(), null, 2);
                }
            }else{
                sendLog("&cNo puedes hacer eso ahora! ¿Has terminado el tutorial?", (Player) sender, 4);
            }
        }
        return false;
    }
}
