package rama.ubcminas;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;

import static rama.ubcminas.UBCoreMinas.sendLog;

public class Sender {

    private UBCoreMinas plugin;

    public Sender(UBCoreMinas plugin){
        this.plugin = plugin;
    }

    public void Send(String channel, String data, Player player){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        try {
            out.writeUTF("UBCoreChannel");
            out.writeUTF(channel);
            out.writeUTF(data);
            player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(plugin.debugMode){
            sendLog("Enviando &7"+data+" &emediante &7"+channel+"&e.", null, 2);
        }
    }
}
