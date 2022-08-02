package rama.ubcminas.commands.parcelas;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rama.ubcminas.Sender;

import static rama.ubcminas.UBCoreMinas.plugin;
import static rama.ubcminas.UBCoreMinas.sendLog;

public class Parcelas implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String permission = plugin.getConfig().getString("permissions.comando-parcelas");
        if(player.hasPermission(permission)){
            Sender senderClass = new Sender(plugin);
            String data = player.getName()+";parcelas";
            senderClass.Send("MainCommandChannel", data, player);
        }else{
            sendLog("&cNo puedes hacer eso ahora! ¿Has terminado el tutorial?", (Player) sender, 4);
        }
        return false;
    }
}
