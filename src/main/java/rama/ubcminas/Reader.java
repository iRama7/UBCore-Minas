package rama.ubcminas;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Reader implements PluginMessageListener {

    private UBCoreMinas plugin;

    public Reader(UBCoreMinas plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] bytes) {
        if (channel.equals("ub:channel")) {
            ByteArrayDataInput in = ByteStreams.newDataInput(bytes);
            String subChannel = in.readUTF();
            if (subChannel.equals("BungeeMainCommandChannel")) {
                String player_name = in.readUTF();
                String send = in.readUTF();
                Player p = Bukkit.getPlayer(player_name);
                CommandExecutor CE = new CommandExecutor(plugin);
                CE.executeCommand(p, send, 1);
                Bukkit.getLogger().warning("DEBUG 01");
            } else if (subChannel.equals("PlayerJoinEvent")) {
                String player_name = in.readUTF();
                Player player1 = Bukkit.getPlayer(player_name);
                String msg = "";
                if (player1.hasPlayedBefore()) {
                    msg = plugin.getConfig().getString("messages.join");
                } else {
                    msg = plugin.getConfig().getString("messages.new-player");
                }
                if (!Bukkit.getOnlinePlayers().isEmpty()) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', msg.replaceAll("%player_name%", player_name)));
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 100, 2);
                    }
                }

            }

        }
    }
}
