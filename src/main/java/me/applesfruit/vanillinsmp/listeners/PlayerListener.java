package me.applesfruit.vanillinsmp.listeners;

import com.comphenix.protocol.PacketType;
import me.applesfruit.vanillinsmp.cmds.BanCommand;
import me.applesfruit.vanillinsmp.cmds.UnbanCommand;
import me.applesfruit.vanillinsmp.handlers.PlayerDataHandler;
import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.metadata.MetadataValue;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        // custom join message starts (nulling original message)
        e.setJoinMessage(null);
        // creates new data file for ranks, muting, banning, and chat color
        PlayerDataHandler.create(e.getPlayer());
        // if banned then get unbanned if in unban list.
        if (UnbanCommand.unbanList.contains(e.getPlayer().getName().toLowerCase())) {
            PlayerDataHandler.unbanPlayer(e.getPlayer());
            UnbanCommand.unbanList.remove(e.getPlayer().getName().toLowerCase());
            return;
        }
        // if ban list, then ban (offline banning functionality)
        if (BanCommand.banList.contains(e.getPlayer().getName().toLowerCase())) {
            PlayerDataHandler.banPlayer(e.getPlayer());
            BanCommand.banList.remove(e.getPlayer().getName().toLowerCase());
            return;
        }

        // if banned and joined, then kick
        if (PlayerDataHandler.findData(e.getPlayer()).isBanned()) {
            e.getPlayer().kickPlayer(TC.c("&cFailed to connect to the server:\n&cYou are permanently banned from VanillinSMP!"));
            return;
        }

        // if vanished then dont show join msg
        if (!isVanished(e.getPlayer())) {
            if (e.getPlayer().hasPlayedBefore())
                Bukkit.broadcastMessage(TC.c("&8[&a+&8] &b" + e.getPlayer().getDisplayName() + "&3 has joined."));
            else
                Bukkit.broadcastMessage(TC.c("&8[&a+&8] &3Welcome &b" + e.getPlayer().getDisplayName() + " &3to VanillinSMP!"));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        // nulls quit message for custom
        e.setQuitMessage(null);
        // quit event shows even when you kick, so check if banned then return (no left message)
        if (PlayerDataHandler.findData(e.getPlayer()).isBanned()) {
            PlayerDataHandler.removeArrayListPlayerData(e.getPlayer());
            return;
        }
        // remove player list data to save memory
        PlayerDataHandler.removeArrayListPlayerData(e.getPlayer());
        // if vanished, don't show message!
        if (!isVanished(e.getPlayer()))
            Bukkit.broadcastMessage(TC.c("&8[&4-&8] &b" + e.getPlayer().getDisplayName() + "&3 has left."));
    }

    @EventHandler
    public void onReload(PluginDisableEvent e) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            PlayerDataHandler.save(p);
        }
    }

    @EventHandler
    public void onEnable(PluginEnableEvent e) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            PlayerDataHandler.create(p);
        }
    }


    private boolean isVanished(Player player) {
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }

}
