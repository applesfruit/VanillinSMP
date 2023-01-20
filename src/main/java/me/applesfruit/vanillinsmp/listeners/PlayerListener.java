package me.applesfruit.vanillinsmp.listeners;

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
        e.setJoinMessage(null);
        PlayerDataHandler.create(e.getPlayer());
        if (UnbanCommand.unbanList.contains(e.getPlayer().getName().toLowerCase()))
        {
            PlayerDataHandler.unbanPlayer(e.getPlayer());
            UnbanCommand.unbanList.remove(e.getPlayer().getName().toLowerCase());
        }
        if (PlayerDataHandler.findData(e.getPlayer()).isBanned())
        {
            e.getPlayer().kickPlayer(TC.c("&cFailed to connect to the server:\n&cYou are permanently banned from VanillinSMP!"));
            return;
        }
        if (!isVanished(e.getPlayer()))
        {
            if (e.getPlayer().hasPlayedBefore())
                Bukkit.broadcastMessage(TC.c("&8[&a+&8] &b" + e.getPlayer().getDisplayName() + "&3 has joined."));
            else
                Bukkit.broadcastMessage(TC.c("&8[&a+&8] &3Welcome &b" + e.getPlayer().getDisplayName() + " &3to SLNSMP!"));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        e.setQuitMessage(null);
        if (PlayerDataHandler.findData(e.getPlayer()).isBanned()) { PlayerDataHandler.removeArrayListPlayerData(e.getPlayer()); return; }
        PlayerDataHandler.removeArrayListPlayerData(e.getPlayer());
        if (!isVanished(e.getPlayer()))
            Bukkit.broadcastMessage(TC.c("&8[&4-&8] &b" + e.getPlayer().getDisplayName() + "&3 has left."));
    }

    @EventHandler
    public void onReload(PluginDisableEvent e)
    {
        for (Player p : Bukkit.getOnlinePlayers())
        {
            PlayerDataHandler.save(p);
        }
    }

    @EventHandler
    public void onEnable(PluginEnableEvent e)
    {
        for (Player p : Bukkit.getOnlinePlayers())
        {
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
