package me.applesfruit.vanillinsmp.handlers;

import me.applesfruit.vanillinsmp.util.PlayerData;
import me.applesfruit.vanillinsmp.util.TC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static me.applesfruit.vanillinsmp.cmds.MuteChatCommand.chat;

public class ChatPingHandler implements Listener {

    @EventHandler
    public void onChatA(AsyncPlayerChatEvent e) {
        if (chat) {
            if (!e.getPlayer().hasPermission("slnsmp.mutechat.bypass")) {
                e.setCancelled(true);
                return;
            }
        }
        e.setCancelled(true);
        for (Player p : Bukkit.getOnlinePlayers()) {
            String finalMessage;
            String formatted;
            if (e.getMessage().toLowerCase().contains(p.getDisplayName().toLowerCase())) {
                formatted = e.getMessage().toLowerCase().replaceAll(p.getDisplayName().toLowerCase(), TC.c("&b" + p.getDisplayName() + "&r"));
                finalMessage = TC.c("&f<" + e.getPlayer().getDisplayName() + "&f> &r" + formatted);

            } else
                finalMessage = TC.c("&f<" + e.getPlayer().getDisplayName() + "&f> &r" + e.getMessage());
                formatted = TC.c(e.getMessage());
            switch(PlayerDataHandler.findData(e.getPlayer()).getRank())
            {
                case MEMBER:
                    finalMessage = TC.c(PlayerData.Ranks.MEMBER.prefix + e.getPlayer().getDisplayName() + "&8: &r" + formatted);
                    break;
                case KNOWN_MEMBER:
                    finalMessage = TC.c(PlayerData.Ranks.KNOWN_MEMBER.prefix + e.getPlayer().getDisplayName() + "&8: &r" + formatted);
                    break;
                case RETIRED:
                    finalMessage = TC.c(PlayerData.Ranks.RETIRED.prefix + e.getPlayer().getDisplayName() + "&8: &r" + formatted);
                    break;
                case TRUSTED:
                    finalMessage = TC.c(PlayerData.Ranks.TRUSTED.prefix + e.getPlayer().getDisplayName() + "&8: &r" + formatted);
                    break;
                case STAFF:
                    finalMessage = TC.c(PlayerData.Ranks.STAFF.prefix + e.getPlayer().getDisplayName() + "&8: &r" + formatted);
                    break;
                case ADMIN:
                    finalMessage = TC.c(PlayerData.Ranks.ADMIN.prefix + e.getPlayer().getDisplayName() + "&8: &r" + formatted);
                    break;
            }

            p.sendMessage(finalMessage);
        }
    }
}
