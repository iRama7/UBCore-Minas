package rama.ubcminas.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rama.ubcminas.Sender;

import static rama.ubcminas.UBCoreMinas.plugin;
import static rama.ubcminas.UBCoreMinas.sendLog;

public class Villa implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String permission = plugin.getConfig().getString("permissions.comando-villa");
        Player player = (Player) sender;
        if(player.hasPermission(permission)){
            String data = player.getName()+";villa";
            Sender sendClass = new Sender(plugin);
            sendClass.Send("MainCommandChannel", data, player);
        }else{
            sendLog("&cNo puedes hacer eso ahora! ¿Has terminado el tutorial?", (Player) sender, 4);
        }
        return false;
    }
}
